package com.example.mall.web;

import com.example.mall.domain.members.Member;
import com.example.mall.dto.MemberFormDto;
import com.example.mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private final MemberService memberService;
    private  final PasswordEncoder passwordEncoder;

    @GetMapping(value="/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping(value = "/new")
//   post 요청으로 넘어온 회원가입 정보를 MemberFormDto 객체로 받음
    //검증하려는 객체 앞에 @Valid 추가. 검사 후 결과는 bindingResult에 담김
    public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
        //에러가 있으면 회원가입 페이지로 이동
        if (bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/";
    }
}
