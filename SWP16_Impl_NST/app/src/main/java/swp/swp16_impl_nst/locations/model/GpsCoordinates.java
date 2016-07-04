package swp.swp16_impl_nst.locations.model;

public class GpsCoordinates
{
    private double lon;
    private double lat;

    public GpsCoordinates() {}
    public GpsCoordinates(double _lat, double _lng)
    {
        lon = _lat;
        lat = _lng;
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
