import com.beratyesbek.global.exception.GlobalCustomException
import com.beratyesbek.global.modal.dto.DeviceCreateDTO
import com.beratyesbek.global.modal.dto.DeviceReadDTO
import com.beratyesbek.global.modal.dto.DeviceUpdateDTO
import com.beratyesbek.global.modal.entity.Brand
import com.beratyesbek.global.modal.entity.Device
import com.beratyesbek.global.modal.mapper.DeviceMapper
import com.beratyesbek.global.repository.DeviceRepository
import com.beratyesbek.global.services.BrandService
import com.beratyesbek.global.services.DeviceService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class DeviceServiceTest {

    @Mock
    private lateinit var repository: DeviceRepository

    @Mock
    private lateinit var brandService: BrandService

    @Mock
    private lateinit var deviceMapper: DeviceMapper

    @InjectMocks
    private lateinit var deviceService: DeviceService


    @Test
    fun `create device`() {
        val deviceCreateDTO = mock(DeviceCreateDTO::class.java)
        val deviceReadDTO = mock(DeviceReadDTO::class.java)
        val device = mock(Device::class.java)
        val brand = mock(Brand::class.java)

        `when`(deviceCreateDTO.brandId).thenReturn("brand-id")
        `when`(brandService.findById(deviceCreateDTO.brandId)).thenReturn(brand)
        `when`(repository.save(any(Device::class.java))).thenReturn(device)
        `when`(deviceMapper.mapTo(any(Device::class.java))).thenReturn(deviceReadDTO)

        val result = deviceService.create(deviceCreateDTO)

        assertEquals(deviceReadDTO, result)
        verify(brandService).findById(deviceCreateDTO.brandId)
        verify(repository).save(any(Device::class.java))
        verify(deviceMapper).mapTo(any(Device::class.java))
    }

    @Test
    fun `update device`() {
        val deviceId = "1"
        val brandId = "2"

        val updateDTO = mock(DeviceUpdateDTO::class.java)
        val deviceReadDTO = mock(DeviceReadDTO::class.java)
        val brand = mock(Brand::class.java)
        val device = mock(Device::class.java)

        `when`(updateDTO.brandId).thenReturn(brandId)
        `when`(repository.findById(deviceId)).thenReturn(Optional.of(device))
        `when`(brandService.findById(brandId)).thenReturn(brand)
        `when`(repository.save(device)).thenReturn(device)
        doNothing().`when`(deviceMapper).mapTo(updateDTO,device)
        `when`(deviceMapper.mapTo(device)).thenReturn(deviceReadDTO)

        val result = deviceService.update(deviceId, updateDTO)

        assertEquals(deviceReadDTO, result)
        verify(repository).findById(deviceId)
        verify(brandService).findById(brandId)
        verify(repository).save(device)
        verify(deviceMapper).mapTo(device)
    }

    @Test
    fun `get device by id`() {
        val deviceId = "1"
        val deviceReadDTO = mock(DeviceReadDTO::class.java)
        val device = mock(Device::class.java)

        `when`(repository.findById(deviceId)).thenReturn(Optional.of(device))
        `when`(deviceMapper.mapTo(any(Device::class.java))).thenReturn(deviceReadDTO)

        val result = deviceService.getById(deviceId)

        assertEquals(deviceReadDTO, result)
        verify(repository).findById(deviceId)
        verify(deviceMapper).mapTo(any(Device::class.java))
    }

    @Test
    fun `get device by id throws exception`() {
        val deviceId = "1"

        `when`(repository.findById(deviceId)).thenReturn(Optional.empty())

        assertThrows(GlobalCustomException::class.java) {
            deviceService.getById(deviceId)
        }

        verify(repository).findById(deviceId)
    }

    @Test
    fun `delete device`() {
        val deviceId = "1"
        doNothing().`when`(repository).deleteById(deviceId)
        deviceService.delete(deviceId)

        verify(repository).deleteById(deviceId)
    }
}