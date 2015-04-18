package bluetooth.bluetoothserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by sanderson on 09/04/2015.
 */
public class Converter {

    public static String inputStremToString(InputStream is){
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String result="";
        try {
            br = new BufferedReader(new InputStreamReader(is));
            int c;
            while ((c = br.read()) != -1) {
                //Since c is an integer, cast it to a char. If it isn't -1, it will be in the correct range of char.
                sb.append( (char)c ) ;
            }

            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

}
