package com.beratyesbek.global.controller;

import com.beratyesbek.global.modal.dto.DeviceCreateDTO;
import com.beratyesbek.global.modal.dto.DeviceReadDTO;
import com.beratyesbek.global.modal.dto.DeviceUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.beratyesbek.global.services.DeviceService;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<DeviceReadDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(deviceService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<DeviceReadDTO>> getAll() {
        return ResponseEntity.ok(deviceService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<DeviceReadDTO>> searchByNameOrBrand(@RequestParam String search) {
        return ResponseEntity.ok(deviceService.searchByNameOrBrand(search));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceReadDTO> update(@PathVariable String id, @RequestBody @Valid DeviceUpdateDTO updateDTO) {
        return ResponseEntity.ok(deviceService.update(id, updateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deviceService.delete(id);
        return ResponseEntity.ok().build();
    }


}
