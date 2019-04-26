package com.example.fileioPractice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fragmentpractice.R
import khttp.post
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.nio.Buffer

class FileActivity : AppCompatActivity() {

    // Root Directory 상수 선언
    //private val fileLocation : String = Environment.getRootDirectory().absolutePath
    private val fileLocation : String = Environment.DIRECTORY_DOWNLOADS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_file)

        val openButton = findViewById<Button>(R.id.saveButton)
        val editedString = findViewById<EditText>(R.id.editText)

        openButton.setOnClickListener{ fileSave(editedString.text.toString()) }

    }

    /**
     *
     * 파일을 저장할 디렉토리를 설정한다.
     *
     * @author khk
     *
     * */
    private fun openFileBrowser()  {

        var selectedSavePath : String ?= null

        try{
            val fileList = File(fileLocation).listFiles()

        }
        catch(ex:Exception){
            // 어떤 에러가 발생했는지 확인
            ex.printStackTrace()
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG)
        }
        finally {
            // 파일 브라우저 닫기
            // file I/O 관련한 내용 닫기
        }
    }

    /**
     *
     * EditText에 적힌 내용을 파일에 저장한다.
     *
     * @author khk
     * */
    private fun fileSave(text:String){
        // 저장 경로
        try{
            var saveFile = File(fileLocation)
            if(!saveFile.exists()){
                throw Exception("There is no Directory!")
            }

            var writer = BufferedWriter(FileWriter(saveFile.name + "/fileIOPractice.txt",true));
            writer.append(text)
            writer.close()
        }
        catch(ex:Exception){
            // 어떤 에러가 발생했는지 확인
            ex.printStackTrace()
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG)
        }
        finally {
            // 파일 브라우저 닫기
            // file I/O 관련한 내용 닫기
        }


    }
}
