package matty.team.anki.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import matty.team.anki.CreationMenu
import matty.team.anki.Screen.DeckCreation

private const val TAG = "MainScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val (isCreationMenuExpanded, setCreationMenuExpanded) = remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            CreationMenu(
                isCreationMenuExpanded,
                setCreationMenuExpanded,
                onAddCardClick = {
                    setCreationMenuExpanded(false)
                    Log.d(TAG, "onCreate: onAddCardClicked")
                },
                onAddDeckClick = {
                    setCreationMenuExpanded(false)
                    navController.navigate(DeckCreation.route)
                }
            )
        }
    ) {
        Surface(Modifier.padding(it)) {
            Text(text = "Main screen")
        }
    }
}
