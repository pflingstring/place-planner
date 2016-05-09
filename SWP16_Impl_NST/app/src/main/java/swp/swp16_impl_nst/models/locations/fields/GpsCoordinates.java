package swp.swp16_impl_nst.models.locations.fields;

import android.os.Parcel;
import android.os.Parcelable;

public class GpsCoordinates implements Parcelable
{
    private double lat;
    private double lon;

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lon);
    }

    public GpsCoordinates()
    {
    }

    protected GpsCoordinates(Parcel in)
    {
        this.lat = in.readDouble();
        this.lon = in.readDouble();
    }

    public static final Parcelable.Creator<GpsCoordinates> CREATOR = new Parcelable.Creator<GpsCoordinates>()
    {
        @Override
        public GpsCoordinates createFromParcel(Parcel source)
        {
            return new GpsCoordinates(source);
        }

        @Override
        public GpsCoordinates[] newArray(int size)
        {
            return new GpsCoordinates[size];
        }
    };
}
