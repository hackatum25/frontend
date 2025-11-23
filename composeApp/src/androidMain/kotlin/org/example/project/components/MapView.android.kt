package org.example.project.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.viewinterop.AndroidView
import org.maplibre.android.MapLibre
import org.maplibre.android.annotations.MarkerOptions
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapView

@Composable
actual fun MapView(modifier: Modifier) {
	val markers = listOf(
        MarkerOptions().position(LatLng(48.1728, 11.5533)).title("Olympiastadion / Olympiapark").snippet("Olympic grounds, tower, park"),
        MarkerOptions().position(LatLng(48.0942, 11.4625)).title("Martinsried (U-Bahn area)").snippet("Martinsried research district / U-Bahn access"),
        MarkerOptions().position(LatLng(48.1599, 11.5870)).title("Eisbachwelle").snippet("Famous standing wave for surfers"),
        MarkerOptions().position(LatLng(48.1038, 11.5698)).title("Isar Cycling / Flaucher").snippet("Popular cycling and recreational route"),
)


    AndroidView(
        modifier = modifier.background(color = Color.Blue, shape = RectangleShape),
        factory = { context: Context ->
            MapLibre.getInstance(context)
            val mapView = MapView(context)
            val styleUrl = "https://files.ghoscht.com/open-street-map/style.json"
            mapView.onCreate(null)
            mapView.getMapAsync { map ->
                map.setStyle(styleUrl) {
                    map.uiSettings.setAttributionMargins(15, 0, 0, 15)
                    map.cameraPosition = CameraPosition.Builder()
                        .target(LatLng(48.137154, 11.576124))
                        .zoom(10.0)
                        .build()
                }

					for (opt in markers) {
					    map.addMarker(opt)
					}
            }
            mapView
        }
    )
}
