package com.github.johnondrej.scratchcard.features.status

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.johnondrej.scratchcard.R
import com.github.johnondrej.scratchcard.shared.presentation.components.PrimaryButton

@Composable
fun StatusScreen(
    onOpenScratchScreen: () -> Unit,
    onOpenActivationScreen: () -> Unit
) {
    Column {
        Text(text = "Status")

        PrimaryButton(
            text = stringResource(id = R.string.screen_scratch),
            onClick = onOpenScratchScreen
        )

        PrimaryButton(
            text = stringResource(id = R.string.screen_activation),
            onClick = onOpenActivationScreen
        )
    }
}
