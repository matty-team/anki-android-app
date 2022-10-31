package matty.team.anki.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import matty.team.anki.R.string
import matty.team.anki.Screen
import matty.team.anki.data.Deck
import matty.team.anki.ui.component.MainBottomBar
import matty.team.anki.ui.component.button.BackButton
import matty.team.anki.ui.screenPaddingValues

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckDetailsScreen(deck: Deck, navController: NavController) {
    Scaffold(
        topBar = {
            TopBar(navController, deck)
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

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    navController: NavController,
    deck: Deck
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            BackButton(onClick = { navController.popBackStack() })
        },
        title = {
            Text(text = deck.name)
        },
        actions = {
            DeckActions(
                onEditDeckClick = { navController.navigate(Screen.DeckEdit.resolveRoute(deck.id)) }
            )
        }
    )
}

@Composable
private fun DeckActions(
    onEditDeckClick: () -> Unit
) {
    var menuExpanded by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = { menuExpanded = true }) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = stringResource(string.deck_menu_desc)
        )
    }

    DropdownMenu(
        expanded = menuExpanded,
        onDismissRequest = { menuExpanded = false },
        offset = DpOffset(8.dp, 0.dp)
    ) {
        DropdownMenuItem(
            text = { Text(text = stringResource(string.deck_edit_btn)) },
            onClick = onEditDeckClick
        )
    }
}
