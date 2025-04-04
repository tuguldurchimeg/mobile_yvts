package com.example.mobile_yvts.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mobile_yvts.R
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
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
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HomeHeader(onSettingsClick)
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WordCardGroup(word = word, displayMode = displayMode, onEditClick = onEditClick)
                NavigationButtons(onPrevious = onPrevious, onNext = onNext)
                ActionButtons(
                    isWordAvailable = word != null,
                    onAddClick = onAddClick,
                    onEditClick = onEditClick,
                    onDeleteClick = { showDeleteDialog = true }
                )
            }
        }
    }

    if (showDeleteDialog) {
        DeleteConfirmationDialog(
            onConfirm = {
                showDeleteDialog = false
                onDeleteClick()
            },
            onCancel = { showDeleteDialog = false }
        )
    }
}


@Composable
fun HomeHeader(onSettingsClick: () -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            IconButton(onClick = onSettingsClick) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = stringResource(R.string.settings),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun WordCardGroup(
    word: Word?,
    displayMode: DisplayMode,
    onEditClick: () -> Unit
) {
    var showMon by remember { mutableStateOf(displayMode == DisplayMode.FULL || displayMode == DisplayMode.ONLY_MONGOLIAN) }
    var showEng by remember { mutableStateOf(displayMode == DisplayMode.FULL || displayMode == DisplayMode.ONLY_ENGLISH) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        WordCard(
            label = stringResource(R.string.eng_word),
            text = if (showEng) word?.english.orEmpty() else "",
            onClick = {
                if (!showEng) showEng = true
            },
            onLongClick = onEditClick
        )
        WordCard(
            label = stringResource(R.string.mon_word),
            text = if (showMon) word?.mongolian.orEmpty() else "",
            onClick = {
                if (!showMon) showMon = true
            },
            onLongClick = onEditClick
        )
    }
}


@Composable
fun WordCard(
    label: String,
    text: String,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 1.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onClick() },
                    onLongPress = { onLongClick() }
                )
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (text.isNotBlank()) text else "-",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Composable
fun NavigationButtons(
    onPrevious: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        modifier = Modifier.padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(onClick = onPrevious) { Text(stringResource(R.string.previous)) }
        Button(onClick = onNext) { Text(stringResource(R.string.next)) }
    }
}

@Composable
fun ActionButtons(
    isWordAvailable: Boolean,
    onAddClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier.padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(onClick = onAddClick) { Text(stringResource(R.string.add_word)) }
        Button(onClick = onEditClick, enabled = isWordAvailable) { Text(stringResource(R.string.edit_word)) }
        Button(onClick = onDeleteClick, enabled = isWordAvailable) { Text(stringResource(R.string.delete)) }
    }
}

@Composable
fun DeleteConfirmationDialog(
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text(stringResource(R.string.alert)) },
        text = { Text(stringResource(R.string.delete_confirm)) },
        confirmButton = {
            TextButton(onClick = onConfirm) { Text(stringResource(R.string.yes)) }
        },
        dismissButton = {
            TextButton(onClick = onCancel) { Text(stringResource(R.string.no)) }
        }
    )
}
