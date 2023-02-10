package kr.co.kmarket.service;

import kr.co.kmarket.entity.TermsEntity;
import kr.co.kmarket.repository.TermsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final TermsRepo termsRepo;

    public TermsEntity getTerms(){
        return termsRepo.findById(1).orElse(null);
    }
}
