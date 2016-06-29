package swp.swp16_impl_nst.friends.activities.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.friends.FriendProvider;
import swp.swp16_impl_nst.friends.activities.FriendShowActivity;
import swp.swp16_impl_nst.friends.model.Friend;
import swp.swp16_impl_nst.utils.FriendUtils;

/**
 * Created by Simon on 29.06.2016.
 */
public class FriendEditFragment extends Fragment
        implements View.OnClickListener
{
    private final static String CURRENT_POSITION = FriendShowActivity.CURRENT_POSITION;
    OnClickListener clickListener;
    private Friend friend;
    private EditText name;
    private TextView street;
    private TextView street_number;
    private TextView zip;
    private TextView city;
    private TextView country;
    private TextView telephone;
    private TextView email;



    public FriendEditFragment()
    { /** Required empty public constructor */ }


    /**
     * Creates a new instance of this fragment
     *
     * @param position - position of the Friend in the list
     * @return A new instance of fragment FriendDetailsFragment.
     */
    public static FriendEditFragment newInstance(int position)
    {
        FriendEditFragment fragment = new FriendEditFragment();
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
            int friendPosition = getArguments().getInt(CURRENT_POSITION);
            friend = FriendProvider.friends.get(friendPosition);
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
        View view = inflater.inflate(R.layout.fragment_friend_edit, container, false);
        Button okButton = (Button) view.findViewById(R.id.okButton);
        okButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        if (getView() != null)
            FriendUtils.populateViews(this, getView(), friend);
    }

    @Override
    public void onClick(View button)
    {
        View view = getView();

        EditText nameView  = (EditText) view.findViewById(R.id.name);
        EditText streetView = (EditText) view.findViewById(R.id.friend_street);
        EditText streetNumberView  = (EditText) view.findViewById(R.id.friend_street_number);
        EditText zipView = (EditText) view.findViewById(R.id.friend_zip);
        EditText cityView  = (EditText) view.findViewById(R.id.friend_city);
        EditText countryView = (EditText) view.findViewById(R.id.friend_country);
        EditText telephoneView  = (EditText) view.findViewById(R.id.friend_telephone);
        EditText emailView = (EditText) view.findViewById(R.id.friend_email);

        String name = nameView.getText().toString();
        String street = streetView.getText().toString();
        String streetNumber = streetNumberView.getText().toString();
        String zip = zipView.getText().toString();
        String city = cityView.getText().toString();
        String country = countryView.getText().toString();
        String telephone = telephoneView.getText().toString();
        String email = emailView.getText().toString();

        Friend friend = new Friend(name, street, streetNumber, zip, city, country, telephone, email);

        clickListener.onOkButtonClicked(friend);
    }


    public interface OnClickListener
    { void onOkButtonClicked(Friend friend); }


    // Setters
    public void setName(EditText name)
    { this.name = name; }

    public void setStreet(EditText street)
    { this.street = street; }

    public void setStreet_number(EditText street_number)
    { this.street_number = street_number; }

    public void setZip(EditText zip)
    { this.zip = zip; }

    public void setCity(EditText city)
    { this.city = city; }

    public void setCountry(EditText country)
    { this.country = country; }

    public void setTelephone(EditText telephone)
    { this.telephone = telephone; }

    public void setEmail(EditText email)
    { this.email = email; }

}
