package com.beratyesbek.oneglobal.modal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceReadDTO {
    private String id;
    private String deviceName;
    private BrandReadDTO brand;
    private OffsetDateTime createdAt;
}
