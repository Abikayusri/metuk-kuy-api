package abika.sinau.metukkuyapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class TestController {
    @GetMapping("/ping")
    fun testPing(): String {
        return "OK"
    }
}