package com.example.wellcome.repository

import android.R.attr
import android.graphics.Bitmap
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import org.apache.http.client.utils.URIBuilder
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor
import android.R.attr.bitmap
import com.example.services.*


class TripRepository(private val executor: Executor,
                     private val responseParser: TripResponseParser) {
    private val baseUrl = "http://192.168.1.12:5229/api/hosts"
    private val presentersFilterUrl = "$baseUrl/presenters/filter"
    private val hostDetailsUrl = "/details"
    private val favoriteUrl = "$baseUrl/favorite"
    private val uploadImageUrl = "$baseUrl/image"

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

    fun removeFavoriteHost(request:FavoriteRequest,
                        callback:(Result<Boolean>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeRemoveFavoriteRequest(request)
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

    fun uploadImage(bitmap:Bitmap,
                        callback:(Result<FileUploadResult>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeUploadImageRequest(bitmap)
                callback(response)
            }
            catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }

    fun createHost(request:HostRequest,
                    callback:(Result<HostPresenter>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeCreateHostRequest(request)
                callback(response)
            }
            catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }

    private fun makeCreateHostRequest(request:HostRequest) : Result<HostPresenter>{
        val url = URL(baseUrl)
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json")
            setRequestProperty("Accept", "application/json")
            doOutput = true
            Json.encodeToStream(request, outputStream)
            return Result.Success(responseParser.parseToHostPresenter(inputStream))
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    private fun makeUploadImageRequest(bitmap: Bitmap) : Result<FileUploadResult>{
        val attachmentName = "File"
        val attachmentFileName = "bitmap.bmp"
        val crlf = "\r\n"
        val twoHyphens = "--"
        val boundary = "*****"

        val url = URL(uploadImageUrl)
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "POST"
            setRequestProperty(
                "Content-Type", "multipart/form-data;boundary=" + boundary);
            doOutput = true
            val request = DataOutputStream(this.outputStream)
            request.writeBytes(twoHyphens + boundary + crlf)
            request.writeBytes("Content-Disposition: form-data; name=\"" +
                    attachmentName + "\";filename=\"" +
                    attachmentFileName + "\"" + crlf)
            request.writeBytes(crlf)
            val pixels = ByteArray(bitmap.width * bitmap.height)
            for (i in 0 until bitmap.width) {
                for (j in 0 until bitmap.height) {
                    pixels[i + j] = ((bitmap.getPixel(i, j) and 0x80 shr 7).toByte())
                }
            }
            request.write(pixels)
            request.writeBytes(crlf)
            request.writeBytes(twoHyphens + boundary +
                    twoHyphens + crlf)
            request.flush()
            request.close()
            return Result.Success(responseParser.parseToFileUploadResult(inputStream))
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    private fun makeRemoveFavoriteRequest(request:FavoriteRequest) : Result<Boolean>{
        val url = URL(favoriteUrl)
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "DELETE"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = true
            Json.encodeToStream(request, outputStream)
            return Result.SuccessNoContent(true)
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    private fun makeSetFavoriteRequest(request:FavoriteRequest) : Result<Nothing>{
        val url = URL(favoriteUrl)
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = true
            Json.encodeToStream(request, outputStream)
            return Result.SuccessNoContent(true)
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    private fun makeHostDetailsRequest(uuid:String) : Result<HostDetails>{
        val uri =  URIBuilder("/$uuid${hostDetailsUrl}")
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