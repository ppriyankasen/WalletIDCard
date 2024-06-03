package com.wallet.Idcard.controller;

import com.wallet.Idcard.model.LogEbtries;
import com.wallet.Idcard.model.RegistrationModel;
import com.wallet.Idcard.model.SerialNumbers;
import com.wallet.Idcard.service.IosWalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@RestController
public class WalletController {

    private static final Logger logger = LoggerFactory.getLogger(WalletController.class);

    @Autowired
    IosWalletService iosWalletService;

    @GetMapping(path="v1/devices/{deviceLibraryIdentifier}/registrations/{passTypeIdentifier}")
    public SerialNumbers getlist(
            @PathVariable String deviceLibraryIdentifier,
            @PathVariable String passTypeIdentifier,
            @RequestParam(required = false) String passesUpdatedSince
    ) {

        logger.info("Get updated List");

        logger.info("DeviceLibraryIdentifier: " + deviceLibraryIdentifier);
        logger.info("passTypeIdentifier: " + passTypeIdentifier);
        logger.info("passesUpdatedSince: " + passesUpdatedSince);
        SerialNumbers serialNumbers = new SerialNumbers();
        serialNumbers.setSerialNumbers(Arrays.asList("nmyuxofgna0.5926158093793123"));
        serialNumbers.setLastUpdated("2024-05-31T10:00:00Z");
        return serialNumbers;
    }

    @PostMapping(path="v1/devices/{deviceLibraryIdentifier}/registrations/{passTypeIdentifier}/{serialNumber}")
    public void registerWebserviceUrl(
            @PathVariable String deviceLibraryIdentifier,
            @PathVariable String passTypeIdentifier,
            @PathVariable String serialNumber,
            @RequestBody RegistrationModel request
    ) {
        logger.info("Register ID Card");

        logger.info("Push Token: " + request.getPushToken());
        logger.info("DeviceLibraryIdentifier: " + deviceLibraryIdentifier);
        logger.info("passTypeIdentifier: " + passTypeIdentifier);
        logger.info("serialNumber: " + serialNumber);
    }

    @DeleteMapping(path="v1/devices/{deviceLibraryIdentifier}/registrations/{passTypeIdentifier}/{serialNumber}")
    public void unregisterWebserviceUrl(
            @PathVariable String deviceLibraryIdentifier,
            @PathVariable String passTypeIdentifier,
            @PathVariable String serialNumber
    ) {

        logger.info("Un Register ID Card");

        logger.info("DeviceLibraryIdentifier: " + deviceLibraryIdentifier);
        logger.info("passTypeIdentifier: " + passTypeIdentifier);
        logger.info("serialNumber: " + serialNumber);
    }

    @GetMapping(path="v1/passes/{passTypeIdentifier}/{serialNumber}")
    public ResponseEntity<ByteArrayResource> sendUpdatedPass(
            @PathVariable String passTypeIdentifier,
            @PathVariable String serialNumber
    ) {

        logger.info("Send Updated Pass");

        logger.info("passTypeIdentifier: " + passTypeIdentifier);
        logger.info("serialNumber: " + serialNumber);

        byte[] pkpassByteArray = iosWalletService.getUpdatedPass();
        ByteArrayResource resource = new ByteArrayResource(pkpassByteArray);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
        String nowAsString = df.format(new Date());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=pass.pkpass");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.pkpass");
        headers.add(HttpHeaders.LAST_MODIFIED, nowAsString);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pkpassByteArray.length)
                .body(resource);
    }

    @PostMapping(path="/v1/log")
    public void getLogs(@RequestBody LogEbtries logEntries) {
        logger.info("logEntries: " + logEntries.toString());
    }

}
