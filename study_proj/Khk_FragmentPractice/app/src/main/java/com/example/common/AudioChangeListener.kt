package com.example.common

import android.content.Context
import android.media.AudioManager
import android.os.Vibrator
import android.widget.Toast

class AudioChangeListener(context: Context) : AudioManager.OnAudioFocusChangeListener{

    private var vibratorManager = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    private var context = context
    private val VAL_CONTROLED_SOUND = " : 조절 중"

    private fun audioModeChange(audioFocus : Int){
        vibratorManager.vibrate(1000)
        Toast.makeText(this.context, audioFocus.toString() + VAL_CONTROLED_SOUND, Toast.LENGTH_SHORT).show()
    }

    override fun onAudioFocusChange(focusChange: Int) {

        when(focusChange){
            AudioManager.AUDIOFOCUS_GAIN -> audioModeChange(AudioManager.AUDIOFOCUS_GAIN)
            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT -> audioModeChange(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> audioModeChange(AudioManager.AUDIOFOCUS_LOSS_TRANSIENT)
            AudioManager.AUDIOFOCUS_LOSS -> audioModeChange(AudioManager.AUDIOFOCUS_LOSS)
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> audioModeChange(AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
        }
    }



}