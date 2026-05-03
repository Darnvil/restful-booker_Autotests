package api

import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import models.AuthRequest
import models.AuthResponse

object AuthApi {
    fun authorize(authRequest: AuthRequest): AuthResponse =
        given()
            .log().ifValidationFails()
            .contentType(ContentType.JSON)
            .body(authRequest)
            .post("/auth")
            .then()
            .log().ifValidationFails()
            .statusCode(200)
            .extract().`as`(AuthResponse::class.java)

    fun getAuthToken(): String {
        val authRequest = AuthRequest("admin", "password123")

        return authorize(authRequest)
            .token ?: error("Token was not received")
    }
}