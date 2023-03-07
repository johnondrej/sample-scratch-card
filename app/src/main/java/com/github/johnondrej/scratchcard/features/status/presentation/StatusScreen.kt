package com.github.johnondrej.scratchcard.features.status.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = dimensionResource(id = R.dimen.padding_default)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state by viewModel.uiStateStream.collectAsState()

        Spacer(modifier = Modifier.weight(1f))

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

        Spacer(modifier = Modifier.weight(1f))
    }
}
