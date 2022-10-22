package matty.team.anki

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CreationMenu(
    isExpanded: Boolean = false,
    onStateChanges: (Boolean) -> Unit = {},
    onAddCardClick: () -> Unit,
    onAddDeckClick: () -> Unit
) {
    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) 45f else 0f,
        animationSpec = tween(durationMillis = 150)
    )

    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        if (isExpanded) {
            MenuItem(text = stringResource(R.string.add_card), onClick = onAddCardClick)
            MenuItem(text = stringResource(R.string.create_deck), onClick = onAddDeckClick)
        }
        FloatingActionButton(
            onClick = {
                onStateChanges(!isExpanded)
            },
        ) {
            Icon(
                Icons.Filled.Add,
                modifier = Modifier.rotate(rotation),
                contentDescription = stringResource(R.string.add)
            )
        }
    }
}

@Composable
private fun MenuItem(
    text: String,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        )
    ) {
        Text(text = text)
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(FloatingActionButtonDefaults.shape)
                .background(MaterialTheme.colorScheme.inversePrimary)
                .clickable(interactionSource, indication = rememberRipple(), onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Filled.Add, contentDescription = text)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun CreationFabPreview() {
    val (isExpanded, setExpanded) = remember { mutableStateOf(true) }
    Scaffold(floatingActionButton = {
        CreationMenu(
            isExpanded,
            onStateChanges = setExpanded,
            onAddCardClick = {},
            onAddDeckClick = {})
    }) { }
}