package co.uk.wavesinsight.deviceservice.controller;


import co.uk.wavesinsight.deviceservice.Model.MeterDevice;
import co.uk.wavesinsight.deviceservice.exception.AlreadyExistsException;
import co.uk.wavesinsight.deviceservice.service.DeviceService;
import co.uk.wavesinsight.deviceservice.type.AppVersion;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("{version}/device")
public class DeviceRestController {

    private DeviceService deviceService;

    @Autowired
   public  DeviceRestController(DeviceService deviceService){
        this.deviceService = deviceService;
    }


    @PostMapping(value="/register",consumes = APPLICATION_JSON_VALUE,produces=APPLICATION_JSON_VALUE)
    public ResponseEntity<MeterDevice> registerDevice(@PathVariable AppVersion version, @Valid @RequestBody MeterDevice device) throws AlreadyExistsException {
        val response  = deviceService.register(device);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
