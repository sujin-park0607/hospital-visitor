package com.example.hospitalvisitor.controller;

import com.example.hospitalvisitor.domain.User;
import com.example.hospitalvisitor.domain.dto.UserDto;
import com.example.hospitalvisitor.domain.dto.UserJoinRequest;
import com.example.hospitalvisitor.domain.dto.UserJoinResponse;
import com.example.hospitalvisitor.domain.dto.UserLoginRequest;
import com.example.hospitalvisitor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest){
        UserDto userDto = userService.join(userJoinRequest);
        return ResponseEntity.ok().body(new UserJoinResponse(userDto.getUserName(), userDto.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest){
        String token = userService.login(userLoginRequest.getUserName(), userLoginRequest.getPassword());
        return ResponseEntity.ok().body(token);
    }

}
