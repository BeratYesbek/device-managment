package com.beratyesbek.oneglobal.repository;

import com.beratyesbek.oneglobal.modal.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, String> {
    @Query("SELECT d FROM Device d WHERE " +
            "LOWER(d.deviceName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(d.brand.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Device> searchByNameOrBrand(@Param("search") String search);
}
