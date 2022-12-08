package com.example.hospitalvisitor.service;

import com.example.hospitalvisitor.domain.Hospital;
import com.example.hospitalvisitor.domain.User;
import com.example.hospitalvisitor.domain.Visit;
import com.example.hospitalvisitor.domain.dto.VisitCreateRequest;
import com.example.hospitalvisitor.exception.AppException;
import com.example.hospitalvisitor.exception.ErrorCode;
import com.example.hospitalvisitor.repository.HospitalRepository;
import com.example.hospitalvisitor.repository.UserRepository;
import com.example.hospitalvisitor.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    private final VisitRepository visitRepository;
    public void createVisit(VisitCreateRequest request, String userName) {
        //hospital 없을 때 등록 불가
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new AppException(ErrorCode.HOSPITAL_NOT_FOUND,"등록가능한 병원이 아닙니다."));

        //user가 없을 때 등록 불가
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND,userName+"가 없습니다."));

        Visit visit = Visit.builder()
                .user(user)
                .hospital(hospital)
                .disease(request.getDisease())
                .amount(request.getAmount())
                .build();

        visitRepository.save(visit);
    }
}
