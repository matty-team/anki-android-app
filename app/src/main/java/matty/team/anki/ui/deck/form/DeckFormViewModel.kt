package matty.team.anki.ui.deck.form

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import matty.team.anki.data.Deck
import matty.team.anki.data.DeckStorage
import matty.team.anki.data.defaultDeckColor
import matty.team.anki.ui.vm.ViewModelState
import matty.team.anki.ui.vm.ViewModelState.Error
import matty.team.anki.ui.vm.ViewModelState.Ready

private const val TAG = "DeckFormViewModel"

@HiltViewModel
class DeckFormViewModel @Inject constructor(
    private val deckStorage: DeckStorage
) : ViewModel() {

    var state: ViewModelState<DeckFormState> by mutableStateOf(
        Ready(
            DeckFormState(
                name = "",
                color = defaultDeckColor,
                deckId = null
            )
        )
    )
        private set

    fun initFrom(deckId: String?) {
        if (deckId == null) {
            Log.e(TAG, "initFrom: deckId can't be null")
            state = Error("deckId can't be null")
            return;
        }
        val deck = deckStorage.findById(UUID.fromString(deckId))
        if (deck == null) {
            Log.e(TAG, "initFrom: deck with id $deckId not found")
            state = Error("Deck not found!")
            return
        }
        state = Ready(
            DeckFormState(
                name = deck.name,
                color = deck.color,
                deckId = deck.id
            )
        )
    }

    fun onAdd() {
        val currentState = state
        if (currentState is Ready) {
            deckStorage.add(
                Deck(
                    id = UUID.randomUUID(),
                    name = currentState.data.nameField.text,
                    color = currentState.data.color
                )
            )
        } else {
            Log.e(TAG, "onAdd: illegal state - $currentState")
            state = Error("Illegal state")
        }
    }

    fun onUpdate() {
        val currentState = state
        if (currentState is Ready && currentState.data.deckId != null) {
            deckStorage.update(
                Deck(
                    id = currentState.data.deckId,
                    name = currentState.data.nameField.text,
                    color = currentState.data.color
                )
            )
        } else {
            Log.e(TAG, "onUpdate: illegal state - $currentState")
            state = Error("Illegal state")
        }
    }
}