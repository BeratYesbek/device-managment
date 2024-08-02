package com.beratyesbek.global.controller

import com.beratyesbek.global.modal.dto.DeviceCreateDTO
import com.beratyesbek.global.modal.dto.DeviceReadDTO
import com.beratyesbek.global.modal.dto.DeviceUpdateDTO
import com.beratyesbek.global.services.DeviceService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

@ExtendWith(MockitoExtension::class)
class DeviceControllerTest {

    private lateinit var mockMvc: MockMvc
    private val deviceService: DeviceService = mock(DeviceService::class.java)
    private val objectMapper = ObjectMapper()
    private val deviceController = DeviceController(deviceService)

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build()
    }

    @Test
    fun `create device`() {
        val createDTO = mock(DeviceCreateDTO::class.java)
        val readDTO = mock(DeviceReadDTO::class.java)

        `when`(deviceService.create(createDTO)).thenReturn(readDTO)

        mockMvc.perform(post("/api/v1/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isCreated)
    }

    @Test
    fun `get device by id`() {
        val id = "1"
        val readDTO = mock(DeviceReadDTO::class.java)

        `when`(deviceService.getById(id)).thenReturn(readDTO)

        mockMvc.perform(get("/api/v1/device/$id"))
                .andExpect(status().isOk)
                .andExpect(content().json(objectMapper.writeValueAsString(readDTO)))
    }

    @Test
    fun `get all devices`() {
        val readDTOs = listOf(
                mock(DeviceReadDTO::class.java),
                mock(DeviceReadDTO::class.java)
        )

        `when`(deviceService.getAll()).thenReturn(readDTOs)

        mockMvc.perform(get("/api/v1/device"))
                .andExpect(status().isOk)
                .andExpect(content().json(objectMapper.writeValueAsString(readDTOs)))
    }

    @Test
    fun `search devices by name or brand`() {
        val searchQuery = "G34"
        val readDTOs = listOf(
                mock(DeviceReadDTO::class.java),
        )

        `when`(deviceService.searchByNameOrBrand(searchQuery)).thenReturn(readDTOs)

        mockMvc.perform(get("/api/v1/device/search")
                .param("search", searchQuery))
                .andExpect(status().isOk)
                .andExpect(content().json(objectMapper.writeValueAsString(readDTOs)))
    }

    @Test
    fun `update device`() {
        val id = "1"
        val updateDTO = mock(DeviceUpdateDTO::class.java)
        val readDTO = mock(DeviceReadDTO::class.java)

        `when`(deviceService.update(id, updateDTO)).thenReturn(readDTO)

        mockMvc.perform(put("/api/v1/device/$id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk)
    }

    @Test
    fun `delete device`() {
        val id = "1"

        doNothing().`when`(deviceService).delete(id)

        mockMvc.perform(delete("/api/v1/device/$id"))
                .andExpect(status().isOk)
    }
}