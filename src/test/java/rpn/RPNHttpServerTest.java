package rpn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Stream;

public class RPNHttpServerTest {

    static Stream<Arguments> checkMultiArgumentsMethodSource() {

        return Stream.of(Arguments.of("1, 2, 3, +, -",-4.0),
                Arguments.of("6, 2, *, 3, /",4),
                Arguments.of("2, 3, ^, 4, 5, +, +" ,17),
                Arguments.of("3, !, 4, 5, *, +", 26),
                Arguments.of("12, 3, /, !",24),
                Arguments.of("5, 1, 2, +, 4, *, +, 3, -",14));
    }

     // {"expression": }
    public static String executePost(String targetURL, String urlParameters) throws Exception {
        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    @Test
    void should_fail_if_server_is_not_listening() {
        ConnectException invalidInputException = Assertions.assertThrows(
                ConnectException.class, () -> {
                    executePost("http://localhost:6000/calculate", "");
                });
        Assertions.assertEquals("Connection refused", invalidInputException.getMessage());
    }

    @Test
    void should_calculate_rpn_expression_when_hit_calculate_endpoint() throws Exception {
        executePost("http://localhost:5000/calculate", "");
    }
}
