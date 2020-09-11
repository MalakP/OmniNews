package digitalfish.omninews.data

import android.util.Log
import digitalfish.omninews.data.model.News
import digitalfish.omninews.data.network.ApiResult
import digitalfish.omninews.data.network.NewsApi

class DataRepository(private val service:NewsApi) {

    var newsStored:News? = null

    suspend fun searchNews(query:String): ApiResult<News> {
        return try {
            val response =  service.getNewsAsync(query).await()
             if(response.isSuccessful){
                 response.body()?.let {
                     newsStored = it
                     ApiResult.Success(it)
                 }?:run{
                     ApiResult.Success(News(emptyList(), emptyList()))
                 }

             }else{
                 Log.e("API service error: ", "Retrofit error: ${response.errorBody()}")
                 ApiResult.Error("API service error: Retrofit error: ${response.errorBody()}")
             }

         }catch (e: Exception){
             Log.e("API service error: ", "general error: $e")
             ApiResult.Error("API service error: general error: $e")
         }
    }
}