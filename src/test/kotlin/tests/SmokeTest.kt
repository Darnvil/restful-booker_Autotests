package tests

import config.BaseTest
import config.requestSpec
import config.responseSpec
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
            .spec(requestSpec)
            .get("/ping")
            .then()
            .spec(responseSpec)
            .statusCode(201)
    }
}