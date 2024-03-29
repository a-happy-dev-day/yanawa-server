package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.error.LevelSettingException;

import javax.persistence.Embeddable;
import java.util.Arrays;
import java.util.HashSet;

@Embeddable
public class Level {
    private static final HashSet<Double> levels = new HashSet<>(
        Arrays.asList(0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0)
    );
    private double level;

    public Level(double level) {
        if (!levels.contains(level)) {
            throw new LevelSettingException("레벨은 0.0 ~ 6.0 사이의 0.5값 단위로 입력되어야 합니다.");
        }
        this.level = level;
    }

    protected Level() {/*no-op*/}

    public double getLevel() {
        return level;
    }
}
