package com.estsoft.demo.controller;

import com.estsoft.demo.service.MemberService;
import com.estsoft.demo.repository.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @ResponseBody
    @GetMapping("/members")
    public List<Member> showMembers(){
        return memberService.getMemberAll();
    }

    @ResponseBody
    @PostMapping("/members")
    public Member saveMember(@RequestBody Member member){
        return memberService.insertMember(member);
    }
    @ResponseBody
    @GetMapping("members/{id}")
    public Optional<Member> getMemberById(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @ResponseBody
    @DeleteMapping("/members/{id}")
    public String deleteMemberById(@PathVariable Long id){
        memberService.deleteMemberById(id);
        return "삭제 성공";
    }

    //GET search/members?name=
    @GetMapping("search/members")
    @ResponseBody
    public List<Member> selectMemberByName(@RequestParam("name") String name){
        return memberService.selectMemberByName(name);
    }

    @GetMapping("/hi")
    public String htmlPage() {
        return "hi";
    }
}
