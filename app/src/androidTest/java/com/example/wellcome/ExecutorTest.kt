package com.example.wellcome

import java.util.concurrent.Executor

class ExecutorTest : Executor {
    override fun execute(r: Runnable) {
        r.run()
    }
}