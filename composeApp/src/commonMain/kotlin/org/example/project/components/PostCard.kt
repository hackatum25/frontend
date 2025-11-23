package org.example.project.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavHostController
import org.example.project.generated.Res
import org.example.project.generated.arrow_downward_24px
import org.example.project.generated.arrow_upward_24px
import org.example.project.generated.badge_official
import org.example.project.generated.history_24px
import org.example.project.generated.person_24px
import org.example.project.generated.swap_vert_24px
import org.example.project.generated.verified_24px
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun durationSince(time: Instant, now: Instant = Clock.System.now()): Duration {
    return time - now
}

// Format Duration to short string
fun formatAge(time: Duration): String {
    val duration: Duration = if (time.isNegative()) -time else time

    return when {
        duration >= 365.days -> {
            val years = (duration / 365.days).toInt()
            "${years}y"
        }
        duration >= 30.days -> {
            val months = (duration / 30.days).toInt()
            "${months}mo"
        }
        duration >= 1.days -> {
            val days = duration.inWholeDays
            "${days}d"
        }
        duration >= 1.hours -> {
            val hours = duration.inWholeHours
            "${hours}h"
        }
        duration >= 1.minutes -> {
            val minutes = duration.inWholeMinutes
            "${minutes}m"
        }
        duration >= 1.seconds -> {
            val seconds = duration.inWholeSeconds
            "${seconds}s"
        }
        else -> "just now"
    }
}

@OptIn(ExperimentalTime::class)
@Composable
@Preview
fun PostCard(
    modifier: Modifier = Modifier,
    avatar: Painter = painterResource(Res.drawable.person_24px),
    title: String, description: String, username: String,
    isOfficial: Boolean = false,
    mainImage: Painter? = null,
    votesCount: Int = 420,
    createdAt: Instant,
    voteState: VoteState = VoteState.NONE,
    onUpClick: (() -> Unit) = {},
    onDownClick: (() -> Unit) = {},
    onCardClick: (() -> Unit),
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    ) {
        Column(modifier = Modifier.clickable(onClick = { onCardClick()})) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Avatar(avatar = avatar)
                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = username,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f) // lets text take remaining space
                )

                Spacer(modifier = Modifier.width(8.dp))

                if(isOfficial){
                    SuggestionChip(
                        onClick = { /*...*/ },
                        label = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(Res.drawable.verified_24px),
                                    contentDescription = "verified icon",
                                    modifier = Modifier.size(18.dp), // adjust to match text height
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(stringResource(Res.string.badge_official))
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .heightIn(min = 32.dp)
                    )
                }
            }
            if(mainImage!=null){
                Image(
                    painter = mainImage,
                    contentDescription = null, // decorative
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth().padding(bottom = 16.dp)
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth().padding(bottom =16.dp, start = 16.dp, end = 16.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )


            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left side: icons and texts
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(Res.drawable.swap_vert_24px),
                        contentDescription = "",
                        tint = LocalContentColor.current
                    )
                    Text(
                        text = votesCount.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(Res.drawable.history_24px),
                        contentDescription = "",
                        tint = LocalContentColor.current
                    )
                    Text(
                        text = formatAge(durationSince(createdAt)),
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Right side: up/down buttons
                Row {
                    IconButton(
                        onClick = onUpClick,
                        modifier = Modifier.size(40.dp),
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.arrow_upward_24px),
                            contentDescription = "Arrow up",
                            tint = if (voteState == VoteState.UP) MaterialTheme.colorScheme.primary else LocalContentColor.current
                        )
                    }

                    IconButton(
                        onClick = onDownClick,
                        modifier = Modifier.size(40.dp),
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.arrow_downward_24px),
                            contentDescription = "Arrow down",
                            tint = if (voteState == VoteState.DOWN) MaterialTheme.colorScheme.primary else LocalContentColor.current
                        )
                    }
                }
            }
        }
    }
}
