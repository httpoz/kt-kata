package com.httpoz.ktkata.rest

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.apache.logging.log4j.kotlin.Logging

@RestController
@RequestMapping("/dummy", produces = [MediaType.APPLICATION_JSON_VALUE])
class DummyController(meterRegistry: MeterRegistry) {
    val counter: Counter = Counter.builder("dummy.hello")
        .description("Dummy hello counter")
        .register(meterRegistry)

    @RequestMapping("/hello")
    fun hello(): List<String> {
        counter.increment()
        logger.error("Hello not working")

        return listOf("Hello, world!")
    }

    companion object : Logging
}
