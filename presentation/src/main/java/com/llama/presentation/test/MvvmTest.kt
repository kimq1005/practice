package com.llama.presentation.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@Composable
fun MvvmScreen(
    modifier: Modifier = Modifier,
    viewModel: MvvmViewModel = hiltViewModel(),
) {
    val count = viewModel.count.collectAsState().value

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text= "count: $count",
            color = Color.White,
            fontSize = 14.sp
        )

        Button(
            modifier = Modifier
                .padding(top = 10.dp),
            onClick = viewModel::setSum
        ) {
            Text("눌러요")
        }
    }
}

class CountRepository {

}

@HiltViewModel
class MvvmViewModel @Inject constructor(

) : ViewModel() {
    private val _count = MutableStateFlow(0)
    val count = _count.asStateFlow()

    fun setSum() {
        _count.value += 1
    }
}