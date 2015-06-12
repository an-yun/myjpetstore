package myjpetstore.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import myjpetstore.domain.*;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zuo on 2015/5/30.
 */
public class cart extends ActionSupport {
    private ActionContext actionContext = ActionContext.getContext();
    private Map session = actionContext.getSession();
    private Cart cart;

    private String messgin;


    private String checkOut;

    private String workingItemId;
    public String viewCart()
    {
        cart = (Cart)session.get("cart");
        if(cart == null) {
            cart = new Cart();
            session.put("cart", cart);
        }
        return SUCCESS;
    }
    public String updateCartQuantities()
    {
        cart = (Cart) session.get("cart");
        HttpServletRequest request = ServletActionContext.getRequest();
        Iterator<CartItem> cartItems = cart.getAllCartItems();
        while (cartItems.hasNext()) {
            CartItem cartItem = cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            try {
                int quantity = Integer.parseInt(request.getParameter(itemId));
                cart.setQuantityByItemId(itemId, quantity);
                if (quantity < 1) {
                    cartItems.remove();
                }
            } catch (Exception e) {
                messgin = "The Quantities of Item must be Integer!";
                return INPUT;
            }
        }
        return SUCCESS;
    }
    public String removedItemFromCart()
    {
        cart = (Cart)session.get("cart");
        Item item  = cart.removeItemById(workingItemId);
        if(item == null)
        {
            messgin = "Attempted to remove null CartItem from Cart.";
            return INPUT;
        }
        else
        {
           return SUCCESS;
        }
    }
    public String checkOut()
    {
        Account account = (Account)session.get("account");
        Cart cart  =(Cart)session.get("cart");
        if(account==null)
        {
            checkOut=".You must sign on before attempting to check out. Please sign on and try checking out again.";
            return INPUT;
        }
        else
        {
            Order order = new Order();
            order.initOrder(account,cart);
            session.put("order",order);
            return SUCCESS;
        }
    }
    public String getMessgin() {
        return messgin;
    }

    public void setMessgin(String messgin) {
        this.messgin = messgin;
    }
    public String getWorkingItemId() {
        return workingItemId;
    }

    public void setWorkingItemId(String workingItemId) {
        this.workingItemId = workingItemId;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
}
