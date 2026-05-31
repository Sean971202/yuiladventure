import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Game {
    public static void main(String[] args) throws Exception {
        System.out.println("--- 玉里奇遇 RPG 啟動 ---");

        // 初始化遊戲物件
        Player ry = new Player(0, 0);
        Location bridge = new Location("客城紅橋", "百年前的鐵道，見證了玉里的變遷。");

        // 啟動 Web 伺服器，監聽 8000 埠
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        
        // 設定 API 接口
        server.createContext("/api/location", exchange -> {
            String json = String.format("{\"name\": \"%s\", \"story\": \"%s\"}", 
                           bridge.getName(), "百年前的鐵道，見證了玉里的變遷。");
            
            byte[] responseBytes = json.getBytes(StandardCharsets.UTF_8);
            
            // 處理跨域 (CORS) 以便網頁連接
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            
            exchange.sendResponseHeaders(200, responseBytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseBytes);
            }
        });

        server.setExecutor(null);
        server.start();
        
        System.out.println("Java 伺服器已啟動於 http://localhost:8000/api/location");
        System.out.println("等待網頁前端連接...");
    }
}