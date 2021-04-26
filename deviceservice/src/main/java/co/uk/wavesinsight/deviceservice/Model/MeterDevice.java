package co.uk.wavesinsight.deviceservice.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name ="meter_device")
@Getter
@Setter
@NoArgsConstructor
public class MeterDevice implements Device {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "device_id")
    @NotNull
    private String deviceId;
    @Column(name ="device_mac")
    @NotNull
    private String deviceMac;
    @Column(name ="device_name")
    @NotNull
    private String deviceName;
    @Column(name = "registration_date")
    @NotNull
    private LocalDate registrationDate;
    @Column(name = "ip_address")
    private String ipAdddres;

    @Builder()
    public MeterDevice(Integer id, @NotNull String deviceId, @NotNull String deviceMac, @NotNull String deviceName,
                       @NotNull LocalDate registrationDate, String ipAdddres) {
        this.id = id;
        this.deviceId = deviceId;
        this.deviceMac = deviceMac;
        this.deviceName = deviceName;
        this.registrationDate = registrationDate;
        this.ipAdddres = ipAdddres;
    }
}
