package digitalfish.omninews

import android.app.Application
import digitalfish.omninews.di.AppContainer

class NewsApplication: Application() {
    val appContainer = AppContainer()
}