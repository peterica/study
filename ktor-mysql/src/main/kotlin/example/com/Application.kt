package example.com

import com.example.plugins.configureDatabases
import example.com.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureDI()
    configureRouting()
    configureDatabases()
    configureMonitoring()
    configureSerialization()
}
