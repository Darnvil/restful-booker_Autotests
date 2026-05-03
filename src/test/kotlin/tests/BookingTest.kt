package tests

import api.AuthApi.getAuthToken
import api.BookingApi.createBooking
import api.BookingApi.deleteBooking
import api.BookingApi.getBooking
import api.BookingApi.getBookingAsModel
import config.BaseTest
import models.Booking
import models.BookingDates
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
        val booking = Booking(
            "firstname",
            "lastname",
            100,
            true,
            BookingDates("2011-11-11", "2012-11-11"),
            null
        )

        val createdBooking = createBooking(booking)


        assertTrue(createdBooking.bookingid > 0)
        assertEquals(booking.firstname, createdBooking.booking.firstname)
        assertEquals(booking.lastname, createdBooking.booking.lastname)
        assertEquals(booking.totalprice, createdBooking.booking.totalprice)
    }

    @Test
    fun `create booking and check if created booking was found`() {
        val booking = Booking(
            "firstname",
            "lastname",
            150,
            true,
            BookingDates("2013-11-11", "2015-11-11"),
            null
        )

        val createdBooking = createBooking(booking)

        val foundBooking = getBookingAsModel(createdBooking.bookingid)

        assertEquals(createdBooking.booking.firstname, foundBooking.firstname)
        assertEquals(createdBooking.booking.lastname, foundBooking.lastname)
        assertEquals(createdBooking.booking.totalprice, foundBooking.totalprice)
        assertEquals(createdBooking.booking.bookingdates.checkin, foundBooking.bookingdates.checkin)
    }

    @Test
    fun `delete booking and check if booking was not found`() {
        val booking = Booking(
            "firstname",
            "lastname",
            150,
            true,
            BookingDates("2013-11-11", "2015-11-11"),
            null
        )

        val token = getAuthToken()

        val createdBooking = createBooking(booking)

       deleteBooking(createdBooking.bookingid, token)

        getBooking(createdBooking.bookingid)
            .statusCode(404)
    }
}