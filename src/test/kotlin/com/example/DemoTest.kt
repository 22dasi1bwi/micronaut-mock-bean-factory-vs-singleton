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
    fun mockDependencyViaFactory() = mockk<DependencyViaFactory>()

    @MockBean(DependencyDirectlyViaSingleton::class)
    fun mockDependencyViaSingleton() = mockk<DependencyDirectlyViaSingleton>()

    @Nested
    inner class BeanCreatedViaFactory {

        @Test
        fun `ignores mocked behavior`() {
            every { dependencyViaFactory.doMagic() } returns "mocked behavior"

            val result = service.doStuffViaFactory()

            assert(result == "mocked behavior") { "Expected `mocked behavior` but was `$result`" }
        }
    }

    @Nested
    inner class BeanCreatedDirectlyViaSingleton {

        @Test
        fun `applies mocked behavior`() {
            every { dependencyDirectlyViaSingleton.doMagic() } returns "mocked behavior"

            val result = service.doStuffViaSingleton()

            assert(result == "mocked behavior") { "Expected `mocked behavior` but was `$result`" }
        }
    }
}
