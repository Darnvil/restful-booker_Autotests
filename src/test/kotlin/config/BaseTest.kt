package config

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import io.restassured.RestAssured.baseURI

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class BaseTest {

    @BeforeAll
    fun setup() {
        baseURI = Config.baseUri
    }
}