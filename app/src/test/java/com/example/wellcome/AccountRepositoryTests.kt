package com.example.wellcome

import com.example.services.AccountDto
import com.example.wellcome.repository.AccountRepository
import com.example.wellcome.repository.AccountResponseParser
import com.example.wellcome.repository.Executor
import com.example.wellcome.repository.Result
import org.junit.Assert
import org.junit.Test

class AccountRepositoryTests {
    private val accountRepository = AccountRepository(Executor(), AccountResponseParser())

    /*@Test
    public fun logInTest(){
        val account = AccountDto("jebray@gmail.com", "password")

        var firstName = String()
        accountRepository.logIn(account, "fab78e29-ede0-4d74-8b00-9596c155268e") { result ->
            when(result){
                is Result.Success<AccountDto> -> {
                    firstName = result.data.firstName!!
                }
            }
        }

        Assert.assertEquals("Rayhane", firstName)
    }*/
}