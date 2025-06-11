package com.llama.practice

sealed class Event {
    object Increment: Event()
    object Decrement: Event()
}

data class State(
    val counter: Int = 0,
)