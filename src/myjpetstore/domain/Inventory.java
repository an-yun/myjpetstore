package myjpetstore.domain;

/**
 * Created by zuo on 2015/6/13.
 */
public class Inventory {
    private String itemid;
    private int qty;
    public Inventory(){}
    public Inventory(String itemid, int qty) {
        this.itemid = itemid;
        this.qty = qty;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
