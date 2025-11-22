package org.example.project.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TagFilter() {
    val items = listOf(
        FilterItem.Environment, FilterItem.Official,
        FilterItem.Events, FilterItem.Finances, FilterItem.Transport
    )
    val scrollState = rememberScrollState()
    var containerWidth by remember { mutableStateOf(0) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .onGloballyPositioned { containerWidth = it.size.width }
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.forEach { AddItem(it) }
        }

        // thumb
        val max = (scrollState.maxValue).coerceAtLeast(1)
        val fraction = scrollState.value.toFloat() / max
        val thumbWidthFraction = 0.25f
        val thumbWidthPx = (containerWidth * thumbWidthFraction).toInt()
        val availablePx = (containerWidth - thumbWidthPx).coerceAtLeast(0)
        val thumbOffsetPx = (fraction * availablePx).toInt()

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .height(4.dp)
                .padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .offset { IntOffset(thumbOffsetPx, 0) }
                    .width(with(LocalDensity.current) { thumbWidthPx.toDp() })
                    .height(4.dp)
                    .alpha(0f)
                    .background(Color.Gray, RoundedCornerShape(2.dp))
            )
        }
    }
}

@Composable
fun AddItem(item: FilterItem) {
    var selected by remember { mutableStateOf(false) }
    FilterChip(
        onClick = { selected = !selected },
        label = {
            Text(stringResource(item.title))
        },
        selected = selected,
        leadingIcon = {
                Icon(
                    painter = painterResource(item.icon),
                    modifier = Modifier.size(FilterChipDefaults.IconSize),
                    contentDescription = "",
                )
        }
    )
}

