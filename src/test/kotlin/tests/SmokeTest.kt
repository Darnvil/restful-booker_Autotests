package tests

import config.BaseTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class SmokeTest : BaseTest() {

    @Test
    fun `project is working`() {
        assertTrue(true)
    }

    @Test
    fun `api is functional`() {
        given()
            .log().ifValidationFails()
            .get("/ping")
            .then()
            .statusCode(201)
    }
}