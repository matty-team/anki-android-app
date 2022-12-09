package matty.team.anki.data

import androidx.lifecycle.ViewModel
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryDeckStorage @Inject constructor() : ViewModel(), DeckStorage {
    private val decks = mutableListOf<Deck>()

    override fun add(deck: Deck) {
        decks.add(deck)
    }

    override fun update(deck: Deck) {
        val index = decks.indexOfFirst { it.id == deck.id }
        decks.add(index, deck)
    }

    override fun getAll(): List<Deck> = decks

    override fun findById(id: UUID): Deck? = decks.find { it.id == id }
}
