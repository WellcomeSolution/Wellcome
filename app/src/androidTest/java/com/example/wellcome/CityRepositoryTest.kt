package com.example.wellcome

import com.example.wellcome.com.example.wellcome.repository.CityResponseParser
import com.example.wellcome.repository.*
import org.junit.Assert
import org.junit.Test

class CityRepositoryTest {
    private val cityRepository : CityRepository
        = CityRepository(ExecutorTest(), CityResponseParser())

    @Test
    fun getCitiesTest(){
        var data = ArrayList<Cities>()
        val r = cityRepository.getCities("soisy-Bouy") { result ->
            when(result){
                is Result.Success<ArrayList<Cities>> ->  data = result.data
            }
        }
        Assert.assertFalse(data.isEmpty())
    }
}