package swp.swp16_impl_nst.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.LocationEditFragment;
import swp.swp16_impl_nst.models.locations.Location;

public class LocationUtils
{
    public static void populateViews(
            @NonNull Fragment fragment,
            @NonNull View view,
            Location location)
    {
        EditText name  = (EditText) view.findViewById(R.id.name);
        EditText owner = (EditText) view.findViewById(R.id.owner);
        EditText rating  = (EditText) view.findViewById(R.id.rating);
        EditText comment = (EditText) view.findViewById(R.id.comment);
        EditText address = (EditText) view.findViewById(R.id.address);
        EditText mediaUrl    = (EditText) view.findViewById(R.id.mediaUrl);
        EditText categories  = (EditText) view.findViewById(R.id.categories);
        EditText contacts    = (EditText) view.findViewById(R.id.contactDetails);
        EditText coordinates = (EditText) view.findViewById(R.id.gpsCoordinates);

        if (location != null)
        {
            name.setText(location.getName());
            comment.setText(location.getComment());
            mediaUrl.setText(location.getMediaUrl());
            owner.setText(location.getOwner().toString());
            rating.setText(location.getRating().toString());
            address.setText(location.getAddress().toString());
            contacts.setText(location.getContact().toString());
            categories.setText(location.getCategory().toString());
            coordinates.setText(location.getCoordinates().toString());
        }

        ((LocationEditFragment) fragment).setName(name);
        ((LocationEditFragment) fragment).setOwner(owner);
        ((LocationEditFragment) fragment).setRating(rating);
        ((LocationEditFragment) fragment).setComment(comment);
        ((LocationEditFragment) fragment).setAddress(address);
        ((LocationEditFragment) fragment).setContacts(contacts);
        ((LocationEditFragment) fragment).setMediaUrl(mediaUrl);
        ((LocationEditFragment) fragment).setCategories(categories);
        ((LocationEditFragment) fragment).setCoordinates(coordinates);
    }
}
