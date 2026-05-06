# API Test Framework (Restful Booker)

## Project description

API test framework for [Restful Booker](https://restful-booker.herokuapp.com)

## Stack

- Kotlin
- JUnit 5
- Rest Assured
- Jackson
- Gradle
- GitHub Actions

## How to launch

```bash
./gradlew test
```

## Tests coverage

- Authentication (token generation)
- Create booking (POST)
- Get booking (GET)
- Delete booking (DELETE)
- Negative scenarios (invalid auth, non-existing booking, unauthorized delete)

Retry mechanism is intentionally not enabled by default to avoid masking real test failures. There are no flaky tests or
external services so no need in implementing retries.

## Example test

```kotlin
@Test
fun `create booking and verify it can be retrieved`() {
    val booking = booking(firstname = "John")

    val createdBooking = createBooking(booking)

    val foundBooking = getBookingAsModel(createdBooking.bookingid)

    foundBooking.shouldMatch(booking)
}
```

## Project structure

- `api` — API client layer
- `models` — DTO models
- `assertions` — custom assertions
- `config` — configuration and Rest Assured specs
- `utils` — test data builders and test context
- `tests` — test scenarios

## CI

Tests are automatically executed using GitHub Actions on every push and pull request to main/master branches.

## Configuration

The framework uses a single public test environment configured in:

`src/test/resources/config/config.properties`

Multi-environment support is intentionally not enabled because the project targets one public demo API.