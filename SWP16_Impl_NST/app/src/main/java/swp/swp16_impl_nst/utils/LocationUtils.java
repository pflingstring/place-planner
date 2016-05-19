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
    // TODO:
    public static void populateViews(
            @NonNull Fragment fragment,
            @NonNull View view,
            Location location)
    {
        if (fragment instanceof LocationEditFragment)
        {
            EditText name  = (EditText) view.findViewById(R.id.name);
            EditText email = (EditText) view.findViewById(R.id.email);
            EditText gpsLat  = (EditText) view.findViewById(R.id.gpsLat);
            EditText gpsLong = (EditText) view.findViewById(R.id.gpsLong);
            EditText comment = (EditText) view.findViewById(R.id.comment);
            EditText web_address = (EditText) view.findViewById(R.id.email);
            EditText mediaUrl    = (EditText) view.findViewById(R.id.mediaUrl);
            EditText phone_number = (EditText) view.findViewById(R.id.phone_number);
            EditText address_city = (EditText) view.findViewById(R.id.address_city);
            EditText address_street = (EditText) view.findViewById(R.id.address_street);
            EditText address_number = (EditText) view.findViewById(R.id.address_number);
            EditText address_country = (EditText) view.findViewById(R.id.address_country);
            EditText address_postal_code = (EditText) view.findViewById(R.id.address_postal_code);
            RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);

            if (location != null)
            {
                if (location.getContact() != null)
                {
                    email.setText(location.getContact().getEmail());
                    web_address.setText(location.getContact().getWebAddress());
                    phone_number.setText(location.getContact().getPhoneNumber());
                }

                if (location.getGpsCoordinates() != null)
                {
                    gpsLat.setText(String.valueOf(location.getGpsCoordinates().getLat()));
                    gpsLong.setText(String.valueOf(location.getGpsCoordinates().getLng()));
                }

                if (location.getAddress() != null)
                {
                    address_city.setText(location.getAddress().getCity());
                    address_street.setText(location.getAddress().getStreet());
                    address_number.setText(location.getAddress().getNumber());
                    address_country.setText(location.getAddress().getCountry());
                    address_postal_code.setText(location.getAddress().getPostal_code());
                }

                ratingBar.setRating(location.getRating());

                name.setText(location.getName());
                comment.setText(location.getComment());
                mediaUrl.setText(location.getMediaUrl());
            }

            ((LocationEditFragment) fragment).setName(name);
            ((LocationEditFragment) fragment).setEmail(email);
            ((LocationEditFragment) fragment).setGps_lat(gpsLat);
            ((LocationEditFragment) fragment).setComment(comment);
            ((LocationEditFragment) fragment).setGps_long(gpsLong);
            ((LocationEditFragment) fragment).setMediaUrl(mediaUrl);
            ((LocationEditFragment) fragment).setRatingBar(ratingBar);
            ((LocationEditFragment) fragment).setWeb_address(web_address);
            ((LocationEditFragment) fragment).setPhone_number(phone_number);
            ((LocationEditFragment) fragment).setAddress_city(address_city);
            ((LocationEditFragment) fragment).setAddress_street(address_street);
            ((LocationEditFragment) fragment).setAddress_number(address_number);
            ((LocationEditFragment) fragment).setAddress_country(address_country);
            ((LocationEditFragment) fragment).setAddress_postal_code(address_postal_code);
        }
        else // is instance of LocationDetailsFragment
        {
            TextView country = (TextView) view.findViewById(R.id.country);
            TextView name    = (TextView) view.findViewById(R.id.location_name);
            TextView address_city = (TextView) view.findViewById(R.id.address_city);
            TextView address_number = (TextView) view.findViewById(R.id.street_number);
            TextView address_street = (TextView) view.findViewById(R.id.address_street);
            TextView category_main_name  = (TextView) view.findViewById(R.id.category_main_name);
            TextView address_postal_code = (TextView) view.findViewById(R.id.address_postal_code);
            RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);

            if (location != null)
            {
                name.setText(location.getName());
                ratingBar.setNumStars((int) location.getRating() + 1);
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
