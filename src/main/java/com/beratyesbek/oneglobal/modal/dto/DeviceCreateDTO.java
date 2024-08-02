package com.beratyesbek.oneglobal.modal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceCreateDTO {
    private String deviceName;
    private String brandId;
}
