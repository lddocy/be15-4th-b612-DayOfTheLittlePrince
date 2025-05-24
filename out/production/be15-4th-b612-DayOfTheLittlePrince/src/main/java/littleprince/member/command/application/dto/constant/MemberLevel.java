package littleprince.member.command.application.dto.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum MemberLevel {

    LEVEL_1(1, 10),
    LEVEL_2(2, 30),
    LEVEL_3(3, 60),
    LEVEL_4(4, 100),
    LEVEL_5(5, 150),
    LEVEL_6(6, 210),
    LEVEL_7(7, 280),
    LEVEL_8(8, 360),
    LEVEL_9(9, 460),
    LEVEL_10(10, 600);

    private final int level;
    private final int totalRequiredExp;

    MemberLevel(int level, int totalRequiredExp) {
        this.level = level;
        this.totalRequiredExp = totalRequiredExp;
    }

    public int getLevel() {
        return level;
    }

    public int getTotalRequiredExp() {
        return totalRequiredExp;
    }

    public static Optional<MemberLevel> fromLevel(int level) {
        return Arrays.stream(values())
                .filter(e -> e.level == level)
                .findFirst();
    }

    public static int getTotalExpByLevel(int level) {
        return fromLevel(level).map(MemberLevel::getTotalRequiredExp).orElse(0);
    }
}