package myjpetstore.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import myjpetstore.domain.Cart;
import myjpetstore.domain.Category;
import myjpetstore.domain.Item;
import myjpetstore.domain.Product;
import myjpetstore.service.CatalogService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by zuo on 2015/5/28.
 */
public class catalog extends ActionSupport
{
    private ActionContext actionContext = ActionContext.getContext();
    private Map session = actionContext.getSession();
    private CatalogService catalogService = new CatalogService();

    private String categoryId;
    private Category category;
    private List<Product> productList;

    private String productId;
    private Product product;
    private List<Item> itemList;

    private String itemId;
    private Item item;

    private String workingItemId;
    private Cart cart;

    private String keyword;

    public String main()
    {
        return SUCCESS;
    }
    public String help(){return SUCCESS;}
    public String ViewCategory() throws Exception {
        category = catalogService.getCategory(categoryId);
        productList = catalogService.getProductListByCategory(categoryId);
        return SUCCESS;
    }
    public String viewProduct()
    {
        product = catalogService.getProduct(productId);
        session.put("product",product);
        itemList = catalogService.getItemListByProduct(productId);
        return SUCCESS;
    }
    public String addItemToCart()
    {
        cart = (Cart)session.get("cart");
        if(cart == null) cart = new Cart();
        if (cart.containsItemId(workingItemId))
            cart.incrementQuantityByItemId(workingItemId);
        else {
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item, isInStock);
        }
        session.put("cart",cart);
        return SUCCESS;
    }
    public String viewItem()
    {
        item = catalogService.getItem(itemId);
        return SUCCESS;
    }
    public String searchProduct()
    {
        productList = catalogService.searchProductList(keyword);
        return SUCCESS;
    }
    public String searchSuggest()
    {
        HttpServletResponse response = ServletActionContext.getResponse();
        String result = catalogService.getSuggestByKeyword(keyword);
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(result);
        out.flush();
        out.close();
        return SUCCESS;
    }
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getWorkingItemId() {
        return workingItemId;
    }

    public void setWorkingItemId(String workingItemId) {
        this.workingItemId = workingItemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
