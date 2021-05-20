package org.wikipedia.tts

import android.content.Context
import android.os.Build
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.Log
import com.marytts.android.link.MaryLink
import org.wikipedia.util.log.L
import java.util.*

object TTSManager {
    private var textToSpeech: TextToSpeech? = null

    @JvmStatic
    @Synchronized
    fun setUp(mainActivityContext: Context) {
        textToSpeech = TextToSpeech(mainActivityContext, { status: Int ->
            if (status == TextToSpeech.SUCCESS) {

            }
        }, "com.googlecode.eyesfree.espeak")
    }

    @JvmStatic
    @Synchronized
    fun setUpMary(mainActivityContext: Context) {
        val h = Handler()
        val  r =  Runnable {
            fun run() {

            }
        }
        MaryLink.load(mainActivityContext);
        Log.e("######","PAJ"+(MaryLink.getInstance()==null))
        if (MaryLink.getInstance() == null) {
            h.postDelayed(r, 15000) //wait 15 sec. loading TTS voice models ...
        }
        MaryLink.getInstance().startTTS("This is fabulous")
    }

    @JvmStatic
    @Synchronized
    fun speak(text: String, language: String) {
        Log.e("#####", language + "LANG" + text)
        textToSpeech?.let {
            val supported: Int = it.setLanguage(Locale(language))
            if (supported != TextToSpeech.LANG_AVAILABLE && supported != TextToSpeech.LANG_COUNTRY_AVAILABLE) {
                L.e("current language not supported")
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                it.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
            } else {
                it.speak(text, TextToSpeech.QUEUE_FLUSH, null)
            }
        }

    }

    fun speakMary() {
        val h = Handler()
      val  r =  Runnable {
          fun run() {

          }
      }
        Log.e("######","PAJ"+(MaryLink.getInstance()==null))
        if (MaryLink.getInstance() == null) {
            h.postDelayed(r, 15000) //wait 15 sec. loading TTS voice models ...
        }
         MaryLink.getInstance().startTTS("This is fabulous")

    }
}