package swp.swp16_impl_nst.adapters.viewholders;


import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import swp.swp16_impl_nst.R;

public class LocationHeadViewHolder extends AbstractExpandableItemViewHolder
{
    RatingBar ratingBar;
    ImageView icon;
    TextView name;
    View parentView;

    public LocationHeadViewHolder(View view)
    {
        super(view);
        parentView = view;
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        name = (TextView) view.findViewById(R.id.location_name);
        icon = (ImageView) view.findViewById(R.id.icon);
    }

    public void setBackgroundColor(int color)
        { parentView.setBackgroundResource(color); }

    public void setRatingBar(float stars)
        { ratingBar.setRating(stars); }

    public void setName(String locationName)
        { name.setText(locationName); }

    public void setIcon(int id)
    {
    }
}

