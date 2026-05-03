package tests

import api.AuthApi.getAuthToken
import api.BookingApi.createBooking
import api.BookingApi.deleteBooking
import api.BookingApi.deleteBookingWithoutToken
import api.BookingApi.getBooking
import api.BookingApi.getBookingAsModel
import assertions.shouldMatch
import config.BaseTest
import utils.booking
import kotlin.test.Test
import kotlin.test.assertTrue

class BookingTest : BaseTest() {

    @Test
    fun `get booking and check if firstname and checkin not blank`() {
        val booking = getBookingAsModel(1)

        assertTrue(booking.firstname.isNotBlank())
        assertTrue(booking.bookingdates.checkin.isNotBlank())
    }

    @Test
    fun `get nonexistent booking returns 404`() {
        getBooking(999999999)
            .statusCode(404)
    }

    @Test
    fun `create booking and check if it was success`() {
        val booking = booking()

        val createdBooking = createBooking(booking)

        assertTrue(createdBooking.bookingid > 0)
        createdBooking.booking.shouldMatch(booking)
    }

    @Test
    fun `create booking and verify it can be retrieved`() {
        val booking = booking(firstname = "John")

        val createdBooking = createBooking(booking)

        val foundBooking = getBookingAsModel(createdBooking.bookingid)

        foundBooking.shouldMatch(booking)
    }

    @Test
    fun `delete booking and check if booking was not found`() {
        val booking = booking(firstname = "John")

        val token = getAuthToken()

        val createdBooking = createBooking(booking)

        deleteBooking(createdBooking.bookingid, token)

        getBooking(createdBooking.bookingid)
            .statusCode(404)
    }

    @Test
    fun `delete booking without token returns 403`() {
        val booking = booking(firstname = "John")

        val createdBooking = createBooking(booking)

        deleteBookingWithoutToken(createdBooking.bookingid)
            .statusCode(403)
    }
}