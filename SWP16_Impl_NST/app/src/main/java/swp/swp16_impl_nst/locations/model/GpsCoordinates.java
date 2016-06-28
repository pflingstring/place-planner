package swp.swp16_impl_nst.locations.model;

public class GpsCoordinates
{
    private double lng;
    private double lat;

    public GpsCoordinates() {}
    public GpsCoordinates(double _lat, double _lng)
    {
        lng = _lat;
        lat = _lng;
    }

    // TODO: find better condition check
    public boolean isEmpty()
    { return lat == 0 && lng == 0; }

    public double getLng()
    { return lng; }

    public double getLat()
    { return lat; }

    public void setLat(double _lat)
    { lat = _lat; }

    public void setLng(double _lon)
    { lng = _lon; }

}
