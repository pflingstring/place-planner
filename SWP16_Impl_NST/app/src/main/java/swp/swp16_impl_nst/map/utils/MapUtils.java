package swp.swp16_impl_nst.map.utils;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.List;

import swp.swp16_impl_nst.locations.model.GpsCoordinates;
import swp.swp16_impl_nst.locations.model.Location;

public class MapUtils
{
    public static LatLngBounds getLatLngBounds(List<Location> locations)
    {
        GpsCoordinates coordinates = locations.get(0).getCoordinates();
        double latMin = coordinates.getLat();
        double lngMin = coordinates.getLng();
        double latMax = latMin;
        double lngMax = lngMin;

        for (Location location : locations)
        {
            GpsCoordinates latLng = location.getCoordinates();
            double latitude  = latLng.getLat();
            double longitude = latLng.getLng();

            latMin = (latitude  < latMin) ? latitude  : latMin;
            lngMax = (longitude > lngMax) ? longitude : lngMax;
            latMax = (latitude  > latMax) ? latitude  : latMax;
            lngMin = (longitude < lngMin) ? longitude : lngMin;
        }

        return new LatLngBounds(
                new LatLng(latMin, lngMin),
                new LatLng(latMax, lngMax));
    }
}
