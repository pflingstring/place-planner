package swp.swp16_impl_nst.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.friends.activities.fragments.FriendDetailsFragment;
import swp.swp16_impl_nst.friends.activities.fragments.FriendEditFragment;
import swp.swp16_impl_nst.friends.model.Friend;

/**
 * Created by Simon on 29.06.2016.
 */
public class FriendUtils {
    public static void populateViews(
            @NonNull Fragment fragment,
            @NonNull View view,
            Friend friend)
    {
        if (fragment instanceof FriendEditFragment)
        {
            EditText name  = (EditText) view.findViewById(R.id.name);
            EditText street = (EditText) view.findViewById(R.id.friend_street);
            EditText street_number  = (EditText) view.findViewById(R.id.friend_street_number);
            EditText zip = (EditText) view.findViewById(R.id.friend_zip);
            EditText city  = (EditText) view.findViewById(R.id.friend_city);
            EditText country = (EditText) view.findViewById(R.id.friend_country);
            EditText telephone  = (EditText) view.findViewById(R.id.friend_telephone);
            EditText email = (EditText) view.findViewById(R.id.friend_email);

            if (friend != null)
            {
                if (friend.getName() != null)
                {
                    name.setText(friend.getName());
                    street.setText(friend.getStreet());
                    street_number.setText(friend.getStreetNumber());
                    zip.setText(friend.getZip());
                    city.setText(friend.getCity());
                    country.setText(friend.getCountry());
                    telephone.setText(friend.getTelephone());
                    email.setText(friend.getEmail());
                }
            }

            ((FriendEditFragment) fragment).setName(name);
            ((FriendEditFragment) fragment).setStreet(street);
            ((FriendEditFragment) fragment).setStreet_number(street_number);
            ((FriendEditFragment) fragment).setZip(zip);
            ((FriendEditFragment) fragment).setCity(city);
            ((FriendEditFragment) fragment).setCountry(country);
            ((FriendEditFragment) fragment).setTelephone(telephone);
            ((FriendEditFragment) fragment).setEmail(email);
        }
        else // is instance of FriendDetailsFragment
        {
            TextView name  = (TextView) view.findViewById(R.id.friend_name);
            TextView street = (TextView) view.findViewById(R.id.friend_street);
            TextView street_number  = (TextView) view.findViewById(R.id.friend_street_number);
            TextView zip = (TextView) view.findViewById(R.id.friend_zip);
            TextView city  = (TextView) view.findViewById(R.id.friend_city);
            TextView country = (TextView) view.findViewById(R.id.friend_country);
            TextView telephone  = (TextView) view.findViewById(R.id.friend_telephone);
            TextView email = (TextView) view.findViewById(R.id.friend_email);

            if (friend != null)
            {
                if (street != null)
                    street.setText(friend.getStreet());
                if (street_number != null)
                    street_number.setText(friend.getStreetNumber());
                if (zip != null)
                    zip.setText(friend.getZip());
                if (city != null)
                    city.setText(friend.getCity());
                if (country != null)
                    country.setText(friend.getCountry());
                if (telephone != null)
                    telephone.setText(friend.getTelephone());
                if (email != null)
                    email.setText(friend.getEmail());

                name.setText(friend.getName());
            }

            ((FriendDetailsFragment) fragment).setName(name);
            ((FriendDetailsFragment) fragment).setStreet(street);
            ((FriendDetailsFragment) fragment).setStreet_number(street_number);
            ((FriendDetailsFragment) fragment).setZip(zip);
            ((FriendDetailsFragment) fragment).setCity(city);
            ((FriendDetailsFragment) fragment).setCountry(country);
            ((FriendDetailsFragment) fragment).setTelephone(telephone);
            ((FriendDetailsFragment) fragment).setEmail(email);
        }
    }
}
