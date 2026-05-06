package config

object TestContext {
    private val createdBookingIds = ThreadLocal.withInitial {
        mutableListOf<Int>()
    }

    fun addBookingId(id: Int) {
        createdBookingIds.get().add(id)
    }

    fun getBookingIds() =
        createdBookingIds.get().toList()

    fun clearBookingIds() {
        createdBookingIds.get().clear()
    }
}