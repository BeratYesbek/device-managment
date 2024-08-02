package com.beratyesbek.oneglobal.modal.mapper;

import com.beratyesbek.oneglobal.modal.dto.DeviceReadDTO;
import com.beratyesbek.oneglobal.modal.entity.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface DeviceMapper {

    @Named("mapTo")
    DeviceReadDTO mapTo(Device device);
}
