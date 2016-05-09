package swp.swp16_impl_nst.models.locations.fields;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable
{
    private int icon;
    private String name;

    public Category(int icon, String name)
    {
        this.icon = icon;
        this.name = name;
    }

    public String getName()
    { return  this.name; }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(this.icon);
        dest.writeString(this.name);
    }

    protected Category(Parcel in)
    {
        this.icon = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>()
    {
        @Override
        public Category createFromParcel(Parcel source)
        {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size)
        {
            return new Category[size];
        }
    };
}
