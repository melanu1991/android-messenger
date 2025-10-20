package com.uandcode.messenger.features.signin

/**
 * Minimal sign-in use case for the `signin` feature.
 */
class SignInUseCase {
    suspend fun invoke(username: String, password: String): Boolean {
        // placeholder logic — replace with real auth
        return username == "user" && password == "pass"
    }
}

