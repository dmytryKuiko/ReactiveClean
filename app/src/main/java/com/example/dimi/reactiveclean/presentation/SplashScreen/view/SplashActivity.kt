package com.example.dimi.reactiveclean.presentation.SplashScreen.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.presentation.FirstScreen.view.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        button.setOnClickListener { view -> startActivity(Intent(this, MainActivity::class.java)) }
        //startActivity(Intent(this, MainActivity::class.java))
    }
}
