package co.uk.wavesinsight.deviceservice.service;


import co.uk.wavesinsight.deviceservice.Model.MeterDevice;
import co.uk.wavesinsight.deviceservice.exception.AlreadyExistsException;
import co.uk.wavesinsight.deviceservice.repository.DeviceRepository;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {

    @Mock
    DeviceRepository deviceRepository;
    @InjectMocks
    DeviceService deviceService;

    @Test
    @DisplayName("Test device registration")
    public void deviceRegistrationTest() throws AlreadyExistsException {
        //Arrange
        val device = MeterDevice.builder()
                .deviceName("Test Device")
                .registrationDate(LocalDate.now())
                .ipAdddres("")
                .deviceId("12Test")
                .build();


        //Act
        val result = deviceService.register(device);

        //Assert
        assertEquals("12Test", result.getDeviceId());
        assertTrue(result.getId() > 1);
    }

    @Test
    @DisplayName("Test Existing device")
    public void testExisting() {

        //arrange
        val device = MeterDevice.builder()
                .deviceName("Test")
                .deviceMac("00:1A:C2:7B:00:47")
                .deviceId("123435T0")
                .build();
        when(deviceRepository.findByDeviceMac(any())).thenReturn(device);

        //act and assert
        assertThrows(AlreadyExistsException.class,() ->
            deviceService.register(device)
        );

        //assert
    }
}
