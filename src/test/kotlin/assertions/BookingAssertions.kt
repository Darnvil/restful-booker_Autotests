package assertions

import io.qameta.allure.Step
import models.Booking

import kotlin.test.assertEquals

@Step("Get booking with id = {bookingId}")
fun Booking.shouldMatch(expected: Booking) {
    assertEquals(expected.firstname, this.firstname, "firstname mismatch")
    assertEquals(expected.lastname, this.lastname,  "lastname mismatch")
    assertEquals(expected.totalprice, this.totalprice, "totalprice mismatch")
    assertEquals(expected.bookingdates.checkin, this.bookingdates.checkin, "checkin mismatch")
}