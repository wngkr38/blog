package com.estsoft.demo.controller;

import com.estsoft.demo.dto.MemberRequest;
import com.estsoft.demo.repository.Member;
import com.estsoft.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @ResponseBody
    @GetMapping("/members")
    public List<MemberDTO> showMembers() {
        List<Member> memberAll = memberService.getMemberAll();
        return memberAll.stream()
                .map(MemberDTO::new)
                .toList();
    }

    @ResponseBody
    @PostMapping("/members")            // POST /members 회원정보 저장 API
    public MemberDTO saveMember(@RequestBody MemberRequest member) {
        Member savedMember = memberService.insertMember(member.toEntity());
        return new MemberDTO(savedMember);
    }

    // GET /members/{id} -> member 단건 조회
    @ResponseBody
    @GetMapping("/members/{id}")
    public MemberDTO selectMemberById(@PathVariable Long id) {
        Member member = memberService.selectMemberById(id);
        return new MemberDTO(member);
    }


    // DELETE /members/{id}  -> member 단건 삭제
    @ResponseBody
    @DeleteMapping("/members/{id}")
    public String deleteMemberById(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return "OK";
    }

    // GET /search/members?name=---
    @GetMapping("/search/members")
    @ResponseBody
    public List<Member> selectMemberByName(@RequestParam("name") String name) {
        return memberService.selectMemberByName(name);
    }


    @GetMapping("/hi")
    public String htmlPage() {
        return "hi";        // hi.html
    }

}
