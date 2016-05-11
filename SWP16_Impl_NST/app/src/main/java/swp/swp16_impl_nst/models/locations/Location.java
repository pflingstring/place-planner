package swp.swp16_impl_nst.models.locations;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import swp.swp16_impl_nst.models.locations.fields.Address;
import swp.swp16_impl_nst.models.locations.fields.Category;
import swp.swp16_impl_nst.models.locations.fields.Contact;
import swp.swp16_impl_nst.models.locations.fields.GpsCoordinates;
import swp.swp16_impl_nst.models.locations.fields.Rating;
import swp.swp16_impl_nst.models.locations.fields.User;

public class Location implements Parcelable
{
    private String id;
    private String name;
    private String comment;

    public String getMediaUrl()
    {
        return mediaUrl;
    }

    private String mediaUrl;

    public User getOwner()
    {
        return owner;
    }

    private User owner;

    public Rating getRating()
    {
        return rating;
    }

    private Rating rating;

    public Address getAddress()
    { return address; }

    private Address address;

    public GpsCoordinates getCoordinates()
    {
        return coordinates;
    }

    private GpsCoordinates coordinates;

    public Contact getContact()
    {
        return contact;
    }

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

        public Builder rating(Rating rating)
        { this.rating = rating; return this; }

        public Location build()
        {
            if (address == null || category == null)
                throw new IllegalArgumentException("Address and category may not be null");
            return new Location(this);
        }
    }


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.comment);
        dest.writeString(this.mediaUrl);
        dest.writeParcelable(this.owner, flags);
        dest.writeInt(this.rating == null ? -1 : this.rating.ordinal());
        dest.writeParcelable(this.address, flags);
        dest.writeParcelable(this.coordinates, flags);
        dest.writeParcelable(this.contact, flags);
        dest.writeList(this.categories);
//        dest.writeLong(this.createdTimestamp);
        dest.writeLong(this.lastModifiedTimestamp);
    }

    protected Location(Parcel in)
    {
        this.id = in.readString();
        this.name = in.readString();
        this.comment = in.readString();
        this.mediaUrl = in.readString();
        this.owner = in.readParcelable(User.class.getClassLoader());
        int tmpRating = in.readInt();
        this.rating = tmpRating == -1 ? null : Rating.values()[tmpRating];
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.coordinates = in.readParcelable(GpsCoordinates.class.getClassLoader());
        this.contact = in.readParcelable(Contact.class.getClassLoader());
        this.categories = new ArrayList<Category>();
        in.readList(this.categories, Category.class.getClassLoader());
//        this.createdTimestamp = in.readLong();
        this.lastModifiedTimestamp = in.readLong();
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>()
    {
        @Override
        public Location createFromParcel(Parcel source)
        {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size)
        {
            return new Location[size];
        }
    };
}
