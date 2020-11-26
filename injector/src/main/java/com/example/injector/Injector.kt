package com.example.injector

interface Injector {
    fun inject(any : Any)
}

interface InjectorResolver {
    fun resolveInjector(): Injector
}