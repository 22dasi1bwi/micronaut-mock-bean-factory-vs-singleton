package com.example

import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import jakarta.inject.Inject
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@MicronautTest
class DemoTest {

    @Inject
    private lateinit var service: MyService

    @Inject
    private lateinit var dependencyDirectlyViaSingleton: DependencyDirectlyViaSingleton

    @Inject
    private lateinit var dependencyViaFactory: DependencyViaFactory

    @MockBean(DependencyViaFactory::class)
    fun mockFactory() = mockk<DependencyViaFactory>()

    @MockBean(DependencyDirectlyViaSingleton::class)
    fun mockSingleton() = mockk<DependencyDirectlyViaSingleton>()

    @Nested
    inner class FailsForBeanCreatedByFactory {

        @Test
        fun `does not apply mock`() {
            every { dependencyViaFactory.doMagic() } returns "mocked behavior"

            val result = service.doStuffViaFactory()

            assert(result == "mocked behavior") { "Expected `mocked behavior` but was `$result`" }
        }
    }

    @Nested
    inner class SucceedsForBeanCreatedViaSingleton {

        @Test
        fun `does apply mock`() {
            every { dependencyDirectlyViaSingleton.doMagic() } returns "mocked behavior"

            val result = service.doStuffViaSingleton()

            assert(result == "mocked behavior") { "Expected `mocked behavior` but was `$result`" }
        }
    }
}
