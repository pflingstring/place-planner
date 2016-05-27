package swp.swp16_impl_nst.locations.model;

public class GpsCoordinates
{
    private double lat;
    private double lng;

    public GpsCoordinates(double _lat, double _lng)
    {
        lat = _lat;
        lng = _lng;
    }

    public double getLat()
    { return lat; }

    public double getLng()
    { return lng; }

}
