package myjpetstore.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import myjpetstore.domain.Order;
import myjpetstore.service.OrderService;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by zuo on 2015/6/4.
 */
public class order extends ActionSupport
{
    private ActionContext actionContext = ActionContext.getContext();
    private Map session = actionContext.getSession();
    private OrderService orderService = new OrderService();

    private String orderMsg;

    private int orderId;


    public String continueConfirm()
    {
        Order order = (Order)session.get("order");
        orderService.insertOrder(order);
        orderMsg="¡¤Thank you, your order has been submitted.";
        session.put("cart",null);
        return SUCCESS;
    }
    public String viewOrder()
    {
        session.put("order", orderService.getOrder(orderId));
        return SUCCESS;
    }
    public String getOrderMsg() {
        return orderMsg;
    }

    public void setOrderMsg(String orderMsg) {
        this.orderMsg = orderMsg;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

}
