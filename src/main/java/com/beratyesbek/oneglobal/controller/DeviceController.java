package com.beratyesbek.oneglobal.controller;

import com.beratyesbek.oneglobal.modal.dto.DeviceCreateDTO;
import com.beratyesbek.oneglobal.modal.dto.DeviceReadDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beratyesbek.oneglobal.services.DeviceService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/device")
public class DeviceController {
    private final DeviceService deviceService;

    @PostMapping
    public ResponseEntity<DeviceReadDTO> create(@RequestBody @Valid DeviceCreateDTO createDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deviceService.create(createDTO));
    }
}
