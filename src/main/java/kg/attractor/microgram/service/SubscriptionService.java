package kg.attractor.microgram.service;

import kg.attractor.microgram.dao.SubscriptionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionDao subscriptionDao;

    public String subscript(int myId, int userId) {
        try {
            subscriptionDao.add(myId, userId);
            return "Ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    public String deleteSubscription(int myId, int userId) {
        try {
            subscriptionDao.deleteSubscriptions(myId, userId);
            return "Ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
