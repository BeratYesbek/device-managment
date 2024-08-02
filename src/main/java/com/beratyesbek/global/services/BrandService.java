package com.beratyesbek.global.services;

import com.beratyesbek.global.exception.GlobalCustomException;
import com.beratyesbek.global.modal.entity.Brand;
import com.beratyesbek.global.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository repository;

    public Brand findById(String id) {
        return repository.findById(id).orElseThrow(
                () -> new GlobalCustomException("Brand not found with id: " + id)
        );
    }
}
