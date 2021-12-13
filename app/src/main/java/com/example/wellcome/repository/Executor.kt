package com.example.wellcome.repository

import java.util.concurrent.Executor

class Executor : Executor {
    override fun execute(r: Runnable?) {
        Thread(r).start()
    }
}