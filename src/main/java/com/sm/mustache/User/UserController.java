package com.sm.mustache.User;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    // 회원가입 폼
    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    // 회원가입 처리
    @PostMapping("/join")
    public String join(@RequestParam String username,
                       @RequestParam String password,
                       @RequestParam String email,
                       @RequestParam String name,
                       @RequestParam(required = false) String address,
                       @RequestParam(required = false) String phone,
                       Model model) {

        if(userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "이미 존재하는 아이디입니다.");
            return "user/join-form";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);  // 암호화 없이 저장 (나중에 교체 권장)
        user.setEmail(email);
        user.setName(name);
        user.setAddress(address);
        user.setPhone(phone);

        userRepository.save(user);

        return "redirect:/user/login-form";
    }

    // 로그인 폼
    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        User user = userRepository.findByUsername(username).orElse(null);

        if(user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "user/login-form";
        }

        session.setAttribute("sessionUser", user);

        return "redirect:/";
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
