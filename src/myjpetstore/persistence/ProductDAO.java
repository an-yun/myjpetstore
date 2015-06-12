package myjpetstore.persistence;

import myjpetstore.domain.Product;

import java.util.List;

/**
 * Created by zuo on 2015/4/25.
 */
public interface ProductDAO
{
    List<Product> getProductListByCategory(String categoryId);

    Product getProduct(String productId);

    List<Product> searchProductList(String keywords);

    String getSuggestByKeyword(String keyword);
}
