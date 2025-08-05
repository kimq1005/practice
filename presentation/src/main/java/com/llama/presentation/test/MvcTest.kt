package com.llama.presentation.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.llama.presentation.theme.ConnectedTheme

@Composable
fun CounterMVCScreen(
    controller: CounterController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "count = ${controller.count.intValue}",
            fontSize = 14.sp
        )

        Button(
            modifier = Modifier
                .padding(top = 13.dp),
            onClick = { controller.onIncrement() }
        ) {
            Text("눌러요")
        }
    }
}

class CounterModel {
    private var count = 0

    fun getCount() = count

    fun increment() {
        count ++
    }
}

class CounterController(
    private val model: CounterModel
) {
    private val _count = mutableIntStateOf(model.getCount())
    val count = _count

    fun onIncrement() {
        model.increment()
        _count.intValue = model.getCount()
    }
}

@Preview
@Composable
private fun CounterMVCScreenPreview() {
    ConnectedTheme {
        val model = CounterModel()
        val controller = CounterController(model)

        CounterMVCScreen(
            controller = controller
        )
    }
}
