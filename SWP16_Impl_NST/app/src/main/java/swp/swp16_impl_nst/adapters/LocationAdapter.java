package swp.swp16_impl_nst.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.model.Location;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder>
{
    private List<Location> dataSet;

    public LocationAdapter(List<Location> data)
    { dataSet = data; }

    public List<Location> getDataSet()
    { return dataSet; }

    public void replaceDataSet(List<Location> positions)
    {
        dataSet.clear();
        dataSet.addAll(positions);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView icon;
        public TextView  locationName;
        public TextView  locationComment;

        public ViewHolder(View view)
        {
            super(view);
            icon = (ImageView) view.findViewById(R.id.icon);
            locationName    = (TextView) view.findViewById(R.id.location_name);
            locationComment = (TextView) view.findViewById(R.id.location_comment);
        }

        public void setLocationName(String name)
        { locationName.setText(name); }

        public void setLocationComment(String comment)
        { locationComment.setText(comment); }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new ViewHolder(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.view_location, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final Location location = dataSet.get(position);
        holder.setLocationName(location.getName());
        holder.setLocationComment(location.getComment());
    }

    @Override
    public int getItemCount()
    {
        return dataSet.size();
    }

}
