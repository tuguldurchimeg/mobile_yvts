package com.example.mobile_yvts.ui.word

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mobile_yvts.R
import com.example.mobile_yvts.data.Word

@Composable
fun WordEdit(
    word: Word?,
    onSave: (String, String) -> Unit,
    onCancel: () -> Unit
) {
    var english by remember { mutableStateOf(word?.english ?: "") }
    var mongolian by remember { mutableStateOf(word?.mongolian ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 80.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EditField(
            value = english,
            onValueChange = { english = it },
            label = stringResource(R.string.eng_word)
        )

        EditField(
            value = mongolian,
            onValueChange = { mongolian = it },
            label = stringResource(R.string.mon_word)
        )

        EditButtons(
            onSave = { onSave(english, mongolian) },
            onCancel = onCancel
        )
    }
}


@Composable
fun EditField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun EditButtons(
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
    ) {
        Button(onClick = onSave) {
            Text(text = stringResource(R.string.save))
        }
        OutlinedButton(onClick = onCancel) {
            Text(text = stringResource(R.string.cancel))
        }
    }
}
