import androidx.compose.ui.window.ComposeUIViewController
import com.nezihyilmaz.multiplatform.particles.ui.composable.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController { App() }
}
