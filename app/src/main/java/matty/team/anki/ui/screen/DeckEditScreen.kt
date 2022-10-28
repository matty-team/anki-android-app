package matty.team.anki.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import matty.team.anki.R
import matty.team.anki.R.string
import matty.team.anki.data.Deck
import matty.team.anki.ui.component.DeckForm
import matty.team.anki.ui.component.DeckFormState
import matty.team.anki.ui.screenPaddingValues
import matty.team.anki.ui.theme.AnkiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckEditScreen(
    onBack: () -> Unit,
    onDone: (String, Long) -> Unit,
    deck: Deck
) {
    val formState = remember { DeckFormState(deck) }
    val doneAction = remember { { onDone(formState.nameField.text, formState.color) } }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.create_deck_btn))
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(string.go_back_btn)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = doneAction) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = stringResource(string.done_btn)
                        )
                    }
                }
            )
        }
    ) {
        Surface(
            Modifier
                .padding(it)
                .padding(screenPaddingValues)
        ) {
            DeckForm(formState = formState, onDone = doneAction)
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun DeckEditScreenPreview() {
    AnkiTheme {
        DeckCreationScreen(
            onBack = {},
            onDone = { _, _ -> }
        )
    }
}
