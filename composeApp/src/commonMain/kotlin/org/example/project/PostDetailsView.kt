package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.example.project.generated.Res
import org.example.project.generated.arrow_downward_24px
import org.example.project.generated.arrow_upward_24px
import org.example.project.generated.change_lang
import org.example.project.generated.compose_multiplatform
import org.example.project.generated.hello
import org.example.project.generated.nest_clock_farsight_analog_24px
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

fun formatTimestamp(timestamp: String): String {
    return try {
        // Assuming format like "2024-12-15T14:30:00Z"
        val parts = timestamp.split("T")
        val datePart = parts[0] // "2024-12-15"
        val timePart = parts[1].substringBefore(".").substringBefore("Z") // "14:30:00"

        val dateComponents = datePart.split("-")
        val year = dateComponents[0]
        val month = when (dateComponents[1]) {
            "01" -> "Jan"; "02" -> "Feb"; "03" -> "Mar"; "04" -> "Apr"
            "05" -> "May"; "06" -> "Jun"; "07" -> "Jul"; "08" -> "Aug"
            "09" -> "Sep"; "10" -> "Oct"; "11" -> "Nov"; "12" -> "Dec"
            else -> dateComponents[1]
        }
        val day = dateComponents[2]

        val timeComponents = timePart.split(":")
        val hour = timeComponents[0]
        val minute = timeComponents[1]

        "$month $day, $year at ${hour.toInt()}:$minute" // "Dec 15, 2024 at 14:30"
    } catch (e: Exception) {
        "Invalid date"
    }
}

@OptIn(ExperimentalTime::class)
@Preview
@Composable
fun PostDetailsView(
    title: String,
    description: String,
    labels: List<String>,
    upVote: Int,
    downVote: Int,
    createDate: Instant,
    navController: NavHostController
    ) {
        Card(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp,
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                // Image Section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(top = 40.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.compose_multiplatform), // Your image resource
                        contentDescription = "Header image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(all = 16.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    var expanded = remember { mutableStateOf(false) }
                    var isOverflowing = remember { mutableStateOf(false) }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { if (isOverflowing.value) expanded.value = !expanded.value }
                    ) {
                        Text(
                            text = title,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = if (expanded.value) Int.MAX_VALUE else 5,
                            overflow = TextOverflow.Ellipsis,
                            onTextLayout = { textLayoutResult ->
                                isOverflowing.value = textLayoutResult.hasVisualOverflow
                            }
                        )

                        // Show clickable dots when text overflows
                        if (isOverflowing.value && (!expanded.value)) {
                            Text(
                                text = "...",
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .clickable { expanded.value = true }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Voting section
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.arrow_upward_24px),
                            contentDescription = "Arrow up",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = upVote.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Icon(
                            painter = painterResource(Res.drawable.arrow_downward_24px),
                            contentDescription = "Arrow down",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = downVote.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Row {
                    Icon(
                        painter = painterResource(Res.drawable.nest_clock_farsight_analog_24px),
                        contentDescription = "Clock",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Text(
                        text = formatTimestamp(createDate.toString()),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    )
                }
                
                // Content Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Subtitle
                    Text(
                        text = "Lables",
                        style = MaterialTheme.typography.titleMedium,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Label Row
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(horizontal = 16.dp)
                            .padding(bottom = 16.dp)
                    ) {
                        labels.forEach { label ->
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colorScheme.primaryContainer,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = label,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }

                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleMedium,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    }

                }
            }
}


