package swp.swp16_impl_nst.locations;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import swp.swp16_impl_nst.locations.model.*;

import java.lang.reflect.Type;
import java.util.List;

public class LocationGson
{
    static
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Location loc_orig_0 = new Location.Builder
                ("Mr King")
                .address(new Address("Rudolphsplatz", "33", "35037", "Marburg", "Germany"))
                .category(new Category(2, "FastFood"))
                .comment("JawohlJawohl")
                .build();

        Location loc_orig_1 = new Location.Builder
                ("Schloss")
                .address(new Address("Gisonenweg", "1", "35037", "Marburg", "Germany"))
                .category(new Category(1, "default_cat"))
                .comment("First Location to Json")
                .build();

        LocationProvider.locations.add(loc_orig_0);
        LocationProvider.locations.add(loc_orig_1);

        String json = gson.toJson(LocationProvider.locations);

        Type type = (new TypeToken<List<Location>>(){}).getType();
        List<Location> parsedLocations = gson.fromJson(json, type);

        Log.i("GSON", json);
    }
}
