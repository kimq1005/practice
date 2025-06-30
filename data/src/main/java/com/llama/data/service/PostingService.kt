package com.llama.data.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.llama.data.model.BoardParam
import com.llama.data.model.BoardParcel
import com.llama.data.model.ContentParam
import com.llama.data.retrofit.BoardService
import com.llama.domain.usecase.file.UploadImageUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PostingService(): LifecycleService() {

    @Inject
    lateinit var uploadImageUseCase: UploadImageUseCase

    @Inject
    lateinit var boardService: BoardService

    companion object {
        const val EXTRA_BOARD = "extra_board"
        const val CHANNEL_ID = "channel_id"
        const val CHANNEL_NAME = "channel_name"
        const val FOREGROUND_NOTIFICATION_ID = 1000
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        startForeground()
        intent?.run {
            if (hasExtra(EXTRA_BOARD)) {
                val boardParcel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getParcelableExtra(EXTRA_BOARD, BoardParcel::class.java)
                } else {
                    getParcelableExtra(EXTRA_BOARD)
                }

                boardParcel?.run {
                    lifecycleScope.launch(Dispatchers.IO) {
                        postBoard(this@run)
                    }
                }
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    @SuppressLint("NewApi")
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )

        channel.description = "백그라운드에서 글을 업로드 합니다."

        val notificationManager: NotificationManager? = getSystemService(this, NotificationManager::class.java)
        notificationManager?.createNotificationChannel(channel)
    }


    private fun startForeground() {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID).build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(
                FOREGROUND_NOTIFICATION_ID,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_SHORT_SERVICE
            )
        } else {
            startForeground(
                FOREGROUND_NOTIFICATION_ID,
                notification
            )
        }
    }

    private suspend fun postBoard(boardParcel: BoardParcel) {
        val uploadImages = boardParcel.images.mapNotNull { image ->
            uploadImageUseCase(image).getOrNull()
        }

        val contentParam = ContentParam(
            text = boardParcel.content,
            images = uploadImages
        )

        val requestBody = BoardParam(
            title = boardParcel.title,
            content = contentParam.toJson()
        ).toRequestBody()

        boardService.postBoard(requestBody)
        stopForeground(STOP_FOREGROUND_DETACH)
    }
}