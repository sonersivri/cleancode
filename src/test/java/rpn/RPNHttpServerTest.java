package rpn;

import io.cucumber.java.Before;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

class RPNHttpServerTest {

  static RPNHttpServer server;

  @BeforeAll
  static void setUp() {
   server = new RPNHttpServer();
  }

  static String executePost(String targetURL, String postBody) throws Exception {
    HttpURLConnection connection = null;

    try {
      //Create connection
      URL url = new URL(targetURL);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type",
          "application/x-www-form-urlencoded");

      connection.setRequestProperty("Content-Language", "en-US");

      connection.setUseCaches(false);
      connection.setDoOutput(true);

      //Send request
      DataOutputStream wr = new DataOutputStream(
          connection.getOutputStream());
      wr.writeBytes(postBody);
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
    String postData ="{\n"
        + "  \"expression\": \"string\"\n"
        + "}";
      final String responseString = executePost("http://localhost:8080/calculate", postData);
      Assertions.assertEquals("jjj\r", responseString);
  }
}
