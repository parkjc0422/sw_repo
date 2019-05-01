package com.example.fileioPractice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fragmentpractice.R
import java.io.*
import android.net.Uri
import com.example.common.Constants

class FileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_file)

        try {

            val openButton = findViewById<Button>(R.id.saveButton)
            val callButton = findViewById<Button>(R.id.callButton)

            val editedString = findViewById<EditText>(R.id.editText)
            val callNumber = findViewById<EditText>(R.id.numText)

            openButton.setOnClickListener{ fileSave(editedString.text.toString()) }
            callButton.setOnClickListener{ call(callNumber.text.toString()) }

        }
        catch(ex: Exception){
            exceptionHandler(ex)
        }
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
            exceptionHandler(ex)
        }

        try{
            var fileLineList: List<String>?

            var fileReader = FileReader (fileLocation + "fileIOPractice.txt")
            var bufferedReader = BufferedReader(fileReader)

            fileLineList = bufferedReader.readLines()

            var intent = Intent(this, RecyclerViewActivity::class.java)
            intent.putExtra(Constants.VAL_INTENT_NAME, ArrayList(fileLineList))
            startActivity(intent)
        }
        catch(ex:Exception){
            exceptionHandler(ex)
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
            startActivity(Intent("android.intent.action.DIAL", Uri.parse(uriString)))
        }catch (ex:Exception){
            exceptionHandler(ex)
        }
    }
    
    private fun exceptionHandler(ex : Exception){
        ex.printStackTrace()
        Toast.makeText(this, ex.message, Toast.LENGTH_LONG)
    }
}
