package utils

import models.Booking
import models.BookingDates

fun createTestBooking(
    firstname: String = "firstname",
    lastname: String = "lastname",
    totalprice: Int = 100,
    depositpaid: Boolean = true,
    checkin: String = "2011-11-11",
    checkout: String = "2012-11-11",
    additionalneeds: String? = null,
): Booking = Booking(
    firstname,
    lastname,
    totalprice,
    depositpaid,
    BookingDates(checkin, checkout),
    additionalneeds
)