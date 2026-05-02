package tests

import config.BaseTest
import io.restassured.RestAssured.given
import models.Booking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class BookingTest: BaseTest() {

    @Test
    fun `get booking and check if firstname and checkin not blank`() {
        val booking = given()
            .log().ifValidationFails()
            .get("/booking/1")
            .then()
            .log().ifValidationFails()
            .statusCode(200)
            .extract()
            .`as`(Booking::class.java)

        assertTrue(booking.firstname.isNotBlank())
        assertTrue(booking.bookingdates.checkin.isNotBlank())
    }
}