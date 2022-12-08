package com.example.hospitalvisitor.service;

import com.example.hospitalvisitor.domain.User;
import com.example.hospitalvisitor.domain.dto.UserDto;
import com.example.hospitalvisitor.domain.dto.UserJoinRequest;
import com.example.hospitalvisitor.repository.UserRepository;
import com.example.hospitalvisitor.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private long expireTime = 1000 * 60 * 60;
    @Value("${jwt.token.secret}")
    private String secretKey;

    public UserDto join(UserJoinRequest request) {
        //중복 아이디가 없는지 검사
        userRepository.findByUserName(request.getUserName())
                .ifPresent(user-> {
                    throw new RuntimeException("중복된 회원입니다");
                });
        User savedUser = userRepository.save(request.toEntity(encoder.encode(request.getPassword())));

        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .email(savedUser.getEmail())
                .build();
    }

    public String login(String userName, String password) {
        //userName이 있는지
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new RuntimeException("해당 userName이 없습니다"));

        //password가 같은지
        if(!encoder.matches(password, user.getPassword())){
            throw new RuntimeException("비밀번호 오류");
        }

        return JwtTokenUtil.createToken(user.getUserName(), secretKey, expireTime);
    }
}
