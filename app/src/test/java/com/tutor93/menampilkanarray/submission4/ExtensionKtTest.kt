package com.tutor93.menampilkanarray.submission4

import com.tutor93.menampilkanarray.toStringDateFormat
import junit.framework.Assert.assertEquals
import org.junit.Test

class ExtensionKtTest{
    @Test
    fun changeDateStyle(){
        val serverValue = "30/11/2018"
        val serverFormat = "dd/MM/yyyy"
        val formatedTo = "dd MMM yyyy"
        assertEquals("30 Nov 2018", serverValue.toStringDateFormat(serverFormat, formatedTo))
    }
}