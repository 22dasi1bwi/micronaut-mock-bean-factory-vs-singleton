package com.example

import jakarta.inject.Singleton

@Singleton
class MyService(
    private val dependencyViaFactory: DependencyViaFactory,
    private val dependencyDirectlyViaSingleton: DependencyDirectlyViaSingleton
) {

    fun doStuffViaFactory(): String {
        return dependencyViaFactory.doMagic()
    }

    fun doStuffViaSingleton() : String {
        return dependencyDirectlyViaSingleton.doMagic()
    }
}
