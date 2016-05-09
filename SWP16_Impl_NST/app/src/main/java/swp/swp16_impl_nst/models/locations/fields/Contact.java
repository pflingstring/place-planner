package swp.swp16_impl_nst.models.locations.fields;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable
{
    private String web;
    private String email;
    private String telephone;

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.web);
        dest.writeString(this.email);
        dest.writeString(this.telephone);
    }

    public Contact()
    {
    }

    protected Contact(Parcel in)
    {
        this.web = in.readString();
        this.email = in.readString();
        this.telephone = in.readString();
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>()
    {
        @Override
        public Contact createFromParcel(Parcel source)
        {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size)
        {
            return new Contact[size];
        }
    };
}
