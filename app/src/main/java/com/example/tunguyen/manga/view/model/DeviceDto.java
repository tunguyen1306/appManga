package com.example.tunguyen.manga.view.model;

/**
 * Created by TuNguyen on 01/21/2017.
 */

public class DeviceDto {

    public static String SerialDeviceRefer ;

    public int IdDevice ;
    public String SerialDevice ;
    public String ModelDevice ;
    public String ProductDevice ;
    public String ImeiDevice ;
    public String OsVersionDevice ;
    public int OSApiLevelDevice ;
    public String OsDevice ;

    public int getIdDevice() {
        return IdDevice;
    }

    public void setIdDevice(int idDevice) {
        IdDevice = idDevice;
    }

    public String getSerialDevice() {
        return SerialDevice;
    }

    public void setSerialDevice(String serialDevice) {
        SerialDevice = serialDevice;
    }

    public String getModelDevice() {
        return ModelDevice;
    }

    public void setModelDevice(String modelDevice) {
        ModelDevice = modelDevice;
    }

    public String getProductDevice() {
        return ProductDevice;
    }

    public void setProductDevice(String productDevice) {
        ProductDevice = productDevice;
    }

    public String getImeiDevice() {
        return ImeiDevice;
    }

    public void setImeiDevice(String imeiDevice) {
        ImeiDevice = imeiDevice;
    }

    public String getOsVersionDevice() {
        return OsVersionDevice;
    }

    public void setOsVersionDevice(String osVersionDevice) {
        OsVersionDevice = osVersionDevice;
    }

    public int getOSApiLevelDevice() {
        return OSApiLevelDevice;
    }

    public void setOSApiLevelDevice(int OSApiLevelDevice) {
        this.OSApiLevelDevice = OSApiLevelDevice;
    }

    public String getOsDevice() {
        return OsDevice;
    }

    public void setOsDevice(String osDevice) {
        OsDevice = osDevice;
    }
}
