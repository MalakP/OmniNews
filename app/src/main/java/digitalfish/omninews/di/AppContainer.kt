package digitalfish.omninews.di

import digitalfish.omninews.data.DataRepository
import digitalfish.omninews.data.network.NewsApi
import digitalfish.omninews.data.network.RetrofitFactory
import digitalfish.omninews.viewmodel.DetailsViewModel
import digitalfish.omninews.viewmodel.MainViewModel

const val BASE_URL = "https://omni-content.omni.news/"

class AppContainer {
    private val newsApi = RetrofitFactory().getRetrofitForApi(BASE_URL).create(
        NewsApi::class.java
    )

    private val dataRepository = DataRepository(newsApi)
    val mainViewModel = MainViewModelFactory(dataRepository)
    val detailsViewModel = DetailsViewModelFactory(dataRepository)

    interface Factory<T> {
        fun create(): T
    }

    class MainViewModelFactory(private val dataRepository: DataRepository) :
        Factory<MainViewModel> {
        override fun create(): MainViewModel {
            return MainViewModel(dataRepository)
        }
    }

    class DetailsViewModelFactory(private val dataRepository: DataRepository) :
        Factory<DetailsViewModel> {
        override fun create(): DetailsViewModel {
            return DetailsViewModel(dataRepository)
        }
    }

}