package work.jianhang.server.task;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import work.jianhang.server.entity.Product;
import work.jianhang.server.handler.AuctionHandler;
import work.jianhang.server.service.AuctionService;

import java.util.*;

@Slf4j
@Component
public class ScheduledTasks {

    @Autowired
    private AuctionService auctionService;

    @Scheduled(fixedRate = 2000)
    public void reportCurrentBid() {
        List<Product> products = auctionService.getAllProducts();
        Map<Integer, Float> currentBids = AuctionHandler.getCurrentBids();
        Map<WebSocketSession, List<Integer>> subscription = AuctionHandler.getSubscription();
        for (Product p : products) {
            Float currentBid = currentBids.get(p.getId());
            if (currentBid == null) {
                currentBid = p.getPrice();
            }

            Float newBid = currentBid + new Random().nextFloat()*5;
            currentBids.put(p.getId(), newBid);

            Iterator<Map.Entry<WebSocketSession, List<Integer>>> iterator = subscription.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<WebSocketSession, List<Integer>> entry = iterator.next();
                List<Integer> ids = entry.getValue();
                for (Integer i : ids) {
                    if (i == p.getId()) {
                        Map<String , Object> newBids = new HashMap<>();
                        newBids.put("productId", p.getId());
                        newBids.put("bid", currentBids.get(p.getId()));
                        try {
                            if (entry.getKey().isOpen()) {
                                entry.getKey().sendMessage(new TextMessage(JSONObject.toJSONString(newBids)));
                            } else {
                                iterator.remove();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }
}
