package com.example.mobile_yvts.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobile_yvts.data.Word

@Composable
fun HomeScreen(
    word: Word?,
    displayMode: DisplayMode,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onAddClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = when (displayMode) {
                DisplayMode.FULL -> "${word?.english ?: ""}\n${word?.mongolian ?: ""}"
                DisplayMode.ONLY_ENGLISH -> word?.english ?: ""
                DisplayMode.ONLY_MONGOLIAN -> word?.mongolian ?: ""
            },
            style = MaterialTheme.typography.headlineMedium
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = onPrevious) { Text("Previous") }
            Button(onClick = onNext) { Text("Next") }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = onAddClick) { Text("Add") }
            Button(onClick = onEditClick, enabled = word != null) { Text("Edit") }
        }

        Button(onClick = onDeleteClick, enabled = word != null) {
            Text("Delete")
        }

        TextButton(onClick = onSettingsClick) {
            Text("Settings")
        }
    }
}
