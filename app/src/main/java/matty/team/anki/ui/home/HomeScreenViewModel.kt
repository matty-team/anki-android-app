package matty.team.anki.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import matty.team.anki.data.Deck
import matty.team.anki.data.DeckStorage

private const val TAG = "HomeScreenViewModel"

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val deckStorage: DeckStorage) : ViewModel() {
    var deckList: List<Deck> = listOf()
        private set

    fun onInit() {
        Log.d(TAG, "onInit")
        deckList = deckStorage.getAll()
    }
}