package co.uk.wavesinsight.deviceservice;


import co.uk.wavesinsight.deviceservice.Model.MeterDevice;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Objects;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "Test")
public class DeviceControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    int serverPort;

    @Test
    @DisplayName("Test Meter Registration")
    public void registerMeterTest(){
        //arrange
        val meter = MeterDevice.builder()
                .deviceId("12")
                .deviceName("Test")
                .registrationDate(LocalDate.now())
                .deviceMac("")
                .build();
        val url = "http://localhost:" +serverPort+"/v1/device/register";

        //act
        val response = testRestTemplate.postForEntity(url,meter,MeterDevice.class);

        //Assert
        assertEquals(response.getStatusCode(),HttpStatus.CREATED);
        assertEquals("12", Objects.requireNonNull(response.getBody()).getDeviceId());
        assertThat(response.getBody().getId()).isNotNull();

    }
}
