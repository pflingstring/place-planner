package swp.swp16_impl_nst.locations.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.activities.LocationsMainActivity;
import swp.swp16_impl_nst.locations.model.Location;
import swp.swp16_impl_nst.utils.LocationUtils;

/**
 * Displays the details of a Location
 */
public class LocationDetailsFragment extends Fragment
{
    private final static String CURRENT_POSITION = LocationsMainActivity.CURRENT_POSITION;
    private Location location;
    private TextView name;
    private TextView country;
    private TextView address_city;
    private TextView address_street;
    private TextView address_number;
    private TextView category_main_name;
    private TextView address_postal_code;
    private RatingBar ratingBar;

    public LocationDetailsFragment()
    { /** Required empty public constructor */ }


    /**
     * Creates a new instance of this fragment
     *
     * @param position - position of the Location in the list
     * @return A new instance of fragment LocationDetailsFragment.
     */
    public static LocationDetailsFragment newInstance(int position)
    {
        LocationDetailsFragment fragment = new LocationDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CURRENT_POSITION, position);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
        { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup      container,
                              Bundle savedInstanceState)
        { return inflater.inflate(R.layout.fragment_location_details, container, false); }

    @Override
    public void onStart()
    {
        super.onStart();

        int locationPosition = getArguments().getInt(CURRENT_POSITION);
        location = LocationProvider.locations.get(locationPosition);

        if (getView() != null)
            LocationUtils.populateViews(this, getView(), location);
    }


    // setters
    public void setName(TextView name)
    { this.name = name; }

    public void setCountry(TextView country)
    { this.country = country; }

    public void setRatingBar(RatingBar ratingBar)
    { this.ratingBar = ratingBar; }

    public void setAddress_city(TextView address_city)
    { this.address_city = address_city; }

    public void setAddress_street(TextView address_street)
    { this.address_street = address_street; }

    public void setAddress_number(TextView address_number)
    { this.address_number = address_number; }

    public void setCategory_main_name(TextView category_main_name)
    { this.category_main_name = category_main_name; }

    public void setAddress_postal_code(TextView address_postal_code)
    { this.address_postal_code = address_postal_code; }
}
