package com.tutor93.menampilkanarray.latihan3_footballclub

import kotlinx.coroutines.experimental.Unconfined
import org.junit.Assert.*
import kotlin.coroutines.experimental.CoroutineContext

class CoroutineContextProviderTest: CoroutineContextProvider() {
    override val main: CoroutineContext = Unconfined
}