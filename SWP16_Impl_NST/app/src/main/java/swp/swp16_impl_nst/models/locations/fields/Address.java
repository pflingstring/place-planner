package swp.swp16_impl_nst.models.locations.fields;

import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable
{
    private String street;
    private String number;
    private String city;
    private String zip;
    private String contry;

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.street);
        dest.writeString(this.number);
        dest.writeString(this.city);
        dest.writeString(this.zip);
        dest.writeString(this.contry);
    }

    public Address()
    {
    }

    protected Address(Parcel in)
    {
        this.street = in.readString();
        this.number = in.readString();
        this.city = in.readString();
        this.zip = in.readString();
        this.contry = in.readString();
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>()
    {
        @Override
        public Address createFromParcel(Parcel source)
        {
            return new Address(source);
        }

        @Override
        public Address[] newArray(int size)
        {
            return new Address[size];
        }
    };
}
