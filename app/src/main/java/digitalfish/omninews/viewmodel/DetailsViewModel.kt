package digitalfish.omninews.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import digitalfish.omninews.data.DataRepository
import digitalfish.omninews.extras.Constants.DATA_TYPE_ARTICLE
import digitalfish.omninews.extras.Constants.DATA_TYPE_TOPIC
import kotlinx.coroutines.launch


class DetailsViewModel(private val dataRepository: DataRepository): ViewModel() {

    val detailsLive = MutableLiveData<String>()

    fun loadData(dataType:Int, selectedIdx: Int){
        viewModelScope.launch {
            when(dataType) {
                DATA_TYPE_ARTICLE->{
                    val paragraphs = selectedIdx?.let { dataRepository.newsStored?.articles?.get(it)?.main_text?.paragraphs }
                    detailsLive.value = paragraphs?.filter{it.text?.value!=null}?.map{it.text?.value}?.joinToString (
                        prefix = "\t",
                        separator = "\n\n\t"
                    )
                }
                DATA_TYPE_TOPIC->{
                    val topic = selectedIdx?.let { dataRepository.newsStored?.topics?.get(it) }
                    detailsLive.value = topic?.type
                }
            }
        }
    }
}