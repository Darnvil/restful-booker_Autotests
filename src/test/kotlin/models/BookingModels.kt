package models

data class BookingDates(
    val checkin: String,
    val checkout: String
)

data class Booking(
    val firstname: String,
    val lastname: String,
    val totalprice: Int,
    val depositpaid: Boolean,
    val bookingdates: BookingDates,
    val additionalneeds: String?
)