package matty.team.anki.ui.component

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import matty.team.anki.data.Deck
import matty.team.anki.data.defaultDeckColor

@Stable
class DeckFormState(
    name: String = "",
    color: Long = defaultDeckColor
) {
    constructor(deck: Deck) : this(deck.name, deck.color)

    var nameField by mutableStateOf(TextFieldValue(text = name, selection = TextRange(name.length)))
    var color by mutableStateOf(color)
}
