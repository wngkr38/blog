package com.estsoft.demo.controller;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.repository.MemberRepository;
import com.estsoft.demo.repository.Team;
import com.estsoft.demo.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        memberRepository.deleteAll();
    }

    @DisplayName("member 조회 검증")
    @Test
    void showMembers() throws Exception {
        // 2. given:
        Team team = teamRepository.findById(1L).orElseGet(Team::new);
        Member member = new Member("홍길동", team);
        Member savedMember = memberRepository.save(member);     // INSERT INTO member (id, name, team_id) VALUES (?, ?, ?);

        // 1. when: GET /members 호출
        ResultActions resultActions = mockMvc.perform(get("/members"));

        // 3. then: /members API 호출에 대한 응답 결과가 given절에서 넣어준 데이터와 같다는 검증
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()))
        ;
    }

    // GET /members/{id} -> member 단건 조회 API 검증하는 테스트 코드
    @Test
    public void showOneMember() throws Exception {
        // given: 회원 정보 생성
        Team team = teamRepository.findById(2L).orElseGet(Team::new);
        Member member = new Member("메시", team);
        Member saved = memberRepository.save(member);

        // when
        ResultActions resultActions = mockMvc.perform(get("/members/{id}", saved.getId()));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(saved.getId()))
                .andExpect(jsonPath("$.name").value(saved.getName()));
    }

    @Test
    public void saveMember() throws Exception {
        String content = """
                {
                  "name": "메시",
                  "team": {
                    "id": 1,
                    "name": "FC바르셀로나"
                  }
                }
                """;

        ResultActions resultActions = mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("메시"))
                .andExpect(jsonPath("$.teamDTO.teamId").value(1))
                .andExpect(jsonPath("$.teamDTO.name").value("FC바르셀로나"));
    }

}