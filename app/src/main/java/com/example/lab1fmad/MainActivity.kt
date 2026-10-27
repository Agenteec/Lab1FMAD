package com.example.lab1fmad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab1fmad.ui.theme.Lab1FMADTheme
import java.util.Locale

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
    val labLogic = remember { LabLogic() }
    var sizeInput by remember { mutableStateOf("10") }
    var numbers by remember { mutableStateOf(labLogic.generateNumbers()) }
    var output by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Вариант 5. Нормализация списка по максимальному элементу", style = MaterialTheme.typography.titleMedium)

        OutlinedTextField(
            value = sizeInput,
            onValueChange = { sizeInput = it.filter(Char::isDigit).take(3) },
            label = { Text("Количество элементов") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Text("Исходный список:\n" + numbers.joinToString(", "))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = {
                numbers = labLogic.generateNumbers(sizeInput.toIntOrNull()?.coerceIn(1, 100) ?: 10)
                output = ""
            }) {
                Text("Сгенерировать")
            }

            Button(onClick = {
                val result = labLogic.normalizeByMax(numbers)
                output = if (result.normalized.isEmpty()) {
                    "Нормализация невозможна: максимальный элемент равен ${result.max ?: "—"}"
                } else buildString {
                    append("Максимальный элемент: ${result.max}\n\n")
                    append("Нормализованный список:\n")
                    append(result.normalized.joinToString(", ") { String.format(Locale.US, "%.3f", it) })
                }
            }) {
                Text("Нормализовать")
            }
        }

        OutlinedTextField(
            value = output,
            onValueChange = { },
            label = { Text("Результат") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            singleLine = false,
            minLines = 4,
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
