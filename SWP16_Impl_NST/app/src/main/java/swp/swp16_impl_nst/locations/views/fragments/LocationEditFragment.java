package swp.swp16_impl_nst.locations.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.views.LocationsMainActivity;
import swp.swp16_impl_nst.locations.model.Location;
import swp.swp16_impl_nst.locations.model.fields.Address;
import swp.swp16_impl_nst.locations.model.fields.Category;
import swp.swp16_impl_nst.locations.model.fields.Contact;
import swp.swp16_impl_nst.locations.model.fields.GpsCoordinates;
import swp.swp16_impl_nst.locations.model.fields.Rating;
import swp.swp16_impl_nst.locations.model.fields.User;
import swp.swp16_impl_nst.utils.LocationUtils;

public class LocationEditFragment extends Fragment
    implements View.OnClickListener
{
    private final static String CURRENT_POSITION = LocationsMainActivity.CURRENT_POSITION;
    OnClickListener clickListener;
    private EditText name;
    private EditText owner;
    private EditText rating;
    private EditText address;
    private EditText comment;
    private EditText mediaUrl;
    private EditText contacts;
    private Location location;
    private EditText categories;
    private EditText coordinates;

    public LocationEditFragment()
    { /** Required empty public constructor */ }


    /**
     * Creates a new instance of this fragment
     *
     * @param position - position of the Location in the list
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
        {
            int locationPosition = getArguments().getInt(CURRENT_POSITION);
            location = LocationProvider.locations.get(locationPosition);
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        try
        {
            clickListener = (OnClickListener) context;
        }
        catch (ClassCastException e)
        {
            String className = context.getClass().getSimpleName();
            throw new ClassCastException(className + " must implement OnClickListener");
        }
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_location_edit, container, false);
        Button okButton = (Button) view.findViewById(R.id.okButton);
        okButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        if (getView() != null)
            LocationUtils.populateViews(this, getView(), location);
    }

    @Override
    public void onClick(View view)
    {
        Location location = new Location.Builder(name.getText().toString())
                .address(new Address())
                .category(new Category(1, "LOL"))
                .comment(comment.getText().toString())
                .mediaUrl(mediaUrl.getText().toString())
                .rating(Rating.NO_RATING)
                .owner(new User())
                .gpsCoordinates(new GpsCoordinates())
                .contact(new Contact())
                .build();

        clickListener.onOkButtonClicked(location);
    }


    public interface OnClickListener
    { void onOkButtonClicked(Location location); }


    // Setters
    public void setName(EditText name)
    { this.name = name; }

    public void setOwner(EditText owner)
    { this.owner = owner; }

    public void setRating(EditText rating)
    { this.rating = rating; }

    public void setAddress(EditText address)
    { this.address = address; }

    public void setComment(EditText comment)
    { this.comment = comment; }

    public void setContacts(EditText contacts)
    { this.contacts = contacts; }

    public void setMediaUrl(EditText mediaUrl)
    { this.mediaUrl = mediaUrl; }

    public void setCategories(EditText categories)
    { this.categories = categories; }

    public void setCoordinates(EditText coordinates)
    { this.coordinates = coordinates; }
}
