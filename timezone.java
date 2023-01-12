import java.util.TimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.net.URL;
import java.net.MalformedURLException;

public class timezone {
    public static final String TIMEZONE_URL = "https://raw.githubusercontent.com/ctsf1/sara/master/tz";

    public static void main( String[] args ) {
        
        if (args.length > 0) if ( args[0].equals("write") ) {
            writeSystemTimezone();
            return;
        }
        displayCTime();

    }

    public static void writeSystemTimezone() {
        // get/format timezone id
        TimeZone tz = TimeZone.getDefault();
        String timeZoneID = tz.getID() + "\n";

        writeToFile(timeZoneID);
    }

    public static String getTimezoneFilepath() {
        URL tzFile = timezone.class.getResource("");
        return tzFile.getPath() + "tz";
    }

    public static void writeToFile( String content ){

        // get filepath relative to dir containing executing script
        String fp = getTimezoneFilepath();

        System.out.printf("Writing to file: %s\n", fp);
        System.out.printf("Writing timezone ID to file: %s\n", content);

        // create file obj and write to file overwriting prev content
        File outFile = new File(fp);
        try {
            FileOutputStream fos = new FileOutputStream(outFile, false);
            fos.write(content.getBytes());
            fos.close();
        }
        // handle exceptions
        catch (IOException io) {
            System.out.printf("IO Exception encountered when writing to file: \n%s\n", io.getMessage());
        }
    }

    public static String readTimezoneFromWeb() {
        try{
            URL timezoneURL = new URL( TIMEZONE_URL );

            BufferedReader reader = new BufferedReader(
                new InputStreamReader( timezoneURL.openStream() )
            );

            String webTimeZoneID = reader.readLine();
            reader.close();

            return webTimeZoneID;

        }
        catch ( MalformedURLException urlE ) {
            System.out.printf( "Malformed url: %s\n", urlE.getMessage() );
            return null;
        }
        catch ( IOException ioE ) {
            System.out.printf( "IOException: %s\n", ioE.getMessage() );
            return null;
        }
    }
    
    public static void displayCTime() {
        String tz = readTimezoneFromWeb();

        Date date = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("EEEE, dd MMMM YYYY @ h:mm:ss a");
        fmt.setTimeZone(TimeZone.getTimeZone(tz));
        
        String hl = "-".repeat(30);

        System.out.println(hl);
        System.out.printf("Colin's current timezone ID: %s \n", tz);
        System.out.printf("Current time in timezone: %s\n", fmt.format(date));
        System.out.println(hl);
    }
}