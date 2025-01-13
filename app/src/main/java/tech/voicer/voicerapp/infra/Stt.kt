package tech.voicer.voicerapp.infra

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.json.JSONObject
import org.vosk.LibVosk
import org.vosk.LogLevel
import org.vosk.Recognizer
import org.vosk.android.RecognitionListener
import org.vosk.android.SpeechService
import org.vosk.android.StorageService
import tech.voicer.voicerapp.infra.stt.Resolver
import tech.voicer.voicerapp.infra.stt.VerbalGrammar
import kotlin.Exception

private const val PERMISSIONS_REQUEST_RECORD_AUDIO: Int = 1

class Stt(private var activity: Activity) : RecognitionListener {

  private lateinit var speechService: SpeechService
  private var resolver = Resolver()

  init {
    LibVosk.setLogLevel(LogLevel.INFO)
    // Check if user has given permission to record audio, init the model after permission is granted
    val permissionCheck =
      ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO)
    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(
        activity,
        arrayOf(Manifest.permission.RECORD_AUDIO),
        PERMISSIONS_REQUEST_RECORD_AUDIO
      )
    } else {
      initModel()
    }
  }

  private fun initModel() {
    StorageService.unpack(
      activity,
      "model-pt-br",
      "model",
      { mdl ->
        val gr = VerbalGrammar.getInstance()
        val rec = Recognizer(mdl, 16000.0f, gr.getGrammar())
        speechService = SpeechService(rec, 16000.0f)
        speechService.startListening(this)
        Log.d("Vosk - Prepare", "initModel: OK")
      },
      { ex: Exception -> Log.e("STT", "${ex.message} - ${ex.stackTraceToString()}") }
    )
  }

  private fun formatResult(data: String): String {
    return JSONObject(data).getString("text")
  }


  fun setGrammar(gr: String) {
    this.setGrammar(gr)
  }


  override fun onPartialResult(hypothesis: String?) {
    // TODO: Apenas para os casos em que se queira fazer algum debug.
  }

  override fun onResult(hypothesis: String?) {
    Log.d("Vosk - Result", hypothesis.toString())
    val value = formatResult(hypothesis ?: "")
    Log.d("Vosk - Result_Processed", value)
    if (value.isNotEmpty()) {
      Log.d("Vosk - onResult", "chamando o resolver com o valor recebido!")
      resolver.evaluate(value)
    }
  }

  override fun onFinalResult(hypothesis: String?) {
    // TODO: Apenas para os casos em que se queira fazer algum debug.
  }

  override fun onError(exception: Exception?) {
    Log.e("Vosk - Error", "onError: ${exception!!.stackTraceToString()}")
  }

  override fun onTimeout() {
    Log.d("Vosk - Timeout", "Timeout reached!")
  }
}