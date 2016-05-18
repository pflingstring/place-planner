package swp.swp16_impl_nst.locations;

import java.util.ArrayList;
import java.util.List;

import swp.swp16_impl_nst.locations.model.Location;
import swp.swp16_impl_nst.locations.model.fields.Address;
import swp.swp16_impl_nst.locations.model.fields.Category;
import swp.swp16_impl_nst.locations.model.fields.Contact;
import swp.swp16_impl_nst.locations.model.fields.GpsCoordinates;
import swp.swp16_impl_nst.locations.model.fields.Rating;
import swp.swp16_impl_nst.locations.model.fields.User;

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
                .mediaUrl("www.schloss.de")
                .rating(Rating.NO_RATING)
                .owner(new User())
                .gpsCoordinates(new GpsCoordinates())
                .contact(new Contact())
                .build();

        Location l2 = new Location.Builder("E-Kirche")
                .address(new Address())
                .category(inMarburg)
                .comment("Unten in der Stadt")
                .mediaUrl("www.schloss.de")
                .rating(Rating.NO_RATING)
                .owner(new User())
                .gpsCoordinates(new GpsCoordinates())
                .contact(new Contact())
                .build();
        locations.add(l2);
        locations.add(l1);
    }

    // so that `loadLocations` only gets called once,
    static { loadLocations(); }
}
