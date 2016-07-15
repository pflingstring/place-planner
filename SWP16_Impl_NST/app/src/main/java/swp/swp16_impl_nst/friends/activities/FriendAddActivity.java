package swp.swp16_impl_nst.friends.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import java.util.List;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.model.Address;
import swp.swp16_impl_nst.locations.model.Location;

public class FriendAddActivity extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_add);

        RecyclerViewExpandableItemManager expandableManager = new RecyclerViewExpandableItemManager(null);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(expandableManager.createWrappedAdapter(new MyAdapter()));
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        expandableManager.attachRecyclerView(recyclerView);
    }


//    static class MyGroupItem extends MyBaseItem {
//        public final List<MyChildItem> children;
//
//        public MyGroupItem(long id, String text) {
//            super(id, text);
//            children = new ArrayList<>();
//        }
//    }

    static abstract class LocationHeadViewHolder extends AbstractExpandableItemViewHolder
    {
        RatingBar ratingBar;
        ImageView icon;
        TextView name;

        public LocationHeadViewHolder(View view)
        {
            super(view);
            ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            icon = (ImageView) view.findViewById(R.id.icon);
            name = (TextView) view.findViewById(R.id.location_name);
        }

        public void setRatingBar(float stars)
        { ratingBar.setRating(stars); }

        public void setName(String locationName)
        { name.setText(locationName); }
    }

    static abstract class LocationTailViewHolder extends AbstractExpandableItemViewHolder
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

        public void setCommentView(String commentView)
        {
            this.commentView.setText(commentView);
        }

        public void setStreetView(String streetView)
        {
            this.streetView.setText(streetView);
        }

        public void setStreerNrView(String streerNrView)
        {
            this.streerNrView .setText(streerNrView);
        }

        public void setPostalCodeView(String postalCodeView)
        {
            this.postalCodeView.setText(postalCodeView);
        }

        public void setCityView(String cityView)
        {
            this.cityView.setText(cityView);
        }

        public void setCountry(String country)
        {
            this.country.setText(country);
        }

        public void setCategoryView(String categoryView)
        {
            this.categoryView.setText(categoryView);
        }

        public void setHomepageView(String homepageView)
        {
            this.homepageView.setText(homepageView);
        }

        public void setPhonenumberView(String phonenumberView)
        {
            this.phonenumberView.setText(phonenumberView);
        }

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
    }


    static class MyGroupViewHolder extends LocationHeadViewHolder
    {
        public MyGroupViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class MyChildViewHolder extends LocationTailViewHolder
    {
        public MyChildViewHolder(View itemView) {
            super(itemView);
        }
    }


    static class MyAdapter extends AbstractExpandableItemAdapter<MyGroupViewHolder, MyChildViewHolder>
    {
        List<Location> mItems;

        public MyAdapter()
        {
            setHasStableIds(true); // this is required for expandable feature.
            mItems = LocationProvider.getLocationsCopy();
        }

        @Override
        public int getGroupCount()
        {
            return mItems.size();
        }

        @Override
        public int getChildCount(int groupPosition)
        {
//            return mItems.get(groupPosition).children.size();
            return 1;
        }

        @Override
        public long getGroupId(int groupPosition)
        {
            // This method need to return unique value within all group items.
//            return mItems.get(groupPosition).id;
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition)
        {
            // This method need to return unique value within the group.
//            return mItems.get(groupPosition).children.get(childPosition).id;
            return 1;
        }

        @Override
        public MyGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_location, parent, false);
            return new MyGroupViewHolder(v);
        }

        @Override
        public MyChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_location_details, parent, false);
            return new MyChildViewHolder(v);
        }

        @Override
        public void onBindGroupViewHolder(MyGroupViewHolder holder, int groupPosition, int viewType)
        {
            Location location = mItems.get(groupPosition);
            holder.setName(location.getName());
            if (location.getRating() != null)
            {
                holder.setRatingBar(location.getRating().ordinal());
            }
        }

        @Override
        public void onBindChildViewHolder(MyChildViewHolder holder, int groupPosition, int childPosition, int viewType)
        {

            Location location = mItems.get(groupPosition);
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
        public boolean onCheckCanExpandOrCollapseGroup(MyGroupViewHolder holder, int groupPosition, int x, int y, boolean expand)
        {
            return true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_friend_add, menu);
        return true;
    }

    public void navigateBack(View view)
    {
        NavUtils.navigateUpFromSameTask(this);
    }
}
