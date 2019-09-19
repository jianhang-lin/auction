package work.jianhang.server.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class AuctionHandler extends TextWebSocketHandler {

    private static Map<WebSocketSession, List<Integer>> subscription = new HashMap<>();
    private static Map<Integer, Float> currentBids = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        Map<String, String> messageObj = JSONObject.parseObject(payload, HashMap.class);
        log.info("=====接收到的数据：{}", messageObj);
        List<Integer> productIds = subscription.get(session);
        if (CollectionUtils.isEmpty(productIds)) {
            productIds = new ArrayList<>();
        }
        productIds.add(Integer.parseInt(String.valueOf(messageObj.get("productId"))));
        subscription.put(session, productIds);

    }

    public static Map<Integer, Float> getCurrentBids() {
        return currentBids;
    }

    public static Map<WebSocketSession, List<Integer>> getSubscription() {
        return subscription;
    }
}
