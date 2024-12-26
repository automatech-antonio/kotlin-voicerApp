package tech.voicer.voicerapp.infra

import android.app.Activity
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale


class Tts(private val activity: Activity, private val message: String): TextToSpeech.OnInitListener {
    private val tts: TextToSpeech = TextToSpeech(activity, this)

    override fun onInit(i: Int) {
        if (i == TextToSpeech.SUCCESS) {
            val localeBr = Locale("pt", "BR")
            val result = tts.setLanguage(localeBr)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.d("Voicer", "Linguagem não suportada!")
            } else {
                speak(message)
            }

        } else {
            Log.d("Voicer", "Tts não pode ser inicializado!")
        }
    }

    private fun speak(message: String) {
        tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}