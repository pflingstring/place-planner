package swp.swp16_impl_nst.locations;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.models.locations.Location;

public class LocationEditFragment extends Fragment 
{
    public LocationEditFragment()
    { /** Required empty public constructor */ }
    
    private final static String CURRENT_POSITION = LocationsMainActivity.CURRENT_POSITION;
    private Location location;
    private EditText name;
    private EditText address;
    private EditText comment;
    private EditText mediaUrl;
    private EditText rating;
    private EditText owner;
    private EditText coordinates;
    private EditText contacts;
    private EditText categories;
    
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LocationDetailsFragment.
     */
    public static LocationEditFragment newInstance(int position)
    {
        LocationEditFragment fragment = new LocationEditFragment();
        Bundle args = new Bundle();
        args.putInt(CURRENT_POSITION, position);
        fragment.setArguments(args);
        
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            location = LocationProvider.locations.get(
                    getArguments().getInt(CURRENT_POSITION));
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
            ViewGroup      container,
            Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_location_edit, container, false);

        // TODO: make a generic helper function; check for nulls
        if (location != null)
        {
            name = (EditText) view.findViewById(R.id.name);
            name.setText(location.getName());

            address = (EditText) view.findViewById(R.id.address);
            address.setText(location.getAddress().toString());

            comment = (EditText) view.findViewById(R.id.comment);
            comment.setText(location.getComment());

            mediaUrl = (EditText) view.findViewById(R.id.mediaUrl);
            mediaUrl.setText(location.getMediaUrl());

            rating = (EditText) view.findViewById(R.id.rating);
            rating.setText(location.getRating().toString());

            owner = (EditText) view.findViewById(R.id.owner);
            owner.setText(location.getOwner().toString());

            coordinates = (EditText) view.findViewById(R.id.gpsCoordinates);
            coordinates.setText(location.getCoordinates().toString());

            contacts = (EditText) view.findViewById(R.id.contactDetails);
            contacts.setText(location.getContact().toString());

            categories = (EditText) view.findViewById(R.id.categories);
            categories.setText(location.getCategory().toString());
        }

        return view;
    }
}
