package myjpetstore.service;

import myjpetstore.domain.Category;
import myjpetstore.domain.Item;
import myjpetstore.domain.Product;
import myjpetstore.persistence.CategoryDAO;
import myjpetstore.persistence.ItemDAO;
import myjpetstore.persistence.ProductDAO;
import myjpetstore.persistence.impl.CategoryDAOImpl;
import myjpetstore.persistence.impl.ItemDAOImpl;
import myjpetstore.persistence.impl.ProductDAOImpl;

import java.util.List;

/**
 * Created by zuo on 2015/4/25.
 */
public class CatalogService
{
    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;
    private ItemDAO itemDAO;

    public CatalogService()
    {
        categoryDAO= new CategoryDAOImpl();
        productDAO = new ProductDAOImpl();
        itemDAO = new ItemDAOImpl();
    }
    public List<Category> getCategoryList() {
        return categoryDAO.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return categoryDAO.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return productDAO.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return productDAO.getProductListByCategory(categoryId);
    }

    // TODO enable using more than one keyword
    public List<Product> searchProductList(String keyword) {
        return productDAO.searchProductList("%" + keyword.toLowerCase() + "%");
    }


    public List<Item> getItemListByProduct(String productId) {
        return itemDAO.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return itemDAO.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return itemDAO.getInventoryQuantity(itemId) > 0;
    }
    public String getSuggestByKeyword(String keyword)
    {
        return productDAO.getSuggestByKeyword(keyword);
    }
}
