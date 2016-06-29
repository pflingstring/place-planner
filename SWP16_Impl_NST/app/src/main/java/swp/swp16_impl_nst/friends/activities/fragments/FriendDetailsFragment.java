package swp.swp16_impl_nst.friends.activities.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.categories.activities.CategoryShowActivity;
import swp.swp16_impl_nst.friends.FriendProvider;
import swp.swp16_impl_nst.friends.model.Friend;
import swp.swp16_impl_nst.utils.FriendUtils;

/**
 * Created by Simon on 29.06.2016.
 */
public class FriendDetailsFragment extends android.support.v4.app.Fragment
{
    private final static String CURRENT_POSITION = CategoryShowActivity.CURRENT_POSITION;
    private Friend friend;
    private TextView name;
    private TextView street;
    private TextView street_number;
    private TextView zip;
    private TextView city;
    private TextView country;
    private TextView telephone;
    private TextView email;


    public FriendDetailsFragment()
    { /** Required empty public constructor */ }


    /**
     * Creates a new instance of this fragment
     *
     * @param position - position of the Friend in the list
     * @return A new instance of fragment CategoryDetailsFragment.
     */
    public static FriendDetailsFragment newInstance(int position)
    {
        FriendDetailsFragment fragment = new FriendDetailsFragment();
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
                              ViewGroup container,
                              Bundle savedInstanceState)
    { return inflater.inflate(R.layout.fragment_friend_details, container, false); }

    @Override
    public void onStart()
    {
        super.onStart();

        int friendPosition = getArguments().getInt(CURRENT_POSITION);
        friend = FriendProvider.friends.get(friendPosition);

        if (getView() != null)
            FriendUtils.populateViews(this, getView(), friend);
    }


    // setters
    public void setName(TextView name)
    { this.name = name; }

    public void setStreet(TextView street)
    { this.street = street; }

    public void setStreet_number(TextView street_number)
    { this.street_number = street_number; }

    public void setZip(TextView zip)
    { this.zip = zip; }

    public void setCity(TextView city)
    { this.city = city; }

    public void setCountry(TextView country)
    { this.country = country; }

    public void setTelephone(TextView telephone)
    { this.telephone = telephone; }

    public void setEmail(TextView email)
    { this.email = email; }
}
