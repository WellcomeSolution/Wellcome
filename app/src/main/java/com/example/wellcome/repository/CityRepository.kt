package com.example.wellcome.repository

import com.example.wellcome.com.example.wellcome.repository.CityResponseParser
import org.apache.http.client.utils.URIBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor

class CityRepository(private val executor: Executor,
                    private val responseParser: CityResponseParser) {
    private val url = "https://nominatim.openstreetmap.org/search"

    fun getCities(prefix:String,
                          callback:(Result<ArrayList<Address>>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeCitiesRequest(prefix)
                callback(response)
            }
            catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }

    private fun makeCitiesRequest(prefix:String) : Result<ArrayList<Address>>{
        val uri =  URIBuilder(url)
            .addParameter("city", prefix)
            .addParameter("format", "json")
            .addParameter("addressdetails", "[0]").build()

        val url = URL(uri.toString())
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")

            return Result.Success(responseParser.parse(inputStream))
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }
}