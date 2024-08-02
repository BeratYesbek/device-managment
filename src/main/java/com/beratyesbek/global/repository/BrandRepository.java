package com.beratyesbek.global.repository;

import com.beratyesbek.global.modal.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, String> {
}
