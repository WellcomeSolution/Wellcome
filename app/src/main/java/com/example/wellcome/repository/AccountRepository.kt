package com.example.wellcome.repository

import com.example.services.AccountDto
import com.example.services.HostReservationDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import org.apache.http.client.utils.URIBuilder
import java.net.HttpURLConnection
import java.net.URL

class AccountRepository(
    private val executor:Executor,
    private val responseParser:AccountResponseParser) {
    private val baseUrl = "http://192.168.1.81:5229/api/account"

    fun updateAccount(dto:AccountDto,
                        callback:(Result<String>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeUpdateAccountRequest(dto)
                callback(response)
            }
            catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }

    fun registerAccount(dto:AccountDto,
                            callback:(Result<String>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeRegisterAccountRequest(dto)
                callback(response)
            }
            catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }

    fun logIn(dto:AccountDto,
                        callback:(Result<AccountDto>) -> Unit
    ){
        executor.execute{
            try {
                val response = makeLogInRequest(dto)
                callback(response)
            }
            catch (e: java.lang.Exception){
                val errorResult = Result.Error(e)
                callback(errorResult)
            }
        }
    }

    private fun makeRegisterAccountRequest(dto: AccountDto) : Result<String>{
        val uri =  URIBuilder("$baseUrl/register")
        val url = URL(uri.toString())
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = true
            Json.encodeToStream(dto, outputStream)
            return Result.Success(responseParser.parseToResponseMessage(inputStream))
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    private fun makeLogInRequest(dto: AccountDto) : Result<AccountDto>{
        val uri =  URIBuilder("$baseUrl/login")
        val url = URL(uri.toString())
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = true
            Thread.sleep(2000)
            Json.encodeToStream(dto, outputStream)
            return Result.Success(responseParser.parseToAccount(inputStream))
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    private fun makeUpdateAccountRequest(dto: AccountDto) : Result<String>{
        val uri =  URIBuilder("$baseUrl")
        val url = URL(uri.toString())
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "PUT"
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = true
            Json.encodeToStream(dto, outputStream)
            return Result.Success(responseParser.parseToResponseMessage(inputStream))
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }
}