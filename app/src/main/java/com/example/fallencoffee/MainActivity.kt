package com.example.fallencoffee

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.example.fallencoffee.view.user.SignIn

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar : ProgressBar = findViewById(R.id.progressBar)
        val progressText : TextView = findViewById(R.id.progressText)
        var i = progressBar.progress
        val handler = Handler(Looper.myLooper()!!)
        val intent1  = Intent(this@MainActivity, SignIn::class.java)

        Thread {
            while (i <= progressBar.max) {
                i += 1
                handler.post {
                    progressBar.progress = i
                    val currentText = progressText.text
                    progressText.text = "$currentText => {$i %}"
                }
                try {
                    Thread.sleep(200)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            progressBar.visibility = View.INVISIBLE
            startActivity(intent1)

        }.start()
    }
}