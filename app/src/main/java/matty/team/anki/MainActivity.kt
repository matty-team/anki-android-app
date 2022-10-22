package matty.team.anki

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import matty.team.anki.ui.theme.AnkiTheme

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val (isCreationMenuExpanded, setCreationMenuExpanded) = remember { mutableStateOf(false) }
            AnkiTheme {
                Scaffold(floatingActionButton = {
                    CreationMenu(
                        isCreationMenuExpanded,
                        setCreationMenuExpanded,
                        onAddCardClick = { Log.d(TAG, "onCreate: onAddCardClicked") },
                        onAddDeckClick = { Log.d(TAG, "onCreate: onAddDeckClicked") })
                }) {

                }
            }
        }
    }
}