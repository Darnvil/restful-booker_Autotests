package tests

import config.BaseTest
import io.restassured.RestAssured.given
import org.hamcrest.Matchers.notNullValue
import kotlin.test.Test

class BookingTest: BaseTest() {

    @Test
    fun `get booking and check if name is not null`() {
        given()
            .log().ifValidationFails()
            .get("/booking/1")
            .then()
            .log().ifValidationFails()
            .statusCode(200)
            .body("firstname", notNullValue())
            .body("lastname", notNullValue())
    }
}