package tests

import api.AuthApi.authorize
import config.BaseTest
import config.Config

import models.AuthRequest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class AuthTest: BaseTest() {

    @Test
    fun `auth with correct password`() {
        val creds = AuthRequest(Config.username, Config.password)

        val auth = authorize(creds)

        assertNotNull(auth.token)
        assertTrue(auth.token.isNotBlank())
    }

    @Test
    fun `auth with incorrect password`() {
        val creds = AuthRequest(Config.username, "123")

        val auth = authorize(creds)

        assertNull(auth.token)
        assertEquals("Bad credentials", auth.reason)
    }
}