package com.example.wellcome

import com.example.wellcome.com.example.wellcome.repository.CityResponseParser
import com.example.wellcome.repository.*
import org.junit.Assert
import org.junit.Test

class CityRepositoryTest {
    private val cityRepository : CityRepository
        = CityRepository(Executor(), CityResponseParser())

    @Test
    fun getCitiesTest(){
        var data = ArrayList<City>()
        val r = cityRepository.getCities("Paris") { result ->
            when(result){
                is Result.Success<CityResponse> ->  data = result.data.data
            }
        }
        Assert.assertNotEquals(data.size, 0)
    }
}