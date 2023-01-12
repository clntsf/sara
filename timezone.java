import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

public class timezone {

    public static void main( String[] args ) {
        writeTimezoneToFile();
    }

    public static void writeTimezoneToFile(){

        // get filepath relative to dir containing executing script
        URL tzFile = timezone.class.getResource("");
        String fp = tzFile.getPath() + "tz";

        System.out.printf("Writing to file: %s\n", fp);

        // get/format timezone id
        TimeZone tz = TimeZone.getDefault();
        String timeZoneID = tz.getID();
        System.out.printf("Writing timezone ID to file: %s\n", timeZoneID);

        // create file obj and write to file overwriting prev content
        File outFile = new File(fp);
        try {
            FileOutputStream fos = new FileOutputStream(outFile, false);
            fos.write(timeZoneID.getBytes());
            fos.close();
        }
        // handle exceptions with a nice message
        catch (IOException io) {
            System.out.printf("IO Exception encountered when writing to file: \n%s\n", io);
        }
    }

}