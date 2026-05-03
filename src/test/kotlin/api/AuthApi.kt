package api

import config.Config
import config.requestSpec
import config.responseSpec
import io.qameta.allure.Step
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import models.AuthRequest
import models.AuthResponse

object AuthApi {

    @Step("Authenticate with username and password")
    fun authorize(authRequest: AuthRequest): AuthResponse =
        given()
            .spec(requestSpec)
            .contentType(ContentType.JSON)
            .body(authRequest)
            .post("/auth")
            .then()
            .spec(responseSpec)
            .log().ifValidationFails()
            .statusCode(200)
            .extract().`as`(AuthResponse::class.java)

    @Step("Get auth token")
    fun getAuthToken(): String {
        val authRequest = AuthRequest(Config.username, Config.password)

        return authorize(authRequest)
            .token ?: error("Token was not received")
    }
}