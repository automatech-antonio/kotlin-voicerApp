package tech.voicer.voicerapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import tech.voicer.voicerapp.domain.StartupUseCase
import tech.voicer.voicerapp.infra.Stt
import tech.voicer.voicerapp.infra.Tts
import tech.voicer.voicerapp.infra.api.Backend
import tech.voicer.voicerapp.shared.Messages
import tech.voicer.voicerapp.shared.onSuccess
import kotlinx.coroutines.*
import tech.voicer.voicerapp.shared.onError

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_main)
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    MainScope().launch {
      Backend().getInfoService()
        .onSuccess {
          res -> Log.d("Main Activity", res.recordset.toString())
          Tts(this@MainActivity, Messages.WELCOME.text)
          Stt(this@MainActivity)
          StartupUseCase(this@MainActivity)
        }
        .onError {
          err, _ -> Log.e("Main Activity", err.toString())
          Tts(this@MainActivity, Messages.BACKEND_ERROR.text)
        }
    }

  }
}