package hs.kr.equus.storage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class EquusStorageApplication

fun main(args: Array<String>) {
    runApplication<EquusStorageApplication>(*args)
}
