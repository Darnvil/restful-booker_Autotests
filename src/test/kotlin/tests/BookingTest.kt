package tests

import api.AuthApi.getAuthToken
import api.BookingApi.createBooking
import api.BookingApi.deleteBooking
import api.BookingApi.getBooking
import api.BookingApi.getBookingAsModel
import config.BaseTest
import utils.createTestBooking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BookingTest : BaseTest() {

    @Test
    fun `get booking and check if firstname and checkin not blank`() {
        val booking = getBookingAsModel(1)

        assertTrue(booking.firstname.isNotBlank())
        assertTrue(booking.bookingdates.checkin.isNotBlank())
    }

    @Test
    fun `create booking and check if success`() {
        val booking = createTestBooking()

        val createdBooking = createBooking(booking)

        assertTrue(createdBooking.bookingid > 0)
        assertEquals(booking.firstname, createdBooking.booking.firstname)
        assertEquals(booking.lastname, createdBooking.booking.lastname)
        assertEquals(booking.totalprice, createdBooking.booking.totalprice)
    }

    @Test
    fun `create booking and check if created booking was found`() {
        val booking = createTestBooking(totalprice = 100)

        val createdBooking = createBooking(booking)

        val foundBooking = getBookingAsModel(createdBooking.bookingid)

        assertEquals(booking.firstname, foundBooking.firstname)
        assertEquals(booking.lastname, foundBooking.lastname)
        assertEquals(booking.totalprice, foundBooking.totalprice)
        assertEquals(booking.bookingdates.checkin, foundBooking.bookingdates.checkin)
    }

    @Test
    fun `delete booking and check if booking was not found`() {
        val booking = createTestBooking(firstname = "John")

        val token = getAuthToken()

        val createdBooking = createBooking(booking)

        deleteBooking(createdBooking.bookingid, token)

        getBooking(createdBooking.bookingid)
            .statusCode(404)
    }
}