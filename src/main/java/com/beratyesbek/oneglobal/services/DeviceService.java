package com.beratyesbek.oneglobal.services;

import com.beratyesbek.oneglobal.modal.dto.DeviceCreateDTO;
import com.beratyesbek.oneglobal.modal.dto.DeviceReadDTO;
import com.beratyesbek.oneglobal.modal.entity.Brand;
import com.beratyesbek.oneglobal.modal.entity.Device;
import com.beratyesbek.oneglobal.modal.mapper.DeviceMapper;
import com.beratyesbek.oneglobal.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository repository;
    private final BrandService brandService;
    private final DeviceMapper deviceMapper;

    public DeviceReadDTO create(DeviceCreateDTO deviceCreateDTO) {
        Brand brand = brandService.findById(deviceCreateDTO.getBrandId());
        Device device = Device.builder()
                .deviceName(deviceCreateDTO.getDeviceName())
                .brand(brand)
                .build();

        return deviceMapper.mapTo(repository.save(device));
    }
}
