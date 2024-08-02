package com.beratyesbek.oneglobal.repository;

import com.beratyesbek.oneglobal.modal.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, String> {
}
