package co.uk.wavesinsight.deviceservice.repository;

import co.uk.wavesinsight.deviceservice.Model.Device;
import co.uk.wavesinsight.deviceservice.Model.MeterDevice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository  extends CrudRepository<MeterDevice, Integer> {

    Device findByDeviceMac(String mac);

}
