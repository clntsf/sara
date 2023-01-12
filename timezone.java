import java.util.TimeZone;

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
            try{
                writeSystemTimezone();
                Process p = Runtime.getRuntime().exec(
                    "git add --all; git commit -m 'update write timezone'; git push -u main"
                );
            }
            catch (IOException ioE) {
                System.out.printf("IO Exception in write: %s\n", ioE.getMessage());
            }
        }
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
    

}