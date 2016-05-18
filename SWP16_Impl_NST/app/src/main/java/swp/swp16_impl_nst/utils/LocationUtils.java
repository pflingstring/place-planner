package swp.swp16_impl_nst.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.views.fragments.LocationDetailsFragment;
import swp.swp16_impl_nst.locations.views.fragments.LocationEditFragment;
import swp.swp16_impl_nst.locations.model.Location;

public class LocationUtils
{
    // TODO: refactor. eliminate code duplication
    public static void populateViews(
            @NonNull Fragment fragment,
            @NonNull View view,
            Location location)
    {
        if (fragment instanceof LocationEditFragment)
        {
            EditText name  = (EditText) view.findViewById(R.id.name);
            EditText comment = (EditText) view.findViewById(R.id.comment);
            EditText mediaUrl    = (EditText) view.findViewById(R.id.mediaUrl);

            if (location != null)
            {
                name.setText(location.getName());
                comment.setText(location.getComment());
                mediaUrl.setText(location.getMediaUrl());
            }

            ((LocationEditFragment) fragment).setName(name);
            ((LocationEditFragment) fragment).setComment(comment);
            ((LocationEditFragment) fragment).setMediaUrl(mediaUrl);
        }
        else // is instance of LocationDetailsFragment
        {
            TextView name  = (TextView) view.findViewById(R.id.location_name);
            TextView country = (TextView) view.findViewById(R.id.country);
            TextView address_city = (TextView) view.findViewById(R.id.address_city);
            TextView address_street = (TextView) view.findViewById(R.id.address_street);
            TextView address_number = (TextView) view.findViewById(R.id.street_number);
            TextView category_main_name = (TextView) view.findViewById(R.id.category_main_name);
            TextView address_postal_code = (TextView) view.findViewById(R.id.address_postal_code);
            RatingBar ratingBar = (RatingBar) view.findViewById(R.id.rating);

            if (location != null)
            {
                name.setText(location.getName());
                ratingBar.setNumStars(location.getRating().ordinal());
                address_city.setText(location.getAddress().getCity());
                address_street.setText(location.getAddress().getStreet());
                address_number.setText(location.getAddress().getNumber());
                category_main_name.setText(location.getCategory().getName());
                address_postal_code.setText(location.getAddress().getPostal_code());

                String country_str = location.getAddress().getCountry();
                if (country_str == null)
                    country.setVisibility(View.GONE);
                else
                    country.setText(country_str);
            }

            ((LocationDetailsFragment) fragment).setName(name);
            ((LocationDetailsFragment) fragment).setCountry(country);
            ((LocationDetailsFragment) fragment).setRatingBar(ratingBar);
            ((LocationDetailsFragment) fragment).setAddress_city(address_city);
            ((LocationDetailsFragment) fragment).setAddress_street(address_street);
            ((LocationDetailsFragment) fragment).setAddress_number(address_number);
            ((LocationDetailsFragment) fragment).setCategory_main_name(category_main_name);
            ((LocationDetailsFragment) fragment).setAddress_postal_code(address_postal_code);
        }
    }
}
