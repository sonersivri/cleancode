package rpn;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HttpPostHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    OutputStream outputStream = httpExchange.getResponseBody();
    StringBuilder htmlBuilder = new StringBuilder();
    htmlBuilder.append("jjj");
    httpExchange.sendResponseHeaders(200, htmlBuilder.length());
    outputStream.write(htmlBuilder.toString().getBytes(StandardCharsets.UTF_8));
    outputStream.flush();
    outputStream.close();
  }
}
