package com.nezihyilmaz.multiplatform.particles.ui.modifier

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.nezihyilmaz.multiplatform.particles.controller.Engine

fun Modifier.handleGestures(engine: Engine): Modifier {
    val modifier = pointerInput(Unit) {
        detectTapGestures(onPress = { offset ->
            engine.createForceField(offset.x, offset.y)
        }) {
            engine.removeForceField()
        }

    }.pointerInput(Unit) {
        detectDragGestures(onDragStart = {

        }, onDragEnd = {
            engine.removeForceField()
        }, onDragCancel = {}) { change, dragAmount ->
            change.consume()
            dragAmount.x
            dragAmount.y
            engine.moveForceFieldBy(
                dragAmount.x, dragAmount.y
            )
        }
    }
    return modifier
}