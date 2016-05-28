package swp.swp16_impl_nst.locations.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.views.LocationsMainActivity;
import swp.swp16_impl_nst.locations.model.Location;
import swp.swp16_impl_nst.locations.model.Address;
import swp.swp16_impl_nst.locations.model.Category;
import swp.swp16_impl_nst.locations.model.Contact;
import swp.swp16_impl_nst.locations.model.GpsCoordinates;
import swp.swp16_impl_nst.locations.model.Rating;
import swp.swp16_impl_nst.locations.model.User;
import swp.swp16_impl_nst.utils.LocationUtils;

public class LocationEditFragment extends Fragment
    implements View.OnClickListener
{
    private final static String CURRENT_POSITION = LocationsMainActivity.CURRENT_POSITION;
    OnClickListener clickListener;
    private Location location;
    private EditText name;
    private EditText email;
    private EditText comment;
    private EditText gps_lat;
    private EditText gps_long;
    private EditText mediaUrl;
    private EditText web_address;
    private EditText phone_number;
    private EditText address_city;
    private EditText address_street;
    private EditText address_number;
    private EditText address_country;
    private EditText address_postal_code;


    private RatingBar ratingBar;


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
    public void onClick(View button)
    {
        View view = getView();

        EditText nameView  = (EditText) view.findViewById(R.id.name);
        EditText emailView = (EditText) view.findViewById(R.id.email);
        EditText gpsLatView  = (EditText) view.findViewById(R.id.gpsLat);
        EditText gpsLongView = (EditText) view.findViewById(R.id.gpsLong);
        EditText commentView = (EditText) view.findViewById(R.id.comment);
        EditText web_addressView = (EditText) view.findViewById(R.id.email);
        EditText mediaUrlView    = (EditText) view.findViewById(R.id.mediaUrl);
        EditText phone_numberView = (EditText) view.findViewById(R.id.phone_number);
        EditText address_cityView = (EditText) view.findViewById(R.id.address_city);
        EditText address_streetView = (EditText) view.findViewById(R.id.address_street);
        EditText address_numberView = (EditText) view.findViewById(R.id.address_number);
        EditText address_countryView = (EditText) view.findViewById(R.id.address_country);
        EditText address_postal_codeView = (EditText) view.findViewById(R.id.address_postal_code);
        RatingBar ratingBarView = (RatingBar) view.findViewById(R.id.ratingBar);

        String name = nameView.getText().toString();

        // get address
        String street = address_streetView.getText().toString();
        String number = address_numberView.getText().toString();
        String postal_code = address_postal_codeView.getText().toString();
        String city = address_cityView.getText().toString();
        String country = address_countryView.getText().toString();
        Address address = new Address(street, number, postal_code, city, country);

        // get gpsCoordinates
        GpsCoordinates gpsCoordinates;
        if (!gpsLatView.getText().toString().equals("") && !gpsLongView.getText().toString().equals(""))
        {
            double lat = Double.parseDouble(gpsLatView.getText().toString());
            double lng = Double.parseDouble(gpsLongView.getText().toString());
            gpsCoordinates = new GpsCoordinates(lat, lng);
        }
        else
        {
            gpsCoordinates = null;
        }

        // get contacts
        Contact contact;
        if (web_addressView.getText() != null && emailView.getText() != null && phone_numberView.getText() != null)
        {
            String web_address = web_addressView.getText().toString();
            String email = emailView.getText().toString();
            String phone_number = phone_numberView.getText().toString();
            contact = new Contact(web_address, email, phone_number);
        }
        else
        {
            contact = null;
        }

        String comment = commentView.getText().toString();
        String mediaUrl = mediaUrlView.getText().toString();

        Location location = new Location.Builder(name)
                .address(address)
                .gpsCoordinates(gpsCoordinates)
                .contact(contact)
                .comment(comment)
                .mediaUrl(mediaUrl)
                .rating(Rating.NO_RATING)
                .category(new Category(1, "Default Category"))     // TODO: implement category
                .build();

        // TODO: update `Edit` fragment with the new/edited values
        clickListener.onOkButtonClicked(location);
    }


    public interface OnClickListener
    { void onOkButtonClicked(Location location); }


    // Setters
    public void setName(EditText name)
    { this.name = name; }

    public void setEmail(EditText email)
    { this.email = email; }

    public void setGps_lat(EditText gps_lat)
    { this.gps_lat = gps_lat; }

    public void setComment(EditText comment)
    { this.comment = comment; }

    public void setGps_long(EditText gps_long)
    { this.gps_long = gps_long; }

    public void setMediaUrl(EditText mediaUrl)
    { this.mediaUrl = mediaUrl; }

    public void setRatingBar(RatingBar ratingBar)
    { this.ratingBar = ratingBar; }

    public void setWeb_address(EditText web_address)
    { this.web_address = web_address; }

    public void setPhone_number(EditText phone_number)
    { this.phone_number = phone_number; }

    public void setAddress_city(EditText address_city)
    { this.address_city = address_city; }

    public void setAddress_street(EditText address_street)
    { this.address_street = address_street; }

    public void setAddress_number(EditText address_number)
    { this.address_number = address_number; }

    public void setAddress_country(EditText address_country)
    { this.address_country = address_country; }

    public void setAddress_postal_code(EditText address_postal_code)
    { this.address_postal_code = address_postal_code; }
}
