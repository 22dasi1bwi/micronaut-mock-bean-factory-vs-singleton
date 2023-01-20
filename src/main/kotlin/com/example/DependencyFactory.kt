package com.example

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory

@Factory
class DependencyFactory {

    @Bean
    fun dependency() = DependencyViaFactory()
}