package swp.swp16_impl_nst.locations.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import swp.swp16_impl_nst.categories.model.Category;

public class Location
{
    private String id;
    private String name;
    private String comment;
    private String mediaUrl;

    private User owner;
    private Rating rating;
    private Contact contactDetails;
    private Address address;
    private GpsCoordinates coordinates;

    private final long createdTimestamp = System.currentTimeMillis();


    private long  lastModifiedTimestamp = createdTimestamp;
    private List<Category> categories = new ArrayList<>();

    // constructor
    private Location(Builder builder)
    { edit(builder); }

    public void edit(Builder builder)
    {
        name    = builder.name;
        owner   = builder.owner;
        rating  = setRating(builder.rating);
        address = builder.address;
        contactDetails = builder.contact;
        comment  = builder.comment;
        mediaUrl = builder.mediaUrl;
        categories.add(builder.category);
        id = UUID.randomUUID().toString();

        if (builder.coordinates != null)
            coordinates = builder.coordinates;
        else
            coordinates = new GpsCoordinates();
    }


    // Location Builder
    public static class Builder
    {
        // required parameters
        private String name;
        private Address address;
        private Category category;

        // optional parameters
        private User owner;
        private float rating;
        private String comment;
        private String mediaUrl;
        private Contact contact;
        private GpsCoordinates coordinates;

        // constructor
        public Builder(String name)
        { this.name = name; }


        public Builder owner(User user)
        { owner = user; return this; }

        public Builder mediaUrl(String url)
        { mediaUrl = url; return this; }

        public Builder rating(float stars)
        { this.rating = stars; return this; }

        public Builder address(Address address)
        { this.address = address; return this; }

        public Builder comment(String comment)
        { this.comment = comment; return this; }

        public Builder contact(Contact contact)
        { this.contact = contact; return this; }

        public Builder category(Category category)
        { this.category = category; return this; }

        public Builder gpsCoordinates(GpsCoordinates coordinates)
        { this.coordinates = coordinates; return this; }

        public Location build()
        {
            if (address == null || category == null)
                throw new IllegalArgumentException("Address and category may not be null");

            return new Location(this);
        }
    }

    public void setGpsCoordinates(double lat, double lon)
    {
        coordinates.setLat(lat);
        coordinates.setLon(lon);
    }

    public LatLng getLatLng()
    {
        return new LatLng(coordinates.getLat(), coordinates.getLon());
    }


    @Override
    public String toString()
    {
        String result = "";
        result += this.name + this.comment;
        return result;
    }

    // Getters
    public User getOwner()
    { return owner; }

    public String getName()
    { return this.name; }

    public Rating getRating()
    { return rating; }

    public String getComment()
    { return comment; }

    public String getMediaUrl()
    { return mediaUrl; }

    public Contact getContactDetails()
    { return contactDetails; }

    public Address getAddress()
    { return address; }

    public Category getCategory()
    { return this.categories.get(0); }      // TODO: return all categories

    public GpsCoordinates getCoordinates()
    { return coordinates; }

    public long getLastModifiedTimestamp()
    { return lastModifiedTimestamp; }


    // Setters
    public Rating setRating(float num)
    {
        int stars = (int) num;
        switch (stars)
        {
            case 1 : rating = Rating.RATING_1; return rating;
            case 2 : rating = Rating.RATING_2; return rating;
            case 3 : rating = Rating.RATING_3; return rating;
            case 4 : rating = Rating.RATING_4; return rating;
            case 5 : rating = Rating.RATING_5; return rating;
            default: return Rating.NO_RATING;
        }
    }
}

