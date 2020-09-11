package digitalfish.omninews.di

import digitalfish.omninews.data.DataRepository
import digitalfish.omninews.data.network.ApiFactory
import digitalfish.omninews.viewmodel.DetailsViewModel
import digitalfish.omninews.viewmodel.MainViewModel

class AppContainer {
    private val dataRepository = DataRepository(ApiFactory().getNewsApi())
    val mainViewModel = MainViewModelFactory(dataRepository)
    val detailsViewModel = DetailsViewModelFactory(dataRepository)

    interface Factory<T> {
        fun create(): T
    }


    class MainViewModelFactory(private val dataRepository: DataRepository) : Factory<MainViewModel> {
        override fun create(): MainViewModel {
            return MainViewModel(dataRepository)
        }
    }

    class DetailsViewModelFactory(private val dataRepository: DataRepository) : Factory<DetailsViewModel> {
        override fun create(): DetailsViewModel {
            return DetailsViewModel(dataRepository)
        }
    }

}