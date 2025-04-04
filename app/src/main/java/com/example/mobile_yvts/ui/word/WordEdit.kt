package com.example.mobile_yvts.ui.word

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun WordEdit(
    initialEnglish: String,
    initialMongolian: String,
    onSave: (english: String, mongolian: String) -> Unit,
    onCancel: () -> Unit
) {
    var english by remember { mutableStateOf(initialEnglish) }
    var mongolian by remember { mutableStateOf(initialMongolian) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = english,
            onValueChange = { english = it },
            label = { Text("English Word") }
        )
        OutlinedTextField(
            value = mongolian,
            onValueChange = { mongolian = it },
            label = { Text("Mongolian Word") }
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { onSave(english, mongolian) }) {
                Text("Update")
            }
            OutlinedButton(onClick = onCancel) {
                Text("Cancel")
            }
        }
    }
}
