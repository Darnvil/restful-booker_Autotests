package api

import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import models.Booking
import models.BookingResponse

object BookingApi {

    fun getBooking(bookingid: Int) =
        given()
            .log().ifValidationFails()
            .get("/booking/$bookingid")
            .then()
            .log().ifValidationFails()

    fun getBookingAsModel(bookingid: Int): Booking =
        given()
            .log().ifValidationFails()
            .get("/booking/$bookingid")
            .then()
            .log().ifValidationFails()
            .statusCode(200)
            .extract().`as`(Booking::class.java)

    fun createBooking(booking: Booking): BookingResponse =
        given()
            .log().ifValidationFails()
            .contentType(ContentType.JSON)
            .body(booking)
            .post("/booking")
            .then()
            .log().ifValidationFails()
            .statusCode(200)
            .extract().`as`(BookingResponse::class.java)

    fun deleteBooking(bookingid: Int, token: String) =
        given()
            .log().ifValidationFails()
            .cookie("token", token)
            .delete("/booking/${bookingid}")
            .then()
            .log().ifValidationFails()
            .statusCode(201)

    fun deleteBookingWithoutToken(bookingid: Int) =
        given()
            .log().ifValidationFails()
            .delete("/booking/${bookingid}")
            .then()
            .log().ifValidationFails()

}