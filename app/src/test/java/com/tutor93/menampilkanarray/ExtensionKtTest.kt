package com.tutor93.menampilkanarray

import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat

class ExtensionKtTest{
    @Test
    fun testToSimpleString() {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.parse("02/28/2018")
        assertEquals("Wed, 28 Feb 2018", toSimpleString(date))
    }

    @Test
    fun dateFormated(){
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.parse("02/28/2018")
        assertEquals("Wed, 28 Feb 2018", date.formated())
    }


}