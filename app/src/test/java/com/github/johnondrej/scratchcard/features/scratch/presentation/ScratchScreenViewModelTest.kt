package com.github.johnondrej.scratchcard.features.scratch.presentation

import com.github.johnondrej.scratchcard.features.scratch.domain.ScratchCardUseCase
import com.github.johnondrej.scratchcard.shared.scratchcard.domain.ObserveScratchCardUseCase
import com.github.johnondrej.scratchcard.shared.scratchcard.model.entities.domain.ScratchCard
import com.github.johnondrej.scratchcard.testing.MainDispatcherRule
import io.kotest.matchers.shouldBe
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ScratchScreenViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `should load initial state`() = runTest {
        val viewModel = viewModel()

        viewModel.uiStateStream.firstOrNull() shouldBe ScratchScreenState(
            scratchCard = ScratchCard.New,
            isLoading = false
        )
    }

    @Test
    fun `should scratch card`() = runTest {
        val scratchUseCase: ScratchCardUseCase = mockk()
        val viewModel = viewModel(scratchUseCase = scratchUseCase)

        viewModel.scratch()

        coVerify {
            scratchUseCase.invoke()
        }
    }

    private fun viewModel(
        scratchUseCase: ScratchCardUseCase = mockk(relaxUnitFun = true),
        scratchCard: ScratchCard = ScratchCard.New
    ) = ScratchScreenViewModel(
        scratchCard = scratchUseCase,
        observeScratchCard = object : ObserveScratchCardUseCase {
            override fun invoke() = flowOf(scratchCard)
        }
    )
}
