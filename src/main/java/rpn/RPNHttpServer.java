package rpn;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class RPNHttpServer {

  HttpServer server;
  ThreadPoolExecutor threadPoolExecutor;

  public RPNHttpServer() {
    try {
      this.threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
      this.server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
      server.createContext("/calculate", new HttpPostHandler());
      server.setExecutor(threadPoolExecutor);
      server.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
