package com.example.fileioPractice

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.fragmentpractice.R
import java.io.*
import android.net.Uri
import com.example.common.AudioChangeListener
import com.example.common.Constants
import com.example.common.showExceptionByToast

class FileActivity : AppCompatActivity() {

    private lateinit var audioManager :AudioManager
    private lateinit var audioChangeListener : AudioChangeListener
    private lateinit var media : MediaPlayer
    private var originSound : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        audioManager = this.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioChangeListener = AudioChangeListener(this)
        media = MediaPlayer.create(this, R.raw.zoom)
        originSound = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

        media.start()
        audioManager.requestAudioFocus(audioChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN)
        try {

            val openButton = findViewById<Button>(R.id.saveButton)
            val callButton = findViewById<Button>(R.id.callButton)

            val editedString = findViewById<EditText>(R.id.editText)
            val callNumber = findViewById<EditText>(R.id.numText)

            openButton.setOnClickListener{ fileSave(editedString.text.toString()) }
            callButton.setOnClickListener{ call(callNumber.text.toString()) }

        }
        catch(ex: Exception){
            showExceptionByToast(this, ex)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, originSound, AudioManager.FLAG_PLAY_SOUND)
        audioManager.abandonAudioFocus(audioChangeListener)
        media.stop()
    }

    /**
     *
     * EditText에 적힌 내용을 파일에 저장 후 RecyclerViewActivity로 넘긴다.
     *
     * @author khk
     * */
    private fun fileSave(text:String){
        // 저장 경로
        val fileLocation : String = filesDir.toString()
        try{

            var fileWriter = FileWriter(fileLocation + "fileIOPractice.txt", false)
            var bufferedWriter = BufferedWriter(fileWriter)
            bufferedWriter.write(text)
            bufferedWriter.close()
        }
        catch(ex:Exception){
            showExceptionByToast(this, ex)
        }

        try{
            var fileLineList: List<String>?

            var fileReader = FileReader (fileLocation + "fileIOPractice.txt")
            var bufferedReader = BufferedReader(fileReader)

            fileLineList = bufferedReader.readLines()

            var intent = Intent(this, RecyclerViewActivity::class.java)
            intent.putExtra(Constants.VAL_INTENT_KEY_STRINGLIST, ArrayList(fileLineList))
            startActivity(intent)
        }
        catch(ex:Exception){
            showExceptionByToast(this, ex)
        }
    }

    /**
     *
     * 입력한 번호를 다이얼 화면으로 넘겨준다.
     * @author khk
     *
     * */
    private fun call(numText : String){
        try {
            var uriString = "tel:" + numText
            startActivity(Intent(Constants.VAL_INTENT_KEY_DIAL, Uri.parse(uriString)))
        }catch (ex:Exception){
            showExceptionByToast(this, ex)
        }
    }
    

}
