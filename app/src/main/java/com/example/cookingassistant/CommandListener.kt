package com.example.cookingassistant

import org.vosk.android.RecognitionListener
import java.lang.Exception

class CommandListener : RecognitionListener {
    override fun onPartialResult(hypothesis: String?) {
        //println(hypothesis)
    }

    override fun onResult(hypothesis: String?) {
        println(hypothesis)
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