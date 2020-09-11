package digitalfish.omninews.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import digitalfish.omninews.data.DataRepository
import digitalfish.omninews.data.model.News
import digitalfish.omninews.data.network.ApiResult
import kotlinx.coroutines.launch

class MainViewModel(private val dataRepository: DataRepository): ViewModel() {

    val loadingIndicatorVisibilityLive = MutableLiveData<Int>()
    val newsLive = MutableLiveData<News>()
    val errorMessageLive = MutableLiveData<String>()

    init {
        loadingIndicatorVisibilityLive.value = View.GONE
    }

     fun loadData(query:String){
        viewModelScope.launch {
            loadingIndicatorVisibilityLive.value = View.VISIBLE
            val apiResult = dataRepository.searchNews(query)
            if(apiResult is ApiResult.Success) {
                newsLive.value = apiResult.data
            }else{
                errorMessageLive.value = apiResult.getMessage()
            }
            loadingIndicatorVisibilityLive.value = View.GONE
        }
    }

}