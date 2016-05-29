package swp.swp16_impl_nst.locations;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;

import swp.swp16_impl_nst.locations.model.Location;
import swp.swp16_impl_nst.locations.model.Address;
import swp.swp16_impl_nst.locations.model.Category;
import swp.swp16_impl_nst.locations.model.Rating;
import swp.swp16_impl_nst.locations.model.User;

public class LocationProvider
{
    public static List<Location> locations = new ArrayList<>();

    public static void add(Location location)
    { locations.add(location); }

    // TODO: load locations from an external source, f.e. internal storage
    public static void loadLocations()
    {
        Address address1 = new Address("Giesonenweg", "10", "35037", "Marburg", null);
        Address address2 = new Address("Elisabethstr.", "1", "35037", "Marburg", "Germany");
        Category inMarburg = new Category(1, "Historical Buildings");

        Location l1 = new Location.Builder("Schloss")
                .address(address1)
                .category(inMarburg)
                .comment("Auf dem Berg")
                .mediaUrl("www.schloss.de")
                .rating(Rating.NO_RATING)
                .owner(new User())
                .build();

        Location l2 = new Location.Builder("E-Kirche")
                .address(address2)
                .category(inMarburg)
                .comment("Unten in der Stadt")
                .mediaUrl("www.schloss.de")
                .rating(Rating.RATING_5)
                .owner(new User())
                .build();

        Location loc_orig_0 = new Location.Builder
                ("Mr King")
                .address(new Address("Rudolphsplatz", "33", "35037", "Marburg", "Germany"))
                .category(new Category(2, "FastFood"))
                .comment("JawohlJawohl")
                .build();

        Location loc_orig_1 = new Location.Builder
                ("Philippinum")
                .address(new Address("Gisonenweg", "1", "35037", "Marburg", "Germany"))
                .category(new Category(1, "default_cat"))
                .comment("First Student Dorm in Germany")
                .build();

        locations.add(l2);
        locations.add(l1);
        locations.add(loc_orig_0);
        locations.add(loc_orig_1);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(LocationProvider.locations);

        LocationStorage.writeToFile(json, "def");


        Type type = (new TypeToken<List<Location>>(){}).getType();
        String jsonStr = LocationStorage.readFromFile("test-locations-ms1.json");

        List<Location> parsedLocations = null;

        try
        {
            JSONObject jsonObject = new JSONObject(jsonStr);
            String jsonArray = jsonObject.getJSONArray("locations").toString();
            parsedLocations = gson.fromJson(jsonArray, type);
            LocationProvider.locations.addAll(parsedLocations);
        }

        catch (JSONException e)
        {
            e.printStackTrace();
            Log.e("!FILE_ERROR", e.getMessage());
        }
    }

    // so that `loadLocations` only gets called once,
    static { loadLocations(); }
}
