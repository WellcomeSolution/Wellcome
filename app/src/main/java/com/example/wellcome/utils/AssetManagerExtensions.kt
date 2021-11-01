package com.example.wellcome.utils

import android.content.res.AssetManager


fun AssetManager.readFile(fileName: String) = open(fileName)
    .bufferedReader()
    .use { it.readText() }

fun AssetManager.getStream(fileName: String) = open(fileName)
