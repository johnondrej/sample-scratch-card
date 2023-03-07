package com.github.johnondrej.scratchcard.features.scratch.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.github.johnondrej.scratchcard.R
import com.github.johnondrej.scratchcard.shared.core.presentation.components.PrimaryButton
import com.github.johnondrej.scratchcard.shared.scratchcard.presentation.components.ScratchCardComponent
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScratchScreen(
    viewModel: ScratchScreenViewModel = koinViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = dimensionResource(id = R.dimen.padding_default)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state by viewModel.uiStateStream.collectAsState()

        Spacer(modifier = Modifier.weight(1f))

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            CardContent(state = state, viewModel = viewModel)
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun CardContent(
    state: ScratchScreenState,
    viewModel: ScratchScreenViewModel
) {
    state.scratchCard?.let { scratchCard ->
        ScratchCardComponent(scratchCard = scratchCard)

        if (state.canScratch) {
            PrimaryButton(
                text = stringResource(id = R.string.scratch_action),
                onClick = { viewModel.scratch() }
            )
        } else {
            Text(
                text = stringResource(id = R.string.scratch_incorrect_state),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.padding_small))
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_default))
            )
        }
    }
}
