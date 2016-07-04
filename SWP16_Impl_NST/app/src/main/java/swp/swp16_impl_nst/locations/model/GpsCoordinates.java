package swp.swp16_impl_nst.locations.model;

public class GpsCoordinates
{
    private double lat;
    private double lon;

    public GpsCoordinates() {}
    public GpsCoordinates(double latitude, double longitude)
    {
        lat = latitude;
        lon = longitude;
    }

    // TODO: find better condition check
    public boolean isEmpty()
    { return lat == 0 && lon == 0; }

    public double getLon()
    { return lon; }

    public double getLat()
    { return lat; }

    public void setLat(double _lat)
    { lat = _lat; }

    public void setLon(double _lon)
    { lon = _lon; }

}
