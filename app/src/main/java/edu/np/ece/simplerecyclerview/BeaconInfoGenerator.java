package edu.np.ece.simplerecyclerview;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * Created by zqi2 on 31/01/2017.
 */

public class BeaconInfoGenerator {

    public static final int MAJOR_MINOR_MAX = 65535;
    public static int lastId = 0;

    public static BeaconInfo getRandomBeacon() {
        BeaconInfo item = new BeaconInfo();
        item.setSerial(lastId++);
        item.setUuid(UUID.randomUUID().toString());
        Random random = new Random();
        item.setMajor(random.nextInt(MAJOR_MINOR_MAX + 1));
        item.setMinor(random.nextInt(MAJOR_MINOR_MAX + 1));
        return item;
    }

    public static void generateRandomBeacon(ArrayList<BeaconInfo> list, int count) {

        if (list == null) return;
        for (int i = 0; i < count; i++) {
            list.add(BeaconInfoGenerator.getRandomBeacon());
        }
    }
}
