package com.example.wellcome.repository

import com.example.services.FavoriteRequest
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
    private val baseUrl = "http://10.0.2.2:5229/api/hosts"
    private val presentersFilterUrl = "$baseUrl/presenters/filter"
    private val hostDetailsUrl = "$baseUrl/details"
    private val hostFavoriteUrl = "$baseUrl/favorite"

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

    fun getHostDetails(uuid: String,
                          callback:(Result<HostDetails>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeHostDetailsRequest(uuid)
                callback(response)
            }
            catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }

    fun setFavoriteHost(request:FavoriteRequest,
                       callback:(Result<Boolean>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeSetFavoriteRequest(request)
                callback(response)
            }
            catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }

    private fun makeRemoveFavoriteRequest(request:FavoriteRequest) : Result<Boolean>{
        val url = URL(hostFavoriteUrl)
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = true
            Json.encodeToStream(request, outputStream)
            Thread.sleep(2000)
            return Result.Success(true)
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    private fun makeSetFavoriteRequest(request:FavoriteRequest) : Result<Boolean>{
        val url = URL(hostFavoriteUrl)
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = true
            Json.encodeToStream(request, outputStream)
            Thread.sleep(2000)
            return Result.Success(true)
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    private fun makeHostDetailsRequest(uuid:String) : Result<HostDetails>{
        val uri =  URIBuilder("${hostDetailsUrl}/$uuid")
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