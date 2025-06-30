package com.llama.data.model

import android.os.Parcelable
import com.llama.domain.model.Image
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class BoardParcel(
    val title: String,
    val content: String,
    val images: List<Image>
): Parcelable