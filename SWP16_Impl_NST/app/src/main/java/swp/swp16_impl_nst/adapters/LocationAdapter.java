package swp.swp16_impl_nst.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import java.util.List;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.model.Address;
import swp.swp16_impl_nst.locations.model.Location;


public class LocationAdapter extends AbstractExpandableItemAdapter<HeadViewHolder, TailViewHolder>
{
    private List<Location> locations;

    public LocationAdapter()
    {
        setHasStableIds(true); // this is required for expandable feature.
        locations = LocationProvider.getLocationsCopy();
    }

    public List<Location> getDataSet()
        { return locations; }

    public void replaceDataSet(List<Location> newData)
    {
        locations.clear();
        locations.addAll(newData);
    }


    // head
    @Override
    public HeadViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_location, parent, false);
        return new HeadViewHolder(v);
    }

    @Override
    public void onBindGroupViewHolder(HeadViewHolder holder, int groupPosition, int viewType)
    {
        Location location = locations.get(groupPosition);

        holder.setName(location.getName());
        holder.setIcon(location.getCategory().getIconId());

        if (location.getRating() != null)
        {
            holder.setRatingBar(location.getRating().ordinal());
        }

        // set background resource (target view ID: container)
        final int expandState = holder.getExpandStateFlags();

        if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_UPDATED) != 0)
        {
            if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_EXPANDED) != 0)
            {
                holder.setBackgroundColor(R.color.rview_background);
            }
            else
            {
                holder.setBackgroundColor(R.color.background);
            }
        }

    }

    @Override
    public int getGroupCount()
        { return locations.size(); }

    @Override
    public long getGroupId(int groupPosition)
        { return groupPosition; }


    // tail
    @Override
    public int getChildCount(int groupPosition)
        { return 1; }

    @Override
    public long getChildId(int groupPosition, int childPosition)
        { return 1; }


    @Override
    public TailViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_location_details, parent, false);
        return new TailViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(TailViewHolder holder, int groupPosition, int childPosition, int viewType)
    {

        Location location = locations.get(groupPosition);
        holder.setCategoryView(location.getCategory().getName());

        if (location.getAddress() != null)
        {
            Address address = location.getAddress();
            if (address.getZip() != null)
            {
                holder.setPostalCodeView(address.getZip());
            }
            holder.setCityView(address.getCity());
            holder.setStreetView(address.getStreet());
            holder.setStreerNrView(address.getNumber());
            //                if (address.getCountry() != null)
            //                {
            //                    holder.setCountry(address.getCountry());
            //                }
        }

        //            if (location.getComment() != null)
        //                holder.setCommentView(location.getComment());
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(HeadViewHolder holder, int groupPosition, int x, int y, boolean expand)
    {
        return true;
    }
}


abstract class LocationHeadViewHolder extends AbstractExpandableItemViewHolder
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

abstract class LocationTailViewHolder extends AbstractExpandableItemViewHolder
{
    TextView commentView;
    TextView streetView;
    TextView streerNrView;
    TextView postalCodeView;
    TextView cityView;
    TextView country;
    TextView categoryView;
    TextView homepageView;
    TextView phonenumberView;


    public LocationTailViewHolder(View view)
    {
        super(view);
        cityView = (TextView) view.findViewById(R.id.address_city);
        country = (TextView) view.findViewById(R.id.address_country);
        streetView = (TextView) view.findViewById(R.id.address_street);
        streerNrView = (TextView) view.findViewById(R.id.street_number);
        postalCodeView = (TextView) view.findViewById(R.id.address_postal_code);
        commentView = (TextView) view.findViewById(R.id.comment);
        categoryView = (TextView) view.findViewById(R.id.category_main_name);
        homepageView = (TextView) view.findViewById(R.id.homepage);
        phonenumberView = (TextView) view.findViewById(R.id.phone_number);
    }

    // setters
    public void setCommentView(String commentView)
        { this.commentView.setText(commentView); }

    public void setStreetView(String streetView)
        { this.streetView.setText(streetView); }

    public void setStreerNrView(String streerNrView)
        { this.streerNrView .setText(streerNrView); }

    public void setPostalCodeView(String postalCodeView)
        { this.postalCodeView.setText(postalCodeView); }

    public void setCityView(String cityView)
        { this.cityView.setText(cityView); }

    public void setCountry(String country)
        { this.country.setText(country); }

    public void setCategoryView(String categoryView)
        { this.categoryView.setText(categoryView); }

    public void setHomepageView(String homepageView)
        { this.homepageView.setText(homepageView); }

    public void setPhonenumberView(String phonenumberView)
        { this.phonenumberView.setText(phonenumberView); }
}

class HeadViewHolder extends LocationHeadViewHolder
{
    public HeadViewHolder(View itemView)
        { super(itemView); }
}

class TailViewHolder extends LocationTailViewHolder
{
    public TailViewHolder(View itemView)
        { super(itemView); }
}
