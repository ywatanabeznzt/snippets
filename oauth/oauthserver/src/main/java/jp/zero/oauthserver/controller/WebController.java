package jp.zero.oauthserver.controller;

import jp.zero.oauthserver.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class WebController {
    private final static Logger logger = LoggerFactory.getLogger(WebController.class);
    class UserInfoResponseDto {
        private int userId;
        private String username;
        private String email;
        private int age;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
    @Autowired
    TokenStore tokenStore;

    @GetMapping(value = "/monitor")
    public String monitor(@RequestHeader Map<String, String> headers, Model model) {
        model.addAttribute("headers", headers);
        return "monitor";
    }

    @GetMapping(value = "/info")
    @ResponseBody
    public Object monitor(@RequestHeader(value = "Authorization") String authorizationHeader) {
        String accessToken = authorizationHeader.split(" ")[1];
        logger.info("アクセストークン[{}]からユーザ情報を取得", accessToken);
        Authentication authentication = tokenStore.readAuthentication(accessToken);
        UserInfoResponseDto response = new UserInfoResponseDto();
        BeanUtils.copyProperties(authentication.getPrincipal(), response);
        return response;
    }

    @GetMapping(value = "/login")
    public String login(@AuthenticationPrincipal User user) {
        if (user != null) {
            logger.info("ログイン済みユーザ[{}]がログインページにアクセス。/userinfoへリダイレクト", user.getUsername());
            return "redirect:/userinfo";
        }
        return "login";
    }
}
