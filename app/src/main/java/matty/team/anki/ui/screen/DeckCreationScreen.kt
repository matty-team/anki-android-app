package matty.team.anki.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import matty.team.anki.ui.theme.AnkiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckCreationScreen(
    onBackClicked: () -> Unit,
    onDoneClicked: () -> Unit
) {
    val (name, setName) = remember { mutableStateOf("") }
    val (description, setDescription) = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "New Deck")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Go back")
                    }
                },
                actions = {
                    IconButton(onClick = onDoneClicked) {
                        Icon(Icons.Default.Done, contentDescription = "Done")
                    }
                }
            )
        }
    ) {
        Surface(Modifier.padding(it)) {
            Column(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = setName,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Name")
                    }
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = setDescription,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Description")
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DeckCreationScreenPreview() {
    AnkiTheme {
        DeckCreationScreen(
            onBackClicked = {},
            onDoneClicked = {}
        )
    }
}
