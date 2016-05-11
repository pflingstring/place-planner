package swp.swp16_impl_nst.locations;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.models.locations.Location;

public class LocationDetailsFragment extends Fragment
{
    private final static String CURRENT_POSITION = LocationsMainActivity.CURRENT_POSITION;
    private Location location;
    private TextView name;
    private TextView address;
    private TextView comment;
    private TextView mediaUrl;
    private TextView rating;
    private TextView owner;
    private TextView coordinates;
    private TextView contacts;
    private TextView categories;

    public LocationDetailsFragment()
    { /** Required empty public constructor */ }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
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
        location = LocationProvider.locations.get(
                getArguments().getInt(CURRENT_POSITION));
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup      container,
                              Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_location_details, container, false);

        // TODO: make a generic helper function; check for nulls
        name = (TextView) view.findViewById(R.id.name);
        name.setText(location.getName());

        address = (TextView) view.findViewById(R.id.address);
        address.setText(location.getAddress().toString());

        comment = (TextView) view.findViewById(R.id.comment);
        comment.setText(location.getComment());

        mediaUrl = (TextView) view.findViewById(R.id.mediaUrl);
        mediaUrl.setText(location.getMediaUrl());

        rating = (TextView) view.findViewById(R.id.rating);
        rating.setText(location.getRating().toString());

        owner = (TextView) view.findViewById(R.id.owner);
        owner.setText(location.getOwner().toString());

        coordinates = (TextView) view.findViewById(R.id.gpsCoordinates);
        coordinates.setText(location.getCoordinates().toString());

        contacts = (TextView) view.findViewById(R.id.contactDetails);
        contacts.setText(location.getContact().toString());

        categories = (TextView) view.findViewById(R.id.categories);
        categories.setText(location.getCategory().toString());

        return view;
    }

}
