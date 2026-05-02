package tests

import config.BaseTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import models.Booking
import models.BookingDates
import models.BookingResponse
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class BookingTest : BaseTest() {

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

    @Test
    fun `create booking and check if success`() {
        val booking = Booking(
            "firstname",
            "lastname",
            100,
            true,
            BookingDates("2011-11-11", "2012-11-11"),
            null
        )

        val createdBooking = given()
            .log().ifValidationFails()
            .contentType(ContentType.JSON)
            .body(booking)
            .post("/booking")
            .then()
            .log().ifValidationFails()
            .statusCode(200)
            .extract().`as`(BookingResponse::class.java)

        assertTrue(createdBooking.bookingid > 0)
        assertEquals(booking.firstname, createdBooking.booking.firstname)
        assertEquals(booking.lastname, createdBooking.booking.lastname)
        assertEquals(booking.totalprice, createdBooking.booking.totalprice)
    }
}