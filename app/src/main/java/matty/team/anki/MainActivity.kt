package matty.team.anki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID
import matty.team.anki.Screen.DeckCreation
import matty.team.anki.Screen.DeckDetails
import matty.team.anki.Screen.DeckEdit
import matty.team.anki.Screen.Main
import matty.team.anki.data.Deck
import matty.team.anki.data.defaultDeckColor
import matty.team.anki.ui.screen.DeckCreationScreen
import matty.team.anki.ui.screen.DeckDetailsScreen
import matty.team.anki.ui.screen.DeckEditScreen
import matty.team.anki.ui.screen.MainScreen
import matty.team.anki.ui.theme.AnkiTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AnkiTheme {
                var deckList by remember {
                    mutableStateOf(
                        listOf(
                            Deck(
                                id = UUID.randomUUID(),
                                name = "English",
                                color = defaultDeckColor
                            )
                        )
                    )
                }

                Surface {
                    NavHost(
                        navController = navController,
                        startDestination = Main.route,
                    ) {
                        composable(Main.route) {
                            MainScreen(navController, deckList = deckList)
                        }
                        composable(DeckCreation.route) {
                            DeckCreationScreen(
                                onDone = { name, color ->
                                    navController.popBackStack()
                                    deckList = deckList + Deck(id = UUID.randomUUID(), name, color)
                                },
                                onBack = navController::popBackStack
                            )
                        }
                        composable(DeckEdit.route) {
                            val idArg = it.arguments?.getString("id")
                            val deck = remember(id) {
                                val id = UUID.fromString(idArg)
                                deckList.find { deck -> deck.id == id }
                            }
                            DeckEditScreen(
                                deck = deck!!,
                                onDone = { name, color ->
                                    navController.navigate(Main.route) {
                                        popUpTo(Main.route) { inclusive = true }
                                    }
                                    deckList = deckList.map { d ->
                                        if (d.id == deck.id) deck.copy(
                                            name = name,
                                            color = color
                                        ) else d
                                    }
                                },
                                onBack = navController::popBackStack
                            )
                        }
                        composable(DeckDetails.route) {
                            val idArg = it.arguments?.getString("id")
                            val deck = remember(id) {
                                val id = UUID.fromString(idArg)
                                deckList.find { deck -> deck.id == id }
                            }
                            DeckDetailsScreen(
                                deck = deck!!,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object DeckCreation : Screen("deck/new")
    object CardCreation : Screen("card/new")

    object DeckDetails : Screen("deck/details/{id}") {
        fun resolveRoute(deckId: UUID) = "deck/details/$deckId"
    }

    object DeckEdit : Screen("deck/edit/{id}") {
        fun resolveRoute(cardId: UUID) = "deck/edit/$cardId"
    }
}
