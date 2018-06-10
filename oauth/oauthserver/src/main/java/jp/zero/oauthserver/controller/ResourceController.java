package jp.zero.oauthserver.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
    @GetMapping(value = "/userinfo")
    Object userinfo(Authentication authentication) {
        return authentication;
    }
}
