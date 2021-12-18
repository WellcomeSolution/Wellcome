package com.example.wellcome.repository

import com.example.services.Host
import com.example.services.TripPattern
import com.example.wellcome.com.example.wellcome.repository.CityResponseParser
import com.example.wellcome.com.example.wellcome.repository.TripResponseParser
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import org.apache.http.client.utils.URIBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor

class TripRepository(private val executor: Executor,
                     private val responseParser: TripResponseParser) {
    private val url = "https://localhost:7229/api/hosts/filter"

    fun getHosts(pattern:TripPattern,
                          callback:(Result<ArrayList<Host>>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeHostsRequest(pattern)
                callback(response)
            }
            catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }

    private fun makeHostsRequest(pattern:TripPattern) : Result<ArrayList<Host>>{
        val uri =  URIBuilder(url)
        val url = URL(uri.toString())
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = true
            Json.encodeToStream(pattern, outputStream)

            return Result.Success(responseParser.parse(inputStream))
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }
}