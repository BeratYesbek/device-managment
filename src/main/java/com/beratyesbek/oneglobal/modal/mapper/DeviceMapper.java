package com.beratyesbek.oneglobal.modal.mapper;

import com.beratyesbek.oneglobal.modal.dto.DeviceReadDTO;
import com.beratyesbek.oneglobal.modal.dto.DeviceUpdateDTO;
import com.beratyesbek.oneglobal.modal.entity.Device;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = MapperConfig.class)
public interface DeviceMapper {

    @Named("mapTo")
    DeviceReadDTO mapTo(Device device);

    List<DeviceReadDTO> mapTo(List<Device> devices);

    @Named("mapTo")
    void mapTo(DeviceUpdateDTO deviceUpdateDTO, @MappingTarget Device device);
}
