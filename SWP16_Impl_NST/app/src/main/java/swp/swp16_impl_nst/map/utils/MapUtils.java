package swp.swp16_impl_nst.map.utils;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

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

    public static void updateLocationsOnMap(
            final List<Location> locations,
            final GoogleMap map)
    {
        map.clear();
        for (Location location : locations)
            map.addMarker(new MarkerOptions()
                    .position(location.getLatLng())
                    .title(location.getName()));

        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener()
        {
            @Override
            public void onCameraChange(CameraPosition cameraPosition)
            {
                map.moveCamera(CameraUpdateFactory.newLatLngBounds(
                        MapUtils.getLatLngBounds(locations), 35));
                map.setOnCameraChangeListener(null);
            }
        });
    }
}
