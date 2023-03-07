package com.github.johnondrej.scratchcard.features.status.presentation

import com.github.johnondrej.scratchcard.shared.scratchcard.domain.ObserveScratchCardUseCase
import com.github.johnondrej.scratchcard.shared.scratchcard.model.entities.domain.ScratchCard
import com.github.johnondrej.scratchcard.testing.MainDispatcherRule
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class StatusScreenViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `should pass correct scratch card to screen state`() = runTest {
        val viewModelNew = viewModel()
        val scratched = ScratchCard.Scratched(code = 123)
        val viewModelScratched = viewModel(
            scratchCard = scratched
        )

        viewModelNew.uiStateStream.firstOrNull() shouldBe StatusScreenState(
            scratchCard = ScratchCard.New
        )
        viewModelScratched.uiStateStream.firstOrNull() shouldBe StatusScreenState(
            scratchCard = scratched
        )
    }

    private fun viewModel(
        scratchCard: ScratchCard = ScratchCard.New
    ) = StatusScreenViewModel(
        observeScratchCard = object : ObserveScratchCardUseCase {
            override fun invoke() = flowOf(scratchCard)
        }
    )
}
