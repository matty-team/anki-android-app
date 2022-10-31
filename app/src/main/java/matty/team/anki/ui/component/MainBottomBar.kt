package matty.team.anki.ui.component

import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import matty.team.anki.R.string
import matty.team.anki.ui.horizontalScreenPadding
import matty.team.anki.ui.verticalScreenPadding

@Composable
fun MainBottomBar(
    onLearnButtonClick: () -> Unit,
    onAddCardButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = horizontalScreenPadding,
                end = horizontalScreenPadding,
                bottom = verticalScreenPadding
            ),
        verticalAlignment = CenterVertically,
        horizontalArrangement = End
    ) {
        ExtendedFloatingActionButton(
            onClick = onLearnButtonClick,
            containerColor = MaterialTheme.colorScheme.inversePrimary,
            modifier = Modifier.weight(1f),
            shape = FloatingActionButtonDefaults.smallShape
        ) {
            Text(text = stringResource(string.learn_btn))
            Icon(
                imageVector = Icons.Outlined.PlayArrow,
                contentDescription = stringResource(string.learn_btn),
            )
        }
        Spacer(modifier = Modifier.width(horizontalScreenPadding))
        FloatingActionButton(onClick = onAddCardButtonClick) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "",
            )
        }
    }
}
