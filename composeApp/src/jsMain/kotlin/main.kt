import com.nezihyilmaz.multiplatform.particles.ui.composable.App
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow("Multiplatform-Particles") {
            App()
        }
    }
}
