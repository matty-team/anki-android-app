package matty.team.anki.ui.deck.form

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import java.util.UUID

@Stable
class DeckFormState(
    name: String,
    color: Long,
    val deckId: UUID? = null
) {
    var nameField by mutableStateOf(TextFieldValue(text = name, selection = TextRange(name.length)))
    var color by mutableStateOf(color)
}
