package com.example.wellcome.repository

import com.example.wellcome.com.example.wellcome.repository.CityResponseParser
import org.apache.http.client.utils.URIBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor

class CityRepository(private val executor: Executor,
                    private val responseParser: CityResponseParser) {
    private val url = "http://geodb-free-service.wirefreethought.com"
    private val cityEndpoint = "/v1/geo/cities"

    fun getCities(prefix:String,
                          callback:(Result<CityResponse>) -> Unit
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

    private fun makeCitiesRequest(prefix:String) : Result<CityResponse>{
        val uri =  URIBuilder("${url}${cityEndpoint}")
            .addParameter("namePrefix", prefix).build()

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