package com.github.johnondrej.scratchcard.features.status.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.github.johnondrej.scratchcard.R
import com.github.johnondrej.scratchcard.shared.core.presentation.components.PrimaryButton
import com.github.johnondrej.scratchcard.shared.scratchcard.presentation.components.ScratchCardComponent
import org.koin.androidx.compose.koinViewModel

@Composable
fun StatusScreen(
    viewModel: StatusScreenViewModel = koinViewModel(),
    onOpenScratchScreen: () -> Unit,
    onOpenActivationScreen: () -> Unit
) {
    Column {
        val state by viewModel.uiStateStream.collectAsState()

        state.scratchCard?.let { scratchCard ->
            ScratchCardComponent(scratchCard = scratchCard)

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
}
