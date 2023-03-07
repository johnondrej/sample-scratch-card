package com.github.johnondrej.scratchcard.features.scratch.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.github.johnondrej.scratchcard.R
import com.github.johnondrej.scratchcard.shared.core.presentation.components.PrimaryButton
import com.github.johnondrej.scratchcard.shared.scratchcard.presentation.components.ScratchCardComponent
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScratchScreen(
    viewModel: ScratchScreenViewModel = koinViewModel()
) {
    Column {
        val state by viewModel.uiStateStream.collectAsState()

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            state.scratchCard?.let { scratchCard ->
                ScratchCardComponent(scratchCard = scratchCard)

                if (state.canScratch) {
                    PrimaryButton(
                        text = stringResource(id = R.string.scratch_action),
                        onClick = { viewModel.scratch() }
                    )
                }
            }
        }
    }
}
