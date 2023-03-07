package com.github.johnondrej.scratchcard.shared.scratchcard.presentation.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.johnondrej.scratchcard.R
import com.github.johnondrej.scratchcard.shared.scratchcard.model.entities.domain.ScratchCard

@Composable
fun ScratchCardComponent(
    scratchCard: ScratchCard,
    modifier: Modifier = Modifier
) {
    val cardStateText = when (scratchCard) {
        is ScratchCard.New -> stringResource(id = R.string.status_new)
        is ScratchCard.Scratched -> stringResource(id = R.string.status_scratched, scratchCard.code)
        is ScratchCard.Activated -> stringResource(id = R.string.status_activated, scratchCard.code)
    }

    Text(
        text = stringResource(id = R.string.status_format, cardStateText),
        modifier = modifier
    )
}
