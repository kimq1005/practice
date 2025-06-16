package com.llama.main.writing

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WritingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text("WritingActivity")
        }
    }
}