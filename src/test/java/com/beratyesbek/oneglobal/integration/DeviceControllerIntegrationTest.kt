package com.beratyesbek.oneglobal.integration


import com.beratyesbek.oneglobal.controller.DeviceController
import com.beratyesbek.oneglobal.modal.dto.DeviceCreateDTO
import com.beratyesbek.oneglobal.modal.dto.DeviceUpdateDTO
import com.beratyesbek.oneglobal.modal.entity.Brand
import com.beratyesbek.oneglobal.modal.entity.Device
import com.beratyesbek.oneglobal.repository.BrandRepository
import com.beratyesbek.oneglobal.repository.DeviceRepository
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension


@ActiveProfiles("test-containers")
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
open class DeviceControllerIntegrationTest {

    @Autowired
    private lateinit var deviceController: DeviceController

    @Autowired
    private lateinit var brandRepository: BrandRepository

    @Autowired
    private lateinit var deviceRepository: DeviceRepository

    private lateinit var brand: Brand


    @BeforeEach
    fun setUp() {
        deviceRepository.deleteAll()
        brand = Brand.builder().name("Brand A").build()
        brandRepository.save(brand)
    }

    @Test
    fun `create device`() {
        val createDTO = DeviceCreateDTO.builder()
                .deviceName("Device A")
                .brandId(brand.id)
                .build()

        val response = deviceController.create(createDTO)

        assert(response.statusCode == HttpStatus.CREATED)
    }


    @Test
    fun `get device by id`() {
        val device = Device.builder()
                .deviceName("Device A")
                .brand(brand)
                .build()
        deviceRepository.save(device)

        val response = deviceController.getById(device.id)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(device.deviceName, response.body?.deviceName)
    }

    @Test
    fun `get all devices`() {
        val device1 = Device.builder()
                .deviceName("Device A")
                .brand(brand)
                .build()
        val device2 = Device.builder()
                .deviceName("Device B")
                .brand(brand)
                .build()
        deviceRepository.saveAll(listOf(device1, device2))

        val response = deviceController.getAll()

        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `search devices by name or brand`() {
        val device = Device.builder()
                .deviceName("Device A")
                .brand(brand)
                .build()
        deviceRepository.save(device)

        val response = deviceController.searchByNameOrBrand("Device A")

        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `update device`() {
        val device = Device.builder()
                .deviceName("Device A")
                .brand(brand)
                .build()
        deviceRepository.save(device)

        val updateDTO = DeviceUpdateDTO.builder()
                .deviceName("Updated Device A")
                .build()

        val response = deviceController.update(device.id, updateDTO)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals("Updated Device A", response.body?.deviceName)
    }

    @Test
    fun `delete device`() {
        val device = Device.builder()
                .deviceName("Device A")
                .brand(brand)
                .build()
        deviceRepository.save(device)

        val response = deviceController.delete(device.id)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(false, deviceRepository.findById(device.id).isPresent)
    }

}