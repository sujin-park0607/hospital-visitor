package com.example.hospitalvisitor.service;

import com.example.hospitalvisitor.domain.User;
import com.example.hospitalvisitor.domain.dto.UserDto;
import com.example.hospitalvisitor.domain.dto.UserJoinRequest;
import com.example.hospitalvisitor.exception.AppException;
import com.example.hospitalvisitor.exception.ErrorCode;
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
                    throw new AppException(ErrorCode.DEPLICATED_USER_NAME, request.getUserName()+"은 중복된 아이디입니다.");
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
                .orElseThrow(()-> new AppException(ErrorCode.USERNAME_NOT_FOUND,userName+"이 가입된 적이 없습니다."));

        //password가 같은지
        if(!encoder.matches(password, user.getPassword())){
            throw new AppException(ErrorCode.INVALID_PASSWORD,"password가 일치하지 않습니다.");
        }

        return JwtTokenUtil.createToken(user.getUserName(), secretKey, expireTime);
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND,""));
    }
}
