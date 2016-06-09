package swp.swp16_impl_nst.locations;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import swp.swp16_impl_nst.R;

public class ImportAdapter extends RecyclerView.Adapter<ImportAdapter.ViewHolder>
{
    private final List<String> dataSet;

    public ImportAdapter(List<String> data)
        { this.dataSet = data;}

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView fileName;

        public ViewHolder(View view)
        {
            super(view);
            fileName    = (TextView) view.findViewById(R.id.file_name);
        }

        public void setFileName(String name)
            { fileName.setText(name); }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.rview_import_file, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
        { holder.setFileName(dataSet.get(position)); }

    @Override
    public int getItemCount()
        { return dataSet.size(); }
}
