package myjpetstore.persistence;

import myjpetstore.domain.Order;

import java.util.List;

/**
 * Created by zuo on 2015/5/2.
 */
public interface OrderDAO {
    List<Order> getOrdersByUsername(String username);

    Order getOrder(int orderId);

    void insertOrder(Order order);

    void insertOrderStatus(Order order);
}
