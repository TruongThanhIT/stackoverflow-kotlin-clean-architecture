package com.thanht.stackoverflow.data.util

import com.thanht.stackoverflow.data.net.ApiConnection

inline fun <reified T : Any> ApiConnection.provideService(): T = retrofit.create(T::class.java)