package swp.swp16_impl_nst.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;

import java.util.List;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.adapters.viewholders.LocationHeadViewHolder;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.activities.LocationTabbedActivity;
import swp.swp16_impl_nst.locations.model.Address;
import swp.swp16_impl_nst.locations.model.Contact;
import swp.swp16_impl_nst.locations.model.Location;
import swp.swp16_impl_nst.adapters.viewholders.LocationTailViewHolder;
import swp.swp16_impl_nst.utils.Constants;


public class LocationAdapter extends AbstractExpandableItemAdapter<LocationHeadViewHolder, LocationTailViewHolder>
{
    private List<Location> locations;
    private RecyclerViewExpandableItemManager itemManager;

    public LocationAdapter(RecyclerViewExpandableItemManager manager)
    {
        setHasStableIds(true); // this is required for expandable feature.
        locations = LocationProvider.locations;
        itemManager = manager;
    }

    //
    // LocationHeadViewHolder
    //
    @Override
    public LocationHeadViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_location, parent, false);
        return new LocationHeadViewHolder(v);
    }

    @Override
    public void onBindGroupViewHolder(LocationHeadViewHolder holder, int groupPosition, int viewType)
    {
        Location location = locations.get(groupPosition);

        holder.setName(location.getName());
        holder.setIcon(location.getCategory().getIconId());

        if (location.getRating() != null)
            holder.setRatingBar(location.getRating().ordinal());

        // set background resource (target view ID: container)
        final int expandState = holder.getExpandStateFlags();
        if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_UPDATED) != 0)
            if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_EXPANDED) != 0)
                holder.setBackgroundColor(R.color.rview_selected);
            else
                holder.setBackgroundColor(R.color.rview_background);
    }

    //
    // LocationTailViewHolder
    //
    @Override
    public LocationTailViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_location_details, parent, false);
        return new LocationTailViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(final LocationTailViewHolder holder, final int groupPosition, int childPosition, int viewType)
    {
        final Location location = locations.get(groupPosition);
        Address address = location.getAddress();
        Contact contact = location.getContactDetails();

        // set background resource (target view ID: container)
        final int expandState = holder.getExpandStateFlags();
        if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_UPDATED) != 0)
            if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_EXPANDED) != 0)
                holder.setBackgroundColor(R.color.background);
            else
                holder.setBackgroundColor(R.color.background);

        ImageButton editBtn = holder.getEditBtn();
        editBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Context context = view.getContext();
                Intent intent = new Intent(context, LocationTabbedActivity.class);
                intent.putExtra(Constants.LOCATION_ITEM, groupPosition);
                context.startActivity(intent);
            }
        });

        ImageButton deleteBtn = holder.getDeleteBtn();
        deleteBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                locations.remove(location);
                itemManager.collapseAll();
                notifyDataSetChanged();
            }
        });

        ImageButton gpsMapBtn = holder.getGpsMapBtn();
        gpsMapBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        holder.setCategoryView(location.getCategory().getName());

        if (address == null || address.isEmpty())
        {
            holder.addressMakeVisible(false);
            holder.warningMakeVisible(true);
        }
        else
        {
            holder.addressMakeVisible(true);
            holder.warningMakeVisible(false);

            if (address.getStreet() != null)
                holder.setStreetView(address.getStreet());

            if (address.getNumber() != null)
                holder.setStreerNrView(address.getNumber());

            if (address.getZip() != null)
                holder.setPostalCodeView(address.getZip());

            if (address.getCity() != null)
                holder.setCityView(address.getCity());

            if (address.getCountry() != null)
                holder.setCountry(address.getCountry());
        }

        if (contact == null || contact.isEmpty())
        {
            holder.contactMakeVisible(false);
            holder.getContactsIcon().setVisibility(View.GONE);
        }
        else
        {
            holder.contactMakeVisible(true);
            holder.getContactsIcon().setVisibility(View.VISIBLE);

            if (contact.getEmail() != null)
                holder.setEmailView(contact.getEmail());
            else
                holder.getEmailView().setVisibility(View.GONE);

            if (contact.getTelephone() != null)
                holder.setPhonenumberView(contact.getTelephone());
            else
                holder.getPhonenumberView().setVisibility(View.GONE);

            if (contact.getWeb() != null)
                holder.setHomepageView(contact.getWeb());
            else
                holder.getHomepageView().setVisibility(View.GONE);
        }

        if (location.getComment() == null || location.getComment().isEmpty())
            holder.getCommentView().setVisibility(View.GONE);
        else
            holder.setCommentView(location.getComment());

    }

    //
    // Misc
    //
    @Override
    public boolean onCheckCanExpandOrCollapseGroup(
            LocationHeadViewHolder holder,
            int groupPosition,
            int x, int y,
            boolean expand)
    {
        return true;
    }

    @Override
    public int getGroupCount()
        { return locations.size(); }

    @Override
    public long getGroupId(int groupPosition)
        { return groupPosition; }

    @Override
    public int getChildCount(int groupPosition)
        { return 1; }

    @Override
    public long getChildId(int groupPosition, int childPosition)
        { return 1; }


    //
    // Adapter methods
    //
    public List<Location> getDataSet()
        { return locations; }

    public void replaceDataSet(List<Location> newData)
    {
        locations.clear();
        locations.addAll(newData);
    }
}

