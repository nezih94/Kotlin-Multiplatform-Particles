package com.nezihyilmaz.multiplatform.particles

import androidx.compose.ui.graphics.Color
import com.nezihyilmaz.multiplatform.particles.physics.ParticleProperties
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

data class FormattedSVGResource(
    val viewportWidth: Float,
    val viewportHeight: Float,
    val data: String,
    val additionalOffsetX: Float = 0f,
    val additionalOffsetY: Float = 0f,
    val particleProperties: ParticleProperties? = null
) {
    companion object {
        val ANDROID_ICON_AND_TEXT = FormattedSVGResource(
            60f,
            60f,
            SVG_COORD_ANDROID_LOGO_AND_TEXT,
            particleProperties = ParticleProperties(color = Color(28,121,33), radius = 6f)
        )
        val IOS_ICON_AND_TEXT = FormattedSVGResource(
            60f,
            60f,
            SVG_COORDS_IOS_LOGO_AND_TEXT,
            particleProperties = ParticleProperties(color = Color.Black, radius = 6f)
        )

        val TEXT_PLAY = FormattedSVGResource(
            60f,
            60f,
            SVG_COORDS_TEXT_PLAY,
            particleProperties = ParticleProperties(color = Color.Magenta, radius = 6f)
        )
    }
}

expect val DEFAULT_RESOURCE : FormattedSVGResource

fun getUIData(
    canvasWidth: Float,
    canvasHeight: Float,
    formattedSVGResource: FormattedSVGResource = DEFAULT_RESOURCE
): UIData {
    val data = Json.decodeFromString<List<List<Float>>>(formattedSVGResource.data)
    val resourceWidth = formattedSVGResource.viewportWidth
    val resourceHeight = formattedSVGResource.viewportHeight
    val canvasAspectRatio = canvasWidth / canvasHeight
    val resourceAspectRatio = resourceWidth / resourceHeight

    val scaleX: Float
    val scaleY: Float

    val paddingRatio = 0.1f

    if (resourceAspectRatio > canvasAspectRatio) {
        scaleX = (canvasWidth / resourceWidth) * (1 - paddingRatio)
        scaleY = scaleX
    } else {
        scaleY = (canvasHeight / resourceHeight) * (1 - paddingRatio)
        scaleX = scaleY
    }

    val offsetX =
        (canvasWidth - resourceWidth * scaleX) / 2 + formattedSVGResource.additionalOffsetX
    val offsetY =
        ((canvasHeight - resourceHeight * scaleY) / 2) + formattedSVGResource.additionalOffsetY
    val mappedList = data.map {
        val x = it[0]
        val y = it[1]
        val transformedX = x * scaleX + offsetX
        val transformedY = y * scaleY + offsetY
        Pair(transformedX, transformedY)
    }
    return UIData(mappedList, formattedSVGResource.particleProperties)
}

data class UIData(
    val coordinates: List<Pair<Float, Float>>,
    val particleProperties: ParticleProperties?
)