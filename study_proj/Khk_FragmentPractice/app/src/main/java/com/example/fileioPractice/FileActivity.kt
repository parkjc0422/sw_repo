package com.example.fileioPractice

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fragmentpractice.R
import java.io.*
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.example.common.Constants

class FileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_file)

        try {

            val openButton = findViewById<Button>(R.id.saveButton)
            val editedString = findViewById<EditText>(R.id.editText)

            openButton.setOnClickListener{ fileSave(editedString.text.toString()) }

        }
        catch(ex: Exception){
            ex.printStackTrace()
            Toast.makeText(applicationContext, ex.message, Toast.LENGTH_LONG)
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
            // 어떤 에러가 발생했는지 확인
            ex.printStackTrace()
            Toast.makeText(applicationContext, ex.message, Toast.LENGTH_LONG).show()
        }

        try{
            var fileLineList : List<String>?= null

            var fileReader = FileReader (fileLocation + "fileIOPractice.txt")
            var bufferedReader = BufferedReader(fileReader)

            fileLineList = bufferedReader.readLines()

            var intent = Intent(this, RecyclerViewActivity::class.java)
            intent.putExtra(Constants.VAL_INTENT_NAME, ArrayList(fileLineList))
            startActivity(intent)
        }
        catch(ex:Exception){
            ex.printStackTrace()
            Toast.makeText(applicationContext, ex.message, Toast.LENGTH_LONG).show()
        }
    }
}
