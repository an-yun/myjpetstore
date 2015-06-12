package myjpetstore.persistence;

import myjpetstore.domain.Item;

import java.util.List;
import java.util.Map;

/**
 * Created by zuo on 2015/4/25.
 */
public interface ItemDAO
{
    void updateInventoryQuantity(Map<String, Object> param);

    int getInventoryQuantity(String itemId);

    List<Item> getItemListByProduct(String productId);

    Item getItem(String itemId);
}
