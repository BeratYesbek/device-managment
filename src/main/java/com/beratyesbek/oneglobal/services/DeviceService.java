package com.beratyesbek.oneglobal.services;

import com.beratyesbek.oneglobal.exception.OneGlobalException;
import com.beratyesbek.oneglobal.modal.dto.DeviceCreateDTO;
import com.beratyesbek.oneglobal.modal.dto.DeviceReadDTO;
import com.beratyesbek.oneglobal.modal.dto.DeviceUpdateDTO;
import com.beratyesbek.oneglobal.modal.entity.Brand;
import com.beratyesbek.oneglobal.modal.entity.Device;
import com.beratyesbek.oneglobal.modal.mapper.DeviceMapper;
import com.beratyesbek.oneglobal.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

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

    public DeviceReadDTO update(String id, DeviceUpdateDTO updateDTO) {
        Device device = repository.findById(id)
                .orElseThrow(() -> new OneGlobalException("Device not found"));
        deviceMapper.mapTo(updateDTO, device);
        if (StringUtils.hasText(updateDTO.getBrandId())) {
            device.setBrand(brandService.findById(updateDTO.getBrandId()));
        }
        return deviceMapper.mapTo(repository.save(device));
    }

    public DeviceReadDTO getById(String id) {
        return repository.findById(id)
                .map(deviceMapper::mapTo)
                .orElseThrow(() -> new OneGlobalException("Device not found"));
    }

    public List<DeviceReadDTO> getAll() {
        return deviceMapper.mapTo(repository.findAll());
    }

    public List<DeviceReadDTO> searchByNameOrBrand(String search) {
        return deviceMapper.mapTo(repository.searchByNameOrBrand(search));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }


}
