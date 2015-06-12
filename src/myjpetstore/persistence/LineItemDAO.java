package myjpetstore.persistence;

import myjpetstore.domain.LineItem;

import java.util.List;

/**
 * Created by zuo on 2015/5/2.
 */
public interface LineItemDAO
{
    List<LineItem> getLineItemsByOrderId(int orderId);

    void insertLineItem(LineItem lineItem);
}
