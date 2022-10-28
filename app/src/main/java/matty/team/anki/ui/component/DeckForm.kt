package matty.team.anki.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.delay
import matty.team.anki.R.string
import matty.team.anki.data.DeckConstraints
import matty.team.anki.data.deckColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckForm(
    onDone: () -> Unit,
    formState: DeckFormState
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        delay(500)
        focusRequester.requestFocus()
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        OutlinedTextField(
            value = formState.nameField,
            onValueChange = { fieldValue ->
                if (fieldValue.text.length <= DeckConstraints.deckNameMaxLength) {
                    formState.nameField = fieldValue
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            label = {
                Text(text = stringResource(string.name_label))
            },
            keyboardActions = KeyboardActions(onDone = { onDone() }),
            singleLine = true
        )
        FlowRow(
            mainAxisSpacing = 16.dp,
            crossAxisSpacing = 16.dp
        ) {
            deckColors.forEach { color ->
                DeckColorItem(
                    color,
                    onClick = { formState.color = color },
                    isSelected = color == formState.color
                )
            }
        }
    }
}

@Composable
private fun DeckColorItem(color: Long, onClick: () -> Unit, isSelected: Boolean) {
    val interactionSource = remember { MutableInteractionSource() }
    val outlineColor = MaterialTheme.colorScheme.primaryContainer
    val colorRadiusDivider by animateFloatAsState(targetValue = if (isSelected) 2.5f else 2f)

    Box(contentAlignment = Center) {
        Canvas(modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            ),
            onDraw = {
                if (isSelected) {
                    drawCircle(
                        radius = size.width / 2f,
                        color = outlineColor
                    )
                }
                drawCircle(
                    radius = size.width / colorRadiusDivider,
                    color = Color(color).copy(alpha = .8f)
                )
            }
        )
        if (isSelected) {
            Icon(
                Icons.Default.Check,
                contentDescription = stringResource(string.checked_deck_color_description),
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.onTertiary
            )
        }
    }
}
