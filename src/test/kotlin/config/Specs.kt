package config

import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.filter.log.LogDetail

val requestSpec = RequestSpecBuilder()
    .setBaseUri(Config.baseUri)
    .log(LogDetail.URI)
    .addFilter(AllureRestAssured())
    .build()

val responseSpec = ResponseSpecBuilder()
    .build()