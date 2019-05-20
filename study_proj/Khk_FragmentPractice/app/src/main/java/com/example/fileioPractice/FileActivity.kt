package com.example.fileioPractice

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.common.AudioChangeListener
import com.example.common.Constants
import com.example.common.showExceptionByToast
import com.example.fragmentpractice.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

class FileActivity : AppCompatActivity() {

    private lateinit var audioManager :AudioManager
    private lateinit var audioChangeListener : AudioChangeListener
    private lateinit var media : MediaPlayer
    private var originSound : Int = 0

    private var TAG : String = "FileActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        audioManager = this.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioChangeListener = AudioChangeListener(this)
        media = MediaPlayer.create(this, R.raw.zoom)
        originSound = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

        Log.i(TAG, "")
        //media.start()
        audioManager.requestAudioFocus(audioChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN)
        try {

            val openButton = findViewById<Button>(R.id.saveButton)
            val callButton = findViewById<Button>(R.id.callButton)
            val toastButton = findViewById<Button>(R.id.toastButton)

            val editedString = findViewById<EditText>(R.id.editText)
            val callNumber = findViewById<EditText>(R.id.numText)
            val toastString = findViewById<EditText>(R.id.toastText)

            //Observable.create<View>{toastButton.setOnClickListener(it::onNext)}.map{}.subscribe{toastString.text = editedString.text}

            openButton.setOnClickListener{ fileSave(editedString.text.toString()) }
            callButton.setOnClickListener{ call(callNumber.text.toString()) }
            toastButton.setOnClickListener{ rxTest()}

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

    private fun rxTest(){
        /**
         * disposable은 Observer가 Observable에서 구독할 때 생성되는 객체.
         * Observable에서 만드는 이벤트 스트림과 이에 필요한 리소스를 관리.
         * 예) 구독해제: disposable을 통해 구독 해제한 경우 Observable에서 이를 감지하고 유지하던 리소스를 해제한다.
         *
         */

        /* disposable 객체들을 관리하는 객체 */
        var disposable = CompositeDisposable()


        var publishTest = PublishSubject.create<String>()

        /* Observer가 작업을 수행할 스레드를 지정한다. 아래에선 메인 스레드 */
        val subscribe = publishTest.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())    /* Observable이 작업을 수행 할 스레드를 지정한다.  */
            .subscribe({    /* onNext */
                println("ioissibal" + it)
            },
                {               /* onError */
                    it.printStackTrace()
                })              /* onComplete */
            {
                println("complete")
            }

        subscribe.addTo(disposable)


        publishTest.onNext("1")
        publishTest.onNext("2")
        publishTest.onNext("3")
        publishTest.onNext("4")
        publishTest.onNext("5")
    }
}
