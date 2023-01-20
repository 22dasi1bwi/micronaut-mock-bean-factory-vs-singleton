package com.example

import jakarta.inject.Singleton

@Singleton
class DependencyDirectlyViaSingleton {

    fun doMagic() = "<<<Magic-From-Singleton>>>"
}