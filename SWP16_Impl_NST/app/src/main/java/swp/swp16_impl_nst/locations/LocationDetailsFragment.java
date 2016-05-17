package swp.swp16_impl_nst.locations;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.models.locations.Location;
import swp.swp16_impl_nst.utils.LocationUtils;

public class LocationDetailsFragment extends Fragment
{
    private final static String CURRENT_POSITION = LocationsMainActivity.CURRENT_POSITION;
    private Location location;
    private TextView name;
    private TextView owner;
    private TextView rating;
    private TextView address;
    private TextView comment;
    private TextView mediaUrl;
    private TextView contacts;
    private TextView categories;
    private TextView coordinates;

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
    {
        super.onCreate(savedInstanceState);

        int locationPosition = getArguments().getInt(CURRENT_POSITION);
        location = LocationProvider.locations.get(locationPosition);
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup      container,
                              Bundle savedInstanceState)
    { return inflater.inflate(R.layout.fragment_location_details, container, false); }

    @Override
    public void onStart()
    {
        super.onStart();
        
        if (getView() != null)
            LocationUtils.populateViews(this, getView(), location);
    }


    // setters
    public void setName(TextView name)
    { this.name = name; }

    public void setOwner(TextView owner)
    { this.owner = owner; }

    public void setRating(TextView rating)
    { this.rating = rating; }

    public void setAddress(TextView address)
    { this.address = address; }

    public void setComment(TextView comment)
    { this.comment = comment; }

    public void setContacts(TextView contacts)
    { this.contacts = contacts; }

    public void setMediaUrl(TextView mediaUrl)
    { this.mediaUrl = mediaUrl; }

    public void setCategories(TextView categories)
    { this.categories = categories; }

    public void setCoordinates(TextView coordinates)
    { this.coordinates = coordinates; }

}
