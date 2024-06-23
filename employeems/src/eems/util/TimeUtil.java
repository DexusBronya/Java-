package eems.util;

public class TimeUtil {

    // 检查是否在上午打卡时间区间内
    public static boolean isMorningCheckInTime(String timeString) {
        String[] parts = timeString.split(":");
        if (parts.length >= 2) {
            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);
            return (hour == 8 && minute >= 0 && minute < 30);
        }
        return false;
    }

    // 检查是否在下午打卡时间区间内
    public static boolean isAfternoonCheckInTime(String timeString) {
        String[] parts = timeString.split(":");
        System.out.println(parts.length);
        if (parts.length >= 2) {
            int hour = Integer.parseInt(parts[0]);
            System.out.println("hour"+hour);
            int minute = Integer.parseInt(parts[1]);
            System.out.println("min"+minute);

            return (hour == 17 && minute >= 30 && minute < 60);
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
