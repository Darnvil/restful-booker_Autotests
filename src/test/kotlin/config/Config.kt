package config

import java.lang.ClassLoader
import java.util.Properties

object Config {

    private val props = Properties().apply {

        val stream = ClassLoader
            .getSystemResourceAsStream("config.properties")
            ?: error("config.properties not found")

        load(stream)
    }

    private fun getRequiredProperty(key: String): String =
        props.getProperty(key) ?: error("Property '$key' not found")

    val baseUri = getRequiredProperty("baseUri")
    val username = getRequiredProperty("username")
    val password = getRequiredProperty("password")

}