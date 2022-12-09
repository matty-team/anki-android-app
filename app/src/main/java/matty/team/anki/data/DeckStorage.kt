package matty.team.anki.data

import java.util.UUID


interface DeckStorage {

    fun add(deck: Deck) {}

    fun update(deck: Deck) {}

    fun getAll(): List<Deck>

    fun findById(id: UUID): Deck?
}