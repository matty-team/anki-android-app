package matty.team.anki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import matty.team.anki.Screen.DeckCreation
import matty.team.anki.Screen.Main
import matty.team.anki.ui.screen.DeckCreationScreen
import matty.team.anki.ui.screen.MainScreen
import matty.team.anki.ui.theme.AnkiTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AnkiTheme {
                Surface {
                    NavHost(
                        navController = navController,
                        startDestination = Main.route,
                    ) {
                        composable(Main.route) {
                            MainScreen(navController)
                        }
                        composable(DeckCreation.route) {
                            DeckCreationScreen(
                                onDoneClicked = navController::popBackStack,
                                onBackClicked = navController::popBackStack
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
}
