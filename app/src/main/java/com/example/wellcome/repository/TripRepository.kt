package com.example.wellcome.repository

import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import org.apache.http.client.utils.URIBuilder
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor
import com.example.services.*


class TripRepository(private val executor: Executor,
                     private val responseParser: TripResponseParser) {
    private val baseUrl = "http://192.168.1.81:5229/api/hosts"
    private val presentersFilterUrl = "$baseUrl/presenters/filter"
    private val hostDetailsUrl = "/details"
    private val AddRemoveFavoriteUrl = "$baseUrl/favorite"
    private val uploadImageUrl = "$baseUrl/image"
    private val tag = "test"
    fun getHostPresenters(pattern:TripPattern,
                          callback:(Result<ArrayList<HostPresenter>>) -> Unit
    ){
        executor.execute{
            try {
                Log.w(tag,pattern.toString())
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

    fun getFavorites(email:String,
                   callback:(Result<ArrayList<HostPresenter>>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeFavoritesRequest(email)

                callback(response)
                Log.w(tag,email)
                Log.w(tag,response.toString())
            }
           catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }

    fun sendHostReservation(dto:HostReservationDto,
                            callback:(Result<HostReservationDto>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeHostReservationRequest(dto)
                callback(response)
            }
            catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }

    fun getReservations(email: String,
                            callback:(Result<ArrayList<HostReservationPresenterDto>>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeGetReservationsRequest(email)
                callback(response)
            }
            catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }

    fun acceptReservation(uuid: String,
                          callback:(Result<Boolean>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeAcceptReservationRequest(uuid)
                callback(response)
            }
            catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }

    fun deleteReservation(uuid: String,
                        callback:(Result<Boolean>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeRemoveReservationRequest(uuid)
                callback(response)
            }
            catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }

    fun getIncomingTrips(email: String,
                          callback:(Result<ArrayList<IncomingTripDto>>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeGetIncomingTripRequest(email)
                callback(response)
            }
            catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }

    private fun makeGetIncomingTripRequest(email: String) : Result<ArrayList<IncomingTripDto>>{
        val uri =  URIBuilder("$baseUrl/${email}/reservation/incoming")
        val url = URL(uri.toString())
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            return Result.Success(responseParser.parseToIncomingTrip(inputStream))
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    private fun makeHostReservationRequest(dto: HostReservationDto) : Result<HostReservationDto>{
        val uri =  URIBuilder("$baseUrl/reservation")
        val url = URL(uri.toString())
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = true
            Json.encodeToStream(dto, outputStream)
            return Result.Success(responseParser.parseToHostRequest(inputStream))
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    private fun makeFavoritesRequest(email:String) : Result<ArrayList<HostPresenter>>{
        val uri =  URIBuilder("$baseUrl/$email/favorites")
        val url = URL(uri.toString())
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            return Result.Success(responseParser.parseToHostPresenters(inputStream))
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
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

    private fun makeAcceptReservationRequest(uuid: String) : Result<Boolean>{
        val url = URL("$baseUrl/$uuid/reservation/accept")
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "PUT"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            return Result.SuccessNoContent(this.responseCode)
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    private fun makeRemoveReservationRequest(uuid: String) : Result<Boolean>{
        val url = URL("$baseUrl/$uuid/reservation/delete")
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "DELETE"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            return Result.SuccessNoContent(this.responseCode)
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    private fun makeGetReservationsRequest(email:String) : Result<ArrayList<HostReservationPresenterDto>>{
        val url = URL("$baseUrl/$email/reservation")
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            return Result.Success(responseParser.parseToHostReservationPresenter(inputStream))
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    private fun makeRemoveFavoriteRequest(request:FavoriteRequest) : Result<Boolean>{
        val url = URL(AddRemoveFavoriteUrl)
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "DELETE"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = true
            Json.encodeToStream(request, outputStream)
            return Result.SuccessNoContent(this.responseCode)
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    private fun makeSetFavoriteRequest(request:FavoriteRequest) : Result<Nothing>{
        val url = URL(AddRemoveFavoriteUrl)
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = true
            Json.encodeToStream(request, outputStream)
            return Result.SuccessNoContent(this.responseCode)
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    private fun makeHostDetailsRequest(uuid:String) : Result<HostDetails>{
        val uri =  URIBuilder("$baseUrl/$uuid${hostDetailsUrl}")
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
            //Thread.sleep(2000)
            return Result.Success(responseParser.parseToHostPresenters(inputStream))
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }
}