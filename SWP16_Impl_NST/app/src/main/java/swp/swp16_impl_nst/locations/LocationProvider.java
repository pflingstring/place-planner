package swp.swp16_impl_nst.locations;

import java.util.ArrayList;
import java.util.List;

import swp.swp16_impl_nst.models.locations.Location;
import swp.swp16_impl_nst.models.locations.fields.Address;
import swp.swp16_impl_nst.models.locations.fields.Category;

public class LocationProvider
{
    public static List<Location> locations = new ArrayList<>();

    public static void add(Location location)
    { locations.add(location); }


    // TODO: load locations from an external source, f.e. internal storage
    public static void loadLocations()
    {
        Category inMarburg = new Category(1, "Marburg");
        Location l1 = new Location.Builder("Schloss")
                .address(new Address())
                .category(inMarburg)
                .comment("Auf dem Berg")
                .build();

        Location l2 = new Location.Builder("E-Kirche")
                .address(new Address())
                .category(inMarburg)
                .comment("Unten in der Stadt")
                .build();
        locations.add(l2);
        locations.add(l1);
    }

    // so that `loadLocations` only gets called once,
    static { loadLocations(); }
}
