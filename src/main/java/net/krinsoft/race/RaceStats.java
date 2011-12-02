package net.krinsoft.race;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author krinsdeath
 */
public class RaceStats {
    private Map<Integer, Long> points = new HashMap<Integer, Long>();
    private long finish;

    public RaceStats() { }

    public void nextCheckpoint() {
        points.put(points.size(), System.currentTimeMillis());
    }

    public void setFinish() {
        this.finish = System.currentTimeMillis();
    }
}
