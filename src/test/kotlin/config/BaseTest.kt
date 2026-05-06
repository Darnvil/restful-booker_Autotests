package config


import api.AuthApi.getAuthToken
import api.BookingApi.deleteBooking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class BaseTest {

    @AfterEach
    fun cleanup() {
        runCatching {
            val token = getAuthToken()

            TestContext.getBookingIds().forEach { id ->
                runCatching {
                    deleteBooking(id, token)
                }
            }
        }
        TestContext.clearBookingIds()
    }
}