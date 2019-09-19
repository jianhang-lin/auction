package work.jianhang.server.util;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import work.jianhang.server.service.AuctionService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Lazy(false)
@Component
public class ServiceUtil {
    @Resource
    private AuctionService auctionService;

    private static ServiceUtil serviceUtil;

    @PostConstruct
    public void init() {
        serviceUtil = this;
        serviceUtil.auctionService = this.auctionService;

    }

    public static ServiceUtil getInstance() {
        return serviceUtil;
    }

    public AuctionService getAuctionService() {
        return auctionService;
    }

    public void setAuctionService(AuctionService auctionService) {
        this.auctionService = auctionService;
    }
}
