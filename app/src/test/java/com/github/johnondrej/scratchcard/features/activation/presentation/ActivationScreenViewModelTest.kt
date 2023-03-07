package com.github.johnondrej.scratchcard.features.activation.presentation

import com.github.johnondrej.scratchcard.features.activation.domain.ActivateCardUseCase
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
class ActivationScreenViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `should load initial state`() = runTest {
        val viewModel = viewModel()

        viewModel.uiStateStream.firstOrNull() shouldBe ActivationScreenState(
            scratchCard = ScratchCard.New,
            isLoading = false
        )
    }

    @Test
    fun `should activate card`() = runTest {
        val activateUseCase: ActivateCardUseCase = mockk()
        val viewModel = viewModel(activateUseCase = activateUseCase)
        val code = 321

        viewModel.activate(code)

        coVerify {
            activateUseCase.invoke(code)
        }
    }

    private fun viewModel(
        activateUseCase: ActivateCardUseCase = mockk(relaxUnitFun = true),
        scratchCard: ScratchCard = ScratchCard.New
    ) = ActivationScreenViewModel(
        activateCard = activateUseCase,
        observeScratchCard = object : ObserveScratchCardUseCase {
            override fun invoke() = flowOf(scratchCard)
        }
    )
}
