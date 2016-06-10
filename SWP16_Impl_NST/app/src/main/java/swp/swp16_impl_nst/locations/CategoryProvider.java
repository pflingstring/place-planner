package swp.swp16_impl_nst.locations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import swp.swp16_impl_nst.locations.model.Category;
import swp.swp16_impl_nst.locations.model.Location;

/**
 * Created by Simon on 06.06.2016.
 */
public class CategoryProvider {

    // the list of locations on the main screen
    public static final List<Category> categorys = new ArrayList<>();

    public static final List<Category> getCategorysCopy()
    { return new ArrayList<>(categorys); }

    // `type` is for converting Json strings to java Collections
    // see https://github.com/google/gson/blob/master/UserGuide.md#TOC-Collections-Examples
    private final static Gson gson =  new GsonBuilder().setPrettyPrinting().create();
    private final static Type type = (new TypeToken<List<Location>>(){}).getType();

    public static void add(Category category)
    { categorys.add(category); }

    // file looks like: [ "key" : value ]
    public static List<Category> importCategoryArray(String fileName)
    {
        String fileInput = CategoryStorage.readFromFile(fileName);
        return gson.fromJson(fileInput, type);
    }

    // file looks like: { "categorys": ["key":value, ...] }
    public static List<Category> importCategoryFile(String fileName)
    {
        String fileInput = CategoryStorage.readFromFile(fileName);
        List<Category> result = new ArrayList<>();

        try
        {
            String jsonArray = (new JSONObject(fileInput))
                    .getJSONArray("categorys")
                    .toString();
            result = gson.fromJson(jsonArray, type);
        }
        catch (JSONException e)
        { e.printStackTrace(); }

        return result;
    }

    public static void exportCurrentCategorys(String fileName)
    {
        String json = gson.toJson(categorys, type);
        CategoryStorage.writeToFile(json, fileName);
    }

    // so that `loadLocations` only gets called once,
//    static
//    {
//        List<Location> defaultLocations = importLocationArray("4_loc");
//        locations.addAll(defaultLocations);
//    }

}
