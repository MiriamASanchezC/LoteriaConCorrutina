package com.example.lotericoncorrutina.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lotericoncorrutina.viewModels.LoteriaViewModel

import kotlinx.coroutines.delay

val LightPink = Color(0xFFFFC0CB)
val Pink = Color(0xFFC842F3)

@Composable
fun Loteriaview(viewModel: LoteriaViewModel) {
    val lottonNumbers = viewModel.lotoNumbers.value
    val isLoading = viewModel.isLoading.value

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            // Seguimos mostrando el indicador mientras se generan los números
            CircularProgressIndicator(color = Pink)

        }

        // Mostramos los números generados hasta el momento
        if (lottonNumbers.isNotEmpty()) {
            LotteryNumbers(lottonNumbers)
        }

        Button(onClick = { viewModel.generateLotoNumbers() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Pink
            )) {
            Text(
                text = "Generar",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold

            )
        }
    }
}

@Composable
fun LotteryNumbers(lottonNumbers: List<Int>) {
    LazyRow(
        contentPadding = PaddingValues(16.dp, 8.dp)
    ) {
        items(lottonNumbers) { number ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(35.dp)
                    .background(LightPink, CircleShape)
            ) {
                Text(
                    text = number.toString(),
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }
    }
}