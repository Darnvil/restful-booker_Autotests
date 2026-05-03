package api

import config.requestSpec
import config.responseSpec
import io.qameta.allure.Step
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import models.Booking
import models.BookingResponse

object BookingApi {

    @Step("Get raw booking response with id = {bookingid}")
    fun getBooking(bookingid: Int) =
        given()
            .spec(requestSpec)
            .get("/booking/$bookingid")
            .then()
            .spec(responseSpec)
            .log().ifValidationFails()

    @Step("Get booking with id = {bookingid}")
    fun getBookingAsModel(bookingid: Int): Booking =
        given()
            .spec(requestSpec)
            .get("/booking/$bookingid")
            .then()
            .spec(responseSpec)
            .log().ifValidationFails()
            .statusCode(200)
            .extract().`as`(Booking::class.java)

    @Step("Create booking")
    fun createBooking(booking: Booking): BookingResponse =
        given()
            .spec(requestSpec)
            .contentType(ContentType.JSON)
            .body(booking)
            .post("/booking")
            .then()
            .spec(responseSpec)
            .log().ifValidationFails()
            .statusCode(200)
            .extract().`as`(BookingResponse::class.java)

    @Step("Delete booking with id = {bookingid}")
    fun deleteBooking(bookingid: Int, token: String) =
        given()
            .spec(requestSpec)
            .cookie("token", token)
            .delete("/booking/${bookingid}")
            .then()
            .spec(responseSpec)
            .log().ifValidationFails()
            .statusCode(201)

    @Step("Try delete booking with id = {bookingid} without token")
    fun deleteBookingWithoutToken(bookingid: Int) =
        given()
            .spec(requestSpec)
            .delete("/booking/${bookingid}")
            .then()
            .spec(responseSpec)
            .log().ifValidationFails()

}