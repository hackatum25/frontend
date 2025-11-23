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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.example.project.model.Tag
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TagSelector(
    tags: List<Tag> = listOf(Tag.OFFICIAL, Tag.ENVIRONMENT, Tag.EVENTS, Tag.TRANSPORT, Tag.FINANCE),
    selectedTags: SnapshotStateList<Tag> = remember { tags.toMutableStateList() } // keep this single instance
) {
    val items = tags.map { FilterItem.fromTag(it) }


    val scrollState = rememberScrollState()
    var containerWidth by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { containerWidth = it.size.width }
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.forEach { item ->
                TagChip(item = item, selectedTags = selectedTags)
            }
        }

        // thumb (unchanged)
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
fun TagChip(item: FilterItem, selectedTags: SnapshotStateList<Tag>) {
    val tag = FilterItem.toTag(item) // convert to Tag
    val isSelected = selectedTags.contains(tag)

    FilterChip(
		   onClick = {
            if (selectedTags.contains(tag)) selectedTags.remove(tag) else selectedTags.add(tag)
        },
        label = { Text(stringResource(item.title)) },
        selected = isSelected,
        leadingIcon = {
            Icon(
                painter = painterResource(item.icon),
                modifier = Modifier.size(FilterChipDefaults.IconSize),
                contentDescription = null
            )
        }
    )
}
