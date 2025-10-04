package com.example.lab1fmad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab1fmad.ui.theme.Lab1FMADTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab1FMADTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(modifier = Modifier.padding(innerPadding).padding(16.dp))
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {

        Button(onClick = {
            val labLogic = LabLogic()

            val variant = input.toIntOrNull()

            val numbers = labLogic.numbers
            val sumNeg = labLogic.sumOfNegativeElements()
            val countPos = labLogic.countOfPositiveElements()


            output = buildString {
                append("Сгенерированный список:\n")
                append(numbers.joinToString(", "))
                append("\n\nРезультаты:\n")
                append("Сумма отрицательных элементов: $sumNeg\n")
                append("Количество положительных элементов: $countPos")
            }
        }) {
            Text("Выполнить")
        }

        OutlinedTextField(
            value = output,
            onValueChange = { },
            label = { Text("Результат") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            readOnly = true,
            singleLine = false,
            maxLines = 10
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainContent() {
    Lab1FMADTheme {
        MainContent()
    }
}
