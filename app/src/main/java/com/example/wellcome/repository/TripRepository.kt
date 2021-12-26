package com.example.wellcome.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.services.Host
import com.example.services.HostPresenter
import com.example.services.TripPattern
import com.google.android.gms.common.util.IOUtils
import com.google.gson.JsonParser
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import org.apache.http.client.utils.URIBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.Arrays.toString
import java.util.concurrent.Executor
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class TripRepository(private val executor: Executor,
                     private val responseParser: TripResponseParser) {
    private val presentersFilter = "http://10.0.2.2:5229/api/hosts/presenters/filter"

    fun getHosts(pattern:TripPattern,
                          callback:(Result<ArrayList<HostPresenter>>) -> Unit
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

    /*fun getHostPicture(id: Int,
                 callback:(Result<Bitmap>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeHostPictureRequest(id)
                callback(response)
            }
            catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }*/

    private fun makeHostsRequest(pattern:TripPattern) : Result<ArrayList<HostPresenter>>{
        val uri =  URIBuilder(presentersFilter)
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

    /*private fun makeHostPictureRequest(id:Int) : Result<Bitmap>{
        val uri =  URIBuilder("${hostPicture}/${id}")
        val url = URL(uri.toString())
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            return Result.Success(responseParser.parseToBitmap(inputStream))
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }*/
}