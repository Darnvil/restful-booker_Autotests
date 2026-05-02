package tests

import config.BaseTest
import io.restassured.RestAssured.given
import models.Booking
import org.hamcrest.Matchers.notNullValue
import kotlin.test.Test
import kotlin.test.assertEquals

class BookingTest: BaseTest() {

    @Test
    fun `get booking and check if firstname is Sally`() {
        val responce = given()
            .log().ifValidationFails()
            .get("/booking/1")
            .then()
            .log().ifValidationFails()
            .statusCode(200)
            .extract()
            .`as`(Booking::class.java)

        assertEquals("Sally", responce.firstname)
    }
}