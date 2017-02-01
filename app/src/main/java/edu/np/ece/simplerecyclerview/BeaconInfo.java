package edu.np.ece.simplerecyclerview;

/**
 * Created by zqi2 on 31/01/2017.
 */

public class BeaconInfo {
    private int serial;
    private String uuid;
    private int major, minor;

    public BeaconInfo() {
    }

    public BeaconInfo(String uuid, int major, int minor) {
        this.uuid = uuid;
        this.major = major;
        this.minor = minor;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }
}
