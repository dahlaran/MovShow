package com.dahlaran.movshow

import org.junit.Assert.assertEquals
import org.junit.Test
import com.dahlaran.movshow.utils.HtmlUtils

class HtmlUtilsTest {
    @Test
    fun convertHtmlTextToShowText() {
        val textString = "19QdD"

        assertEquals(textString, HtmlUtils.convertHtmlTextToShowText(textString).toString())
    }

    @Test
    fun convertHtmlTextToShowTextSimple() {
        val textString = "19QdD"

        assertEquals(textString, HtmlUtils.convertHtmlTextToShowText(textString).toString())
    }
}