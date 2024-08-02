package com.beratyesbek.oneglobal.services;

import com.beratyesbek.oneglobal.modal.entity.Brand;
import com.beratyesbek.oneglobal.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository repository;

    public Brand findById(String id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Brand not found with id: " + id)
        );
    }
}
