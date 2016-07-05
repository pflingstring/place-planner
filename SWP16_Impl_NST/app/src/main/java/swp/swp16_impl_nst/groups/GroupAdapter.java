package swp.swp16_impl_nst.groups;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.groups.model.Group;

/**
 * Created by Simon on 01.07.2016.
 */
public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder>{
    private List<Group> dataSet;

    public GroupAdapter(List<Group> dataSet)
    { this.dataSet = dataSet;}


    static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView name;

        public ViewHolder(View itemView)
        {
            super(itemView);
            name = (TextView)  itemView.findViewById(R.id.group_name);
        }

        void setName(String name)
        { this.name.setText(name); }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.rview_group_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position)
    {
        Group group = dataSet.get(position);
        viewHolder.setName(group.getName());
    }

    @Override
    public int getItemCount()
    { return dataSet.size(); }
}
