package fashionable.simba.yanawaserver.domain;

import fashionable.simba.yanawaserver.error.LevelSettingException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Level {
    private final double maximumLevel;
    private final double minimumLevel;

    public Level(double maximumLevel, double minimumLevel) {
        Set<Double> levels = new HashSet<>(
                Arrays.asList(0.0,0.5,1.0,1.5,2.0,2.5,3.0,3.5,4.0,4.5,5.0,5.5,6.0));
        if (minimumLevel > maximumLevel
                || !levels.contains(minimumLevel)
                || !levels.contains(maximumLevel)) {
            throw new LevelSettingException();
        }

        this.maximumLevel = maximumLevel;
        this.minimumLevel = minimumLevel;

    }
}
