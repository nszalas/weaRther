package com.nszalas.wearther.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nszalas.wearther.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
    }
}