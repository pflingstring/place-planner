package swp.swp16_impl_nst.locations;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.models.locations.Location;
import swp.swp16_impl_nst.models.locations.fields.Address;
import swp.swp16_impl_nst.models.locations.fields.Category;
import swp.swp16_impl_nst.models.locations.fields.Contact;
import swp.swp16_impl_nst.models.locations.fields.GpsCoordinates;
import swp.swp16_impl_nst.models.locations.fields.Rating;
import swp.swp16_impl_nst.models.locations.fields.User;
import swp.swp16_impl_nst.utils.LocationUtils;

public class LocationEditFragment extends Fragment
    implements View.OnClickListener
{
    public interface OnClickListener
    { void onSaveChanges(Location location); }

    public LocationEditFragment()
    { /** Required empty public constructor */ }

    private final static String CURRENT_POSITION =
          LocationsMainActivity.CURRENT_POSITION;
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
        {
            int locationPosition = getArguments().getInt(CURRENT_POSITION);
            location = LocationProvider.locations.get(locationPosition);
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try { clickListener = (OnClickListener) context; }
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
        Button button = (Button) view.findViewById(R.id.okButton);
        button.setOnClickListener(this);

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

        clickListener.onSaveChanges(location);

        Toast toast = Toast.makeText(getActivity(), "Created new Location", Toast.LENGTH_SHORT);
        toast.show();
    }



    // Setters
    public void setName(EditText name)
    { this.name = name; }

    public void setAddress(EditText address)
    { this.address = address; }

    public void setComment(EditText comment)
    { this.comment = comment; }

    public void setMediaUrl(EditText mediaUrl)
    { this.mediaUrl = mediaUrl; }

    public void setRating(EditText rating)
    { this.rating = rating; }

    public void setOwner(EditText owner)
    { this.owner = owner; }

    public void setCoordinates(EditText coordinates)
    { this.coordinates = coordinates; }

    public void setContacts(EditText contacts)
    { this.contacts = contacts; }

    public void setCategories(EditText categories)
    { this.categories = categories; }
}
