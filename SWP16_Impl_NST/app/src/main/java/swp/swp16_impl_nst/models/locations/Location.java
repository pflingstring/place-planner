package swp.swp16_impl_nst.models.locations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import swp.swp16_impl_nst.models.locations.fields.Address;
import swp.swp16_impl_nst.models.locations.fields.Category;
import swp.swp16_impl_nst.models.locations.fields.Contact;
import swp.swp16_impl_nst.models.locations.fields.GpsCoordinates;
import swp.swp16_impl_nst.models.locations.fields.Rating;
import swp.swp16_impl_nst.models.locations.fields.User;

public class Location
{
    private String id;
    private String name;
    private String comment;
    private String mediaUrl;

    private User owner;
    private Rating rating;
    private Address address;
    private GpsCoordinates coordinates;
    private Contact contact;
    private List<Category> categories = new ArrayList<>();

    private final long createdTimestamp = System.currentTimeMillis();
    private long  lastModifiedTimestamp = createdTimestamp;

    private Location(Builder builder)
    {
        name = builder.name;
        categories.add(builder.category);
        address = builder.address;

        id = UUID.randomUUID().toString();
        coordinates = builder.coordinates;
        comment  = builder.comment;
        mediaUrl = builder.mediaUrl;
        contact  = builder.contact;
        owner  = builder.owner;
        rating = builder.rating;
    }

    public String getComment()
    { return comment; }

    public String getName()
    { return this.name; }

    public Category getCategory()
    { return this.categories.get(0); }      // TODO: return all categories


    public static class Builder
    {
        // required parameters
        private String name;
        private Category category;
        private Address address;

        // optional parameters
        private String comment;
        private String mediaUrl;
        private User owner;
        private Rating rating;
        private GpsCoordinates coordinates;
        private Contact contact;

        public Builder(String name)
        { this.name = name; }

        public Builder category(Category category)
        { this.category = category; return this; }

        public Builder address(Address address)
        { this.address = address; return this; }

        public Builder comment(String comment)
        { this.comment = comment; return this; }

        public Builder mediaUrl(String url)
        { mediaUrl = url; return this; }

        public Builder owner(User user)
        { owner = user; return this; }

        public Builder gpsCoordinates(GpsCoordinates coordinates)
        { this.coordinates = coordinates; return this; }

        public Builder contact(Contact contact)
        { this.contact = contact; return this; }

        public Location build()
        {
            if (address == null || category == null)
                throw new IllegalArgumentException("Address and category may not be null");
            return new Location(this);
        }
    }


}
