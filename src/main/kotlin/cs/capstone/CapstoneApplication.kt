package cs.capstone

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class CapstoneApplication

fun main(args: Array<String>) {
	runApplication<CapstoneApplication>(*args)
}
