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

## Example test

```kotlin
@Test
fun `create booking and verify it can be retrieved`() {
    val booking = booking(firstname = "John")

    val created = createBooking(booking)
    val found = getBookingAsModel(created.bookingid)

    found.shouldMatch(booking)
}
```

```md
## Project structure

- api — HTTP layer
- models — DTOs
- assertions — custom assertions
- utils — test data builders
- tests — test cases
```

## CI 

Tests are automatically executed using GitHub Actions on every push and pull request to main/master branches.