package config

import io.restassured.builder.RequestSpecBuilder
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.filter.log.LogDetail

val requestSpec = RequestSpecBuilder()
    .setBaseUri(Config.baseUri)
    .log(LogDetail.URI)
    .build()

val responseSpec = ResponseSpecBuilder()
    .build()