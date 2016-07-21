package swp.swp16_impl_nst.categories;

import java.util.ArrayList;
import java.util.List;

import swp.swp16_impl_nst.categories.model.Category;

public class CategoryProvider
{

    public static final List<Category> categories = new ArrayList<>();


    public static void add(Category category)
    {
        categories.add(category);
    }


}
