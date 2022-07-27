package com.barros

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        configureRouting()
    }.start(wait = true)
}

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/movie") {
            val response = listOf(
                Movie(
                    id = 1,
                    title = "The Shawshank Redemption",
                    overview = "",
                    releaseDate = "1994-10-14",
                    posterPath = "/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg",
                    voteAverage = 8.7,
                    popularity = 7.5
                ),
                Movie(
                    id = 2,
                    title = "The Godfather",
                    overview = "",
                    releaseDate = "1972-03-14",
                    posterPath = "/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg",
                    voteAverage = 9.3,
                    popularity = 9.5
                )
            )
            call.respond(response)
        }
    }
}