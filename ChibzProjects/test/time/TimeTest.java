package time;

import org.junit.jupiter.Test;

import java.sql.Time;

import static java.lang.System.out;

public class TimeTest {

    @Test
    public void nigger(){
        Time time;

        try{
            time = new Time(34, 10, 34);
        }
        catch(IllegalArgumentException e){
            out.println("I catch am!!!");
            time = new Time(3, 10, 34);
        }
    }
}
