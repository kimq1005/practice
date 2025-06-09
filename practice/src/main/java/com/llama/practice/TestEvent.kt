package com.llama.practice

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

sealed class Event {
    object Increment: Event()
    object Decrement: Event()
}

data class State(
    val counter: Int = 0,
)