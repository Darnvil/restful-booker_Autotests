package tests

import config.BaseTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import models.AuthRequest
import models.AuthResponse
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class AuthTest: BaseTest() {

    @Test
    fun `auth with correct password`() {
        val creds = AuthRequest("admin", "password123")

        val auth = given()
            .log().ifValidationFails()
            .contentType(ContentType.JSON)
            .body(creds)
            .post("/auth")
            .then()
            .statusCode(200)
            .extract()
            .`as`(AuthResponse::class.java)

        assertNotNull(auth.token)
        assertTrue(auth.token.isNotBlank())
    }

    @Test
    fun `auth with incorrect password`() {
        val creds = AuthRequest("admin", "pass")

        val auth = given()
            .log().ifValidationFails()
            .contentType(ContentType.JSON)
            .body(creds)
            .post("/auth")
            .then()
            .log().ifValidationFails()
            .statusCode(200)
            .extract()
            .`as`(AuthResponse::class.java)

        assertNull(auth.token)
        assertEquals("Bad credentials", auth.reason)
    }
}