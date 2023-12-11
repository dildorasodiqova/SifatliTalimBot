package com.example.web.controller;


import com.example.web.dto.request.UserLoginRequest;
import com.example.web.service.profileService.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/go/login")
    public String goLogin(Model model) {
        model.addAttribute("loginModel", new UserLoginRequest());
        model.addAttribute("isError", false);
        return "auth/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) new SecurityContextLogoutHandler().logout(request, response, auth);
        return "redirect:/auth/go/login";
    }
}
