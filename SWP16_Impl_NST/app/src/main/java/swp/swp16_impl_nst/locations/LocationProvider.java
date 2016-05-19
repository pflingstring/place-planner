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
        Address address1 = new Address("Giesonenweg", "10", "35037", "Marburg", null);
        Address address2 = new Address("Elisabethstr.", "1", "35037", "Marburg", "Germany");
        Category inMarburg = new Category(1, "Historical Buildings");

        Location l1 = new Location.Builder("Schloss")
                .address(address1)
                .category(inMarburg)
                .comment("Auf dem Berg")
                .mediaUrl("www.schloss.de")
                .rating(5)
                .owner(new User())
                .build();

        Location l2 = new Location.Builder("E-Kirche")
                .address(address2)
                .category(inMarburg)
                .comment("Unten in der Stadt")
                .mediaUrl("www.schloss.de")
                .rating(3)
                .owner(new User())
                .build();

        locations.add(l2);
        locations.add(l1);
    }

    // so that `loadLocations` only gets called once,
    static { loadLocations(); }
}
