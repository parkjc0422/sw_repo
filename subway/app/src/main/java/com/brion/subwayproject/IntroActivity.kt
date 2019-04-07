package com.brion.subwayproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.brion.subwayproject.ui.SubwayMapActivity
import com.brion.subwayproject.ui.custom.TransparentProgressDialog
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : FragmentActivity() {
    lateinit var alert: TransparentProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        Glide.with(this)
            .load(R.drawable.title_01)
            .thumbnail(0.3f)

            .into(logoImage)


        alert = TransparentProgressDialog(this,
            R.drawable.loading_img,
            R.style.TransparentProgressDialog)


        alert.show()

        Thread{
            Thread.sleep(2000)
            alert.dismiss()

            startActivity(Intent(this, SubwayMapActivity::class.java))
        }.start()
    }


}
