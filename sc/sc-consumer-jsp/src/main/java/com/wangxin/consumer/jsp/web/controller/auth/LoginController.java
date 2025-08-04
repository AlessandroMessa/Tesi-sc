package com.wangxin.consumer.jsp.web.controller.auth;

import com.wangxin.consumer.api.auth.LoginPort;
import com.wangxin.consumer.api.auth.AuthenticationException;
import com.wangxin.consumer.api.auth.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final LoginPort loginPort;

    @Autowired
    public LoginController(LoginPort loginPort) {
        this.loginPort = loginPort;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "view/login/login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute("loginRequest") LoginRequest form,
                          RedirectAttributes redirectAttributes) {
        if (form.getUsername() == null || form.getUsername().isEmpty()
                || form.getPassword() == null || form.getPassword().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Account o password mancanti");
            return "redirect:/login";
        }

        try {
            loginPort.login(form);
            log.info("Utente {} autenticato", form.getUsername());
            return "redirect:/index";
        } catch (AuthenticationException ex) {
            log.error("Login fallito: {}", ex.getMessage());
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        loginPort.logout();
        return "view/login/login";
    }

    @GetMapping("/403")
    public String unauthorizedRole() {
        log.info("Accesso negato");
        return "403";
    }
}
