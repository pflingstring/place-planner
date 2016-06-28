package swp.swp16_impl_nst.friends;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.friends.model.Friend;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder>
{
    private List<Friend> dataSet;

    public FriendAdapter(List<Friend> dataSet)
    { this.dataSet = dataSet;}


    static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView name;
        private TextView street;
        private TextView street_number;
        private TextView zip;
        private TextView city;
        private TextView country;
        private TextView telephone;
        private TextView email;

        public ViewHolder(View itemView)
        {
            super(itemView);
            name = (TextView)  itemView.findViewById(R.id.friend_name);
            street = (TextView) itemView.findViewById(R.id.friend_street);
            street_number = (TextView) itemView.findViewById(R.id.friend_street_number);
            zip = (TextView) itemView.findViewById(R.id.friend_zip);
            city = (TextView) itemView.findViewById(R.id.friend_city);
            country = (TextView) itemView.findViewById(R.id.friend_country);
            telephone = (TextView) itemView.findViewById(R.id.friend_telephone);
            email = (TextView) itemView.findViewById(R.id.friend_email);
        }

        void setName(String name)
        { this.name.setText(name); }

        void setStreet(String street)
        { this.street.setText(street); }

        void setStreet_number(String streetNumber)
        { this.street_number.setText(streetNumber);}

        void setZip(String zip)
        { this.zip.setText(zip);}

        void setCity(String city)
        { this.city.setText(city);}

        void setCountry(String country)
        { this.country.setText(country);}

        void setTelephone(String telephone)
        { this.telephone.setText(telephone);}

        void setEmail(String email)
        { this.email.setText(email);}
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.rview_friend_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position)
    {
        Friend friend = dataSet.get(position);
        viewHolder.setName(friend.getName());
        viewHolder.setStreet(friend.getStreet());
        viewHolder.setStreet_number(friend.getStreetNumber());
        viewHolder.setZip(friend.getZip());
        viewHolder.setCity(friend.getCity());
        viewHolder.setCountry(friend.getCountry());
        viewHolder.setTelephone(friend.getTelephone());
        viewHolder.setEmail(friend.getEmail());
    }

    @Override
    public int getItemCount()
    { return dataSet.size(); }
}
