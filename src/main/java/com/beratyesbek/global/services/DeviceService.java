package com.beratyesbek.global.services;

import com.beratyesbek.global.exception.GlobalCustomException;
import com.beratyesbek.global.modal.dto.DeviceCreateDTO;
import com.beratyesbek.global.modal.dto.DeviceReadDTO;
import com.beratyesbek.global.modal.dto.DeviceUpdateDTO;
import com.beratyesbek.global.modal.entity.Brand;
import com.beratyesbek.global.modal.entity.Device;
import com.beratyesbek.global.modal.mapper.DeviceMapper;
import com.beratyesbek.global.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository repository;
    private final BrandService brandService;
    private final DeviceMapper deviceMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public DeviceReadDTO create(DeviceCreateDTO deviceCreateDTO) {
        Brand brand = brandService.findById(deviceCreateDTO.getBrandId());
        Device device = Device.builder()
                .deviceName(deviceCreateDTO.getDeviceName())
                .brand(brand)
                .build();

        return deviceMapper.mapTo(repository.save(device));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public DeviceReadDTO update(String id, DeviceUpdateDTO updateDTO) {
        Device device = repository.findById(id)
                .orElseThrow(() -> new GlobalCustomException("Device not found"));
        deviceMapper.mapTo(updateDTO, device);
        if (StringUtils.hasText(updateDTO.getBrandId())) {
            device.setBrand(brandService.findById(updateDTO.getBrandId()));
        }
        return deviceMapper.mapTo(repository.save(device));
    }

    public DeviceReadDTO getById(String id) {
        return repository.findById(id)
                .map(deviceMapper::mapTo)
                .orElseThrow(() -> new GlobalCustomException("Device not found"));
    }

    public List<DeviceReadDTO> getAll() {
        return deviceMapper.mapTo(repository.findAll());
    }

    public List<DeviceReadDTO> searchByNameOrBrand(String search) {
        return deviceMapper.mapTo(repository.search(search));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String id) {
        repository.deleteById(id);
    }


}
