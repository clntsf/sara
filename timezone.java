import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


public class timezone {

    public static void main(String[] args) {
        TimeZone tz = TimeZone.getDefault();
        Date date = new Date();

        SimpleDateFormat df = new SimpleDateFormat("EEEE, dd MMMM yyyy @ HH:mm:ss a");
        df.setTimeZone(tz);

        System.out.println("Date and time here: " + df.format(date));
    }

}