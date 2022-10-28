package matty.team.anki.data

import java.util.UUID

data class Deck(
    val id: UUID,
    val name: String,
    val color: Long,
    val isSystemDefault: Boolean = false
)

object DeckConstraints {
    const val deckNameMaxLength = 64
}

const val defaultDeckColor = 0xFFC0C0C0 // GREY

val deckColors = listOf(
    defaultDeckColor,
    0xFFCD5C5C, //INDIAN_RED
    0xFFA52A2A, //BROWN,
    0xFFDC143C, //CRIMSON
    0xFFD2691E, //CHOCOLATE
    0xFFFFA500, //ORANGE
    0xFFFFD700, //GOLD
    0xFFF0E68C, //KHAKI
    0xFFFFDAB9, //PEACH_PUFF
    0xFFFF69B4, //HOT_PINK
    0xFFEE82EE, //VIOLET
    0xFF9370DB, //MEDIUM_PURPLE
    0xFF800080, //PURPLE
    0xFF008000, //GREEN
    0xFF6B8E23, //OLIVE
    0xFF2E8B57, //SEA_GREEN
    0xFF008080, //TEAL
    0xFF7FFFD4, //AQUAMARINE
    0xFF40E0D0, //TURQUOISE
    0xFF4682B4, //STEEL_BLUE
    0xFF4169E1  //ROYAL_BLUE
)