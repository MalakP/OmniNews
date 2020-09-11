package digitalfish.omninews.data.network

const val BASE_URL = "https://omni-content.omni.news/"
class ApiFactory() {
    fun getNewsApi():NewsApi{
        return RetrofitFactory.retrofit(BASE_URL, RetrofitFactory.okHttpClient).create(NewsApi::class.java)
    }
}