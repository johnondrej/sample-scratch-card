package com.github.johnondrej.scratchcard.shared.scratchcard.data

import com.github.johnondrej.scratchcard.shared.core.networking.ApiService
import com.github.johnondrej.scratchcard.shared.core.networking.model.api.VersionResponse
import com.github.johnondrej.scratchcard.shared.scratchcard.model.entities.domain.ScratchCard
import com.github.johnondrej.scratchcard.shared.scratchcard.model.entities.domain.ScratchCardActivationResult
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.beInstanceOf
import io.kotest.matchers.types.instanceOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ScratchCardRepositoryImplTest {

    @Test
    fun `should return new card by default`() = runTest {
        val repository = repository()

        repository.observeScratchCard().firstOrNull() shouldBe ScratchCard.New
    }

    @Test
    fun `should scratch card`() = runTest {
        val repository = repository()

        repository.scratchCard()

        repository.observeScratchCard().firstOrNull() should beInstanceOf<ScratchCard.Scratched>()
    }

    @Test
    fun `should activate card`() = runTest {
        val repository = repository()

        repository.scratchCard()
        val result = repository.activateCard(code = 123)

        result shouldBe ScratchCardActivationResult.Success
        repository.observeScratchCard().firstOrNull() shouldBe ScratchCard.Activated(code = 123)
    }

    @Test
    fun `should not activate card on error`() = runTest {
        val repository = repository(
            versionResponse = VersionResponse(
                android = ACTIVATION_ERROR
            )
        )

        val result = repository.activateCard(code = 123)

        result shouldBe ScratchCardActivationResult.Error
        repository.observeScratchCard().firstOrNull() shouldNotBe instanceOf<ScratchCard.Activated>()
    }

    private fun repository(
        versionResponse: VersionResponse = VersionResponse(
            android = ACTIVATION_SUCCESS
        )
    ) = ScratchCardRepositoryImpl(
        apiService = object : ApiService {
            override suspend fun getVersion(code: Int): VersionResponse {
                return versionResponse
            }
        }
    )

    companion object {

        private const val ACTIVATION_SUCCESS = "90000"
        private const val ACTIVATION_ERROR = "42"
    }
}
