package digitalfish.omninews.data.network

import digitalfish.omninews.data.model.News
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("search")
    fun getNewsAsync(@Query("query") query:String): Deferred<Response<News>>
}