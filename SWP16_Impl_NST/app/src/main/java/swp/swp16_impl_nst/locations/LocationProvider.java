package swp.swp16_impl_nst.locations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;

import swp.swp16_impl_nst.locations.model.Location;

public class LocationProvider
{
    // the list of locations on the main screen
    public static final List<Location> locations = new ArrayList<>();

    // `type` is for converting Json strings to java Collections
    // see https://github.com/google/gson/blob/master/UserGuide.md#TOC-Collections-Examples
    private final static Gson gson =  new GsonBuilder().setPrettyPrinting().create();
    private final static Type type = (new TypeToken<List<Location>>(){}).getType();


    public static void add(Location location)
    { locations.add(location); }

    // file looks like: [ "key" : value ]
    public static List<Location> importLocationArray(String fileName)
    {
        String fileInput = LocationStorage.readFromFile(fileName);
        return gson.fromJson(fileInput, type);
    }

    // file looks like: { "locations": ["key":value, ...] }
    public static List<Location> importLocationFile(String fileName)
    {
        String fileInput = LocationStorage.readFromFile(fileName);
        List<Location> result = new ArrayList<>();

        try
        {
            String jsonArray = (new JSONObject(fileInput))
                    .getJSONArray("locations")
                    .toString();
            result = gson.fromJson(jsonArray, type);
        }
        catch (JSONException e)
            { e.printStackTrace(); }

        return result;
    }

    // so that `loadLocations` only gets called once,
    static
    {
        List<Location> defaultLocations = importLocationArray("4_loc");
        List<Location> testLocationsMS1 = importLocationFile("test-locations-ms1.json");

        locations.addAll(defaultLocations);
        locations.addAll(testLocationsMS1);
    }
}

