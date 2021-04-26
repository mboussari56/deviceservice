package co.uk.wavesinsight.deviceservice.repository;


import co.uk.wavesinsight.deviceservice.Model.MeterDevice;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("Test")
public class DeviceRepositoryTest {

    @Autowired
    DeviceRepository deviceRepository;

    @Test
    @DisplayName("Save Device Test")
    public void testDeviceRegistration() {
        val device = MeterDevice.builder()
                .deviceName("Test Device")
                .build();

        val persistedDevice = deviceRepository.save(device);

        assertThat(persistedDevice).isNotNull();

    }
}
