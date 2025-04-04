package com.example.mobile_yvts.ui.word

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mobile_yvts.R
import com.example.mobile_yvts.ui.home.DisplayMode

@Composable
fun Settings(
    currentMode: DisplayMode,
    onSave: (DisplayMode) -> Unit,
    onBack: () -> Unit,
) {
    var selectedMode by remember { mutableStateOf(currentMode) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .wrapContentHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SettingsOptions(
                selectedMode = selectedMode,
                onModeSelected = { selectedMode = it }
            )

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                OutlinedButton(onClick = onBack) {
                    Text(text = stringResource(R.string.back))
                }
                Button(
                    onClick = {
                        onSave(selectedMode)
                        onBack()
                    }
                ) {
                    Text(text = stringResource(R.string.save))
                }
            }
        }
    }
}

@Composable
fun SettingsOptions(
    selectedMode: DisplayMode,
    onModeSelected: (DisplayMode) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SettingsOption(
            label = stringResource(R.string.both),
            selected = selectedMode == DisplayMode.FULL,
            onSelect = { onModeSelected(DisplayMode.FULL) }
        )
        SettingsOption(
            label = stringResource(R.string.only_eng),
            selected = selectedMode == DisplayMode.ONLY_ENGLISH,
            onSelect = { onModeSelected(DisplayMode.ONLY_ENGLISH) }
        )
        SettingsOption(
            label = stringResource(R.string.only_mon),
            selected = selectedMode == DisplayMode.ONLY_MONGOLIAN,
            onSelect = { onModeSelected(DisplayMode.ONLY_MONGOLIAN) }
        )
    }
}

@Composable
fun SettingsOption(
    label: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        RadioButton(selected = selected, onClick = onSelect)
        Text(
            text = label,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
