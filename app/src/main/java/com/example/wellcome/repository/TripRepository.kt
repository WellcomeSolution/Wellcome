package com.example.wellcome.repository

import com.example.services.HostDetails
import com.example.services.HostPresenter
import com.example.services.TripPattern
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import org.apache.http.client.utils.URIBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor

class TripRepository(private val executor: Executor,
                     private val responseParser: TripResponseParser) {
    private val presentersFilterUrl = "http://10.0.2.2:5229/api/hosts/presenters/filter"
    private val hostDetailsUrl = "http://10.0.2.2:5229/api/hosts/details"

    fun getHostPresenters(pattern:TripPattern,
                          callback:(Result<ArrayList<HostPresenter>>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeHostPresentersRequest(pattern)
                callback(response)
            }
            catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }

    fun getHostDetails(id:Int,
                          callback:(Result<HostDetails>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeHostDetailsRequest(id)
                callback(response)
            }
            catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }

    private fun makeHostDetailsRequest(id:Int) : Result<HostDetails>{
        val uri =  URIBuilder("${hostDetailsUrl}/$id")
        val url = URL(uri.toString())
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            Thread.sleep(2000)
            return Result.Success(responseParser.parseToHostDetails(inputStream))
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    private fun makeHostPresentersRequest(pattern:TripPattern) : Result<ArrayList<HostPresenter>>{
        val uri =  URIBuilder(presentersFilterUrl)
        val url = URL(uri.toString())
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = true
            Json.encodeToStream(pattern, outputStream)
            Thread.sleep(2000)
            return Result.Success(responseParser.parseToHostPresenters(inputStream))
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }
}