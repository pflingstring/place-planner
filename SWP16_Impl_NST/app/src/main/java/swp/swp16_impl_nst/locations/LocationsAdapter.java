package swp.swp16_impl_nst.locations;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import swp.swp16_impl_nst.R;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.ViewHolder>
{
    private List<Location> dataSet;

    public LocationsAdapter(List<Location> data)
    { dataSet = data; }


    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView locationName;

        public ViewHolder(View view)
        {
            super(view);
            locationName = (TextView) view.findViewById(R.id.location_name);
        }

        public void setLocationName(String name)
        {
            locationName.setText(name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.view_location_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Location location = dataSet.get(position);
        holder.setLocationName(location.getName() + "\n" + location.getCategory().getName());
    }

    @Override
    public int getItemCount()
    {
        return dataSet.size();
    }

}
