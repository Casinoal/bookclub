package arw.apps.barcode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Class: ServerInterface - Provides static methods to abstract the server
 * calls. This makes it easy for calling classes to use these functions without
 * worrying about the details of the server communication.
 * 
 * @author Mujtaba Hassanpur
 */
public class GetListofUsers {

        // Declared Constants
        public static final String SERVER_URL = "http://alexwhyatt.com/bookclub_api_getusers.php";

        /**
         * Gets the list of animals from the server.
         * @return A string containing a comma-delimited list of animals.
         */
        public static String getUserList() {
                /*
                 * Let's construct the query string. It should be a key/value pair. In
                 * this case, we just need to specify the command, so no additional
                 * arguments are needed.
                 */
                String data = "command=" + URLEncoder.encode("getUserList");
                return executeHttpRequest(data);
        }

        

        /**
         * Helper function used to communicate with the server by sending/receiving
         * POST commands.
         * @param data String representing the command and (possibly) arguments.
         * @return String response from the server.
         */
        private static String executeHttpRequest(String data) {
                String result = "";
                try {
                        URL url = new URL(SERVER_URL);
                        URLConnection connection = url.openConnection();
                        
                        /*
                         * We need to make sure we specify that we want to provide input and
                         * get output from this connection. We also want to disable caching,
                         * so that we get the most up-to-date result. And, we need to 
                         * specify the correct content type for our data.
                         */
                        connection.setDoInput(true);
                        connection.setDoOutput(true);
                        connection.setUseCaches(false);
                        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                        // Send the POST data
                        DataOutputStream dataOut = new DataOutputStream(connection.getOutputStream());
                        dataOut.writeBytes(data);
                        dataOut.flush();
                        dataOut.close();

                        // get the response from the server and store it in result
                        DataInputStream dataIn = new DataInputStream(connection.getInputStream()); 
                        String inputLine;
                        while ((inputLine = dataIn.readLine()) != null) {
                                result += inputLine;
                        }
                        dataIn.close();
                } catch (IOException e) {
                        /*
                         * In case of an error, we're going to return a null String. This
                         * can be changed to a specific error message format if the client
                         * wants to do some error handling. For our simple app, we're just
                         * going to use the null to communicate a general error in
                         * retrieving the data.
                         */
                        e.printStackTrace();
                        result = null;
                }

                return result;
        }
}