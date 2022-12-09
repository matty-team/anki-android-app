package matty.team.anki.ui.deck

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import matty.team.anki.R
import matty.team.anki.data.defaultDeckColor
import matty.team.anki.ui.component.button.BackButton
import matty.team.anki.ui.component.button.DoneButton
import matty.team.anki.ui.deck.form.DeckForm
import matty.team.anki.ui.deck.form.DeckFormState
import matty.team.anki.ui.screenPaddingValues
import matty.team.anki.ui.theme.AnkiTheme
import matty.team.anki.ui.vm.ViewModelState
import matty.team.anki.ui.vm.ViewModelState.Ready

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckCreationScreen(
    state: ViewModelState<DeckFormState>,
    onBack: () -> Unit,
    onDone: () -> Unit
) {
    val doneAction = remember { { onDone() } }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.create_deck_btn))
                },
                navigationIcon = {
                    BackButton(onClick = onBack)
                },
                actions = {
                    DoneButton(onClick = doneAction)
                }
            )
        }
    ) {
        Surface(
            Modifier
                .padding(it)
                .padding(screenPaddingValues)
        ) {
            if (state is Ready) {
                DeckForm(onDone = doneAction, formState = state.data)
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun DeckCreationScreenPreview() {
    AnkiTheme {
        DeckCreationScreen(
            onBack = {},
            onDone = {},
            state = Ready(
                DeckFormState(name = "", color = defaultDeckColor)
            )
        )
    }
}
