package swp.swp16_impl_nst.models.locations.fields;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable
{
    private String id;
    private String name;

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
    }

    public User()
    {
    }

    protected User(Parcel in)
    {
        this.id = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>()
    {
        @Override
        public User createFromParcel(Parcel source)
        {
            return new User(source);
        }

        @Override
        public User[] newArray(int size)
        {
            return new User[size];
        }
    };
}
