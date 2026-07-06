package time;

public class Time {

    public Time(int hour, int minute, int second){
        validateHour(hour);
        validateMinute(minute);
        validateSecond(second);
    }

    public void validateHour(int hour){
        boolean isHourInValid = hour < 0 || hour > 11;
        if(isHourInValid) throw new IllegalArgumentException("Hour is Invalid");
    }
    public void validateMinute(int minute){
       boolean isMinuteInValid = minute < 0 || minute > 59;
       if(isMinuteInValid) throw new IllegalArgumentException("Minute is Invalid");
    }

    public void validateSecond(int second){
        boolean isSecondInValid = second < 0 || second > 59;
        if(isSecondInValid) throw new IllegalArgumentException("Second is invalid");
    }
}
