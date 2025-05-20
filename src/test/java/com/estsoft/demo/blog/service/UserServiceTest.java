package com.estsoft.demo.blog.service;

import com.estsoft.demo.blog.domain.User;
import com.estsoft.demo.blog.dto.AddUserRequest;
import com.estsoft.demo.blog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Spy
    private BCryptPasswordEncoder encoder;

    // 회원 가입 로직 테스트
    @Test
    public void testSignUp() {
        // given:
        AddUserRequest request = new AddUserRequest();
        request.setEmail("mock_email");
        request.setPassword("mock_password");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        when(userRepository.save(any()))
                .thenReturn(new User(request.getEmail(), passwordEncoder.encode(request.getPassword())));

        // when:
        userService.signup(request);

        // then: 특정 메소드 호출 횟수 검증
        verify(userRepository, times(1)).save(any());
        verify(encoder, times(1)).encode(any());
    }
}