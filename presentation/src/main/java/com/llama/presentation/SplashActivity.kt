package com.llama.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import com.llama.domain.usecase.login.GetTokenUseCase
import com.llama.presentation.main.MainActivity
import com.llama.presentation.login.LoginActivity
import com.llama.presentation.test.MvvmScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var getTokenUseCase: GetTokenUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           MvvmScreen()
        }

//        lifecycleScope.launch {
//            val isLoggedIn = !getTokenUseCase().isNullOrEmpty()
//
//            getTokenUseCase()?.let {
//                Log.d("TAG", "onCreate: myToken = ${getTokenUseCase()}")
//            }
//
//            if (isLoggedIn) {
//                startActivity(
//                    Intent(this@SplashActivity, MainActivity::class.java).apply {
//                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                    }
//                )
//            } else {
//                startActivity(
//                    Intent(this@SplashActivity, LoginActivity::class.java).apply {
//                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                    }
//                )
//            }
//        }
    }
}