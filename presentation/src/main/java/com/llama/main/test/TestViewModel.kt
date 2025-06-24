package com.llama.main.test

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(

) : ViewModel() {
    private val events = Channel<TestEvent>()

    val state = events.receiveAsFlow()
        .runningFold(TestState(), ::reduceState)
        .stateIn(viewModelScope, SharingStarted.Eagerly, TestState())

    fun onEvent(event: TestEvent) {
        viewModelScope.launch {
            events.send(event)
        }
    }

    private fun reduceState(current: TestState, event: TestEvent): TestState {
        return when (event) {
            is TestEvent.Increment -> current.copy(count = current.count + 1)
            is TestEvent.Decrement -> current.copy(count = current.count - 1)
        }
    }
}

@Immutable
data class TestState(
    val count: Int = 0,
)

sealed class TestEvent {
    object Increment : TestEvent()
    object Decrement : TestEvent()
}
