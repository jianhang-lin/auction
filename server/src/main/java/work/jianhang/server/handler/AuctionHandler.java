package work.jianhang.server.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import work.jianhang.server.service.AuctionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class AuctionHandler extends TextWebSocketHandler {

    @Autowired
    private AuctionService auctionService;

    private static Map<Object, List<Integer>> subscription = new HashMap<>();

    private static Map<Integer, Float> currentBids = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        Map<String, String> messageObj = JSONObject.parseObject(payload, HashMap.class);
        log.info("=====接收到的数据：{}", messageObj);
        List<Integer> productIds = subscription.get(session);
        productIds.add(Integer.parseInt(messageObj.get("productId")));
        subscription.put(session, productIds);
        session.sendMessage(new TextMessage("服务器返回收到的消息:" + payload));
    }
}
