package org.example.project.components

import org.example.project.generated.Res
import org.example.project.generated.celebration_24px
import org.example.project.generated.euro_24px
import org.example.project.generated.filter_environment
import org.example.project.generated.filter_events
import org.example.project.generated.filter_finances
import org.example.project.generated.filter_official
import org.example.project.generated.filter_transport
import org.example.project.generated.forest_24px
import org.example.project.generated.transportation_24px
import org.example.project.generated.verified_24px
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class FilterItem(
    val name: String,
    var title: StringResource,
    var icon: DrawableResource
) {
    object Environment : FilterItem("environment", Res.string.filter_environment, Res.drawable.forest_24px)
    object Transport : FilterItem("transport", Res.string.filter_transport, Res.drawable.transportation_24px)
    object Official : FilterItem("official", Res.string.filter_official, Res.drawable.verified_24px)
    object Finances : FilterItem("finances", Res.string.filter_finances, Res.drawable.euro_24px)
    object Events : FilterItem("events", Res.string.filter_events, Res.drawable.celebration_24px)
}
