package swp.swp16_impl_nst.categories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import swp.swp16_impl_nst.categories.model.Category;

public class CategoryProvider
{

    public static final List<Category> categories = new ArrayList<>();

    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final static Type type = (new TypeToken<List<Category>>(){}).getType();

    public static void add(Category category)
    {
        categories.add(category);
    }


}
