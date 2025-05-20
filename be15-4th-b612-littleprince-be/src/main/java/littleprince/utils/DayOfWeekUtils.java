package littleprince.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DayOfWeekUtils {
//    private final static String[] KOREAN_DAYS = { "월", "화", "수", "목", "금", "토", "일" };

    public static int dayToWeek(LocalDate date) {

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int index = dayOfWeek.getValue() - 1;
        return index;
    }
}
