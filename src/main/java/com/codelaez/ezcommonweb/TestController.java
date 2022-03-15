package com.codelaez.ezcommonweb;

import com.codelaez.ezcommon.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author khanhspring
 */
@RestController
@RequestMapping(path = "test")
public class TestController {

    private final WebClient webClient;

    public TestController(@Lazy WebClient localClient) {
        this.webClient = localClient;
    }

    @GetMapping("ping1")
    public String ping1() {
        return "OK";
    }
    @GetMapping("ping2")
    public String ping2() {
        var resp = webClient.get()
                .uri("/ping1")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        if (resp.equals("OK")) {
            throw new ResourceNotFoundException();
        }
        return resp;
    }
}
