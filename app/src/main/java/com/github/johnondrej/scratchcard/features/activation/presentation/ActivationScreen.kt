package com.github.johnondrej.scratchcard.features.activation.presentation

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
fun ActivationScreen(
    viewModel: ActivationScreenViewModel = koinViewModel()
) {
    Column {
        val state by viewModel.uiStateStream.collectAsState()

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            state.scratchCard?.let { scratchCard ->
                ScratchCardComponent(scratchCard = scratchCard)

                val code = state.scratchCard?.code
                if (state.canActivate && code != null) {
                    PrimaryButton(
                        text = stringResource(id = R.string.activate_action),
                        onClick = { viewModel.scratch(code) }
                    )
                }
            }
        }
    }
}
