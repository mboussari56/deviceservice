package co.uk.wavesinsight.deviceservice.service;

import co.uk.wavesinsight.deviceservice.Model.MeterDevice;
import co.uk.wavesinsight.deviceservice.exception.AlreadyExistsException;
import co.uk.wavesinsight.deviceservice.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    private DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    /**
     * Saves new device
     * Also ensures devices does not exist\
     *
     * @param device @{@link MeterDevice}
     * @return @{@link MeterDevice}
     */
    public MeterDevice register(MeterDevice device) throws AlreadyExistsException {
        if (deviceRepository.findByDeviceMac(device.getDeviceMac()) != null) {
            throw new AlreadyExistsException();
        }
        return deviceRepository.save(device);
    }
}
