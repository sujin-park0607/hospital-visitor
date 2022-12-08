package com.example.hospitalvisitor.controller;

import com.example.hospitalvisitor.domain.dto.VisitCreateRequest;
import com.example.hospitalvisitor.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
@Slf4j
public class VisitController {

    private final VisitService visitService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody VisitCreateRequest request, Authentication authentication){
        String userName = authentication.getName();
        log.info("token userName:{}", userName);
        visitService.createVisit(request, userName);
        return ResponseEntity.ok().body("방문 등록 성공!");
    }
}
