package matty.team.anki.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import matty.team.anki.R.string
import matty.team.anki.Screen
import matty.team.anki.data.Deck
import matty.team.anki.ui.component.DeckList
import matty.team.anki.ui.component.MainBottomBar
import matty.team.anki.ui.screenPaddingValues

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    onInit: () -> Unit,
    deckList: List<Deck>
) {
    LaunchedEffect(Unit) {
        onInit()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {})
        },
        bottomBar = {
            MainBottomBar(
                onLearnButtonClick = { TODO() },
                onAddCardButtonClick = { TODO() }
            )
        }
    ) {
        Surface(
            Modifier
                .padding(it)
                .padding(screenPaddingValues)
        ) {
            if (deckList.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Center) {
                    Button(onClick = { navController.navigate(Screen.NewDeck.route) }) {
                        Text(text = stringResource(string.create_deck_btn))
                    }
                }
            } else {
                DeckList(
                    decks = deckList,
                    onAddClick = { navController.navigate(Screen.NewDeck.route) },
                    onDeckClick = { card ->
                        navController.navigate(
                            Screen.DeckDetails.resolveRoute(card.id)
                        )
                    }
                )
            }
        }
    }
}
