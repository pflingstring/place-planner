package swp.swp16_impl_nst.locations.model;

public class GpsCoordinates
{
    private double lon;
    private double lat;

    public GpsCoordinates(double _lat, double _lng)
    {
        lon = _lat;
        lat = _lng;
    }

    public double getLon()
    { return lon; }

    public double getLat()
    { return lat; }

}
