package com.example.web.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Admin on 14.12.2023
 * @project sifatli_talim_bot
 * @package com.example.web.util
 * @contact @sarvargo
 */
public class CookieUtil {
    @SneakyThrows
    public static void setCookieValue(String name, HttpServletRequest request, HttpServletResponse response, String value) {
        String contextPath = request.getContextPath();
        String path = request.getRequestURI().substring(contextPath.length());

        Cookie cookie = new Cookie(name, URLEncoder.encode(value, StandardCharsets.UTF_8));
        cookie.setPath(path);
        response.addCookie(cookie);
    }
}

