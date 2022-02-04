package com.example.cookingassistant

import android.view.View
import org.vosk.android.RecognitionListener
import java.lang.Exception

class CommandListener(next: (View?) -> Unit, prev: (View?) -> Unit) : RecognitionListener {
    val next = next
    val prev = prev

    override fun onPartialResult(hypothesis: String?) {
        //println(hypothesis)
    }

    override fun onResult(hypothesis: String?) {
        val h = hypothesis?.substring(14, hypothesis?.length - 3)
        println(h)
        if (h.equals("next"))
            next(null)
        else if (h.equals("previous") || h.equals("back"))
            prev(null)
    }

    override fun onFinalResult(hypothesis: String?) {
        println(hypothesis)
    }

    override fun onError(exception: Exception?) {
        println("Error: " + exception?.message)
    }

    override fun onTimeout() {
        println("Timed out")
    }

}