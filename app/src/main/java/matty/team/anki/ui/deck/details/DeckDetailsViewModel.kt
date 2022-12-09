package matty.team.anki.ui.deck.details

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
import matty.team.anki.ui.vm.ViewModelState
import matty.team.anki.ui.vm.ViewModelState.NotInit

private const val TAG = "DeckDetailsViewModel"

@HiltViewModel
class DeckDetailsViewModel @Inject constructor(
    private val deckStorage: DeckStorage
) : ViewModel() {
    var state: DeckDetailsState by mutableStateOf(NotInit())
        private set

    fun loadDeck(deckId: String?) {
        if (deckId == null) {
            Log.e(TAG, "loadDeck: deck id can't be null")
            state = ViewModelState.Error("Something's gone wrong =(")
            return
        }
        val deck = deckStorage.findById(UUID.fromString(deckId))
        if (deck == null) {
            Log.e(TAG, "loadDeck: deck with id - $deckId not found")
            state = ViewModelState.Error("Deck not found")
            return
        }
        state = ViewModelState.Ready(deck)
    }
}

typealias DeckDetailsState = ViewModelState<Deck>

