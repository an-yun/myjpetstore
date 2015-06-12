package myjpetstore.persistence;

import myjpetstore.domain.Category;

import java.util.List;

/**
 * Created by zuo on 2015/4/25.
 */
public interface CategoryDAO
{
    //select all categories
    List<Category> getCategoryList();

    //select a category by id
    Category getCategory(String cagegoryId);
}
