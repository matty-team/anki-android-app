package matty.team.anki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID
import matty.team.anki.Screen.DeckDetails
import matty.team.anki.Screen.DeckEdit
import matty.team.anki.Screen.Home
import matty.team.anki.Screen.NewDeck
import matty.team.anki.ui.deck.DeckCreationScreen
import matty.team.anki.ui.deck.DeckEditScreen
import matty.team.anki.ui.deck.details.DeckDetailsScreen
import matty.team.anki.ui.deck.details.DeckDetailsViewModel
import matty.team.anki.ui.deck.form.DeckFormViewModel
import matty.team.anki.ui.home.HomeScreen
import matty.team.anki.ui.home.HomeScreenViewModel
import matty.team.anki.ui.theme.AnkiTheme
import matty.team.anki.ui.vm.ViewModelState.Error

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AnkiTheme {
                Surface {
                    NavHost(
                        navController = navController,
                        startDestination = Home.route,
                    ) {
                        composable(Home.route) {
                            val viewModel = hiltViewModel<HomeScreenViewModel>()
                            HomeScreen(
                                navController,
                                viewModel::onInit,
                                viewModel.deckList
                            )
                        }
                        composable(NewDeck.route) {
                            val viewModel = hiltViewModel<DeckFormViewModel>()
                            DeckCreationScreen(
                                state = viewModel.state,
                                onDone = {
                                    viewModel.onAdd()
                                    if (viewModel.state !is Error) {
                                        navController.popBackStack()
                                    }
                                },
                                onBack = navController::popBackStack
                            )
                        }
                        composable(DeckEdit.route) {
                            val idArg = it.arguments?.getString("id")
                            val viewModel = hiltViewModel<DeckFormViewModel>()
                            DeckEditScreen(
                                init = { viewModel.initFrom(idArg) },
                                onDone = {
                                    viewModel.onUpdate()
                                    if (viewModel.state !is Error) {
                                        navController.popBackStack()
                                    }
                                },
                                onBack = navController::popBackStack,
                                state = viewModel.state
                            )
                        }
                        composable(DeckDetails.route) {
                            val idArg = it.arguments?.getString("id")
                            val viewModel = hiltViewModel<DeckDetailsViewModel>()
                            DeckDetailsScreen(
                                init = { viewModel.loadDeck(idArg) },
                                state = viewModel.state,
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
    object Home : Screen("main")
    object NewDeck : Screen("deck/new")
    object NewCard : Screen("card/new")

    object DeckDetails : Screen("deck/details/{id}") {
        fun resolveRoute(deckId: UUID) = "deck/details/$deckId"
    }

    object DeckEdit : Screen("deck/edit/{id}") {
        fun resolveRoute(cardId: UUID) = "deck/edit/$cardId"
    }
}
