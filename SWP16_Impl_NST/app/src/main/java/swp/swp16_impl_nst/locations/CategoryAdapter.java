package swp.swp16_impl_nst.locations;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.model.Category;
import swp.swp16_impl_nst.locations.views.CategoryShowActivity;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>
{
    private List<Category> dataSet;

    public CategoryAdapter(List<Category> dataSet)
        { this.dataSet = dataSet;}


    static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView name;
        private ImageView icon;

        public ViewHolder(View itemView)
        {
            super(itemView);
            name = (TextView)  itemView.findViewById(R.id.name);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }

        void setName(String name)
            { this.name.setText(name); }

        void setIcon(int id)
            { icon.setImageResource(id); }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.rview_category_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position)
    {
        Category category = dataSet.get(position);
        viewHolder.setName(category.getName());
        viewHolder.setIcon(category.getIconId());
    }

    @Override
    public int getItemCount()
        { return dataSet.size(); }
}
