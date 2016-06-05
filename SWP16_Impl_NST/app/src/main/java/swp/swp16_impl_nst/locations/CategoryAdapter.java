package swp.swp16_impl_nst.locations;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.views.CategoryShowActivity;

/**
 * Created by Simon on 02.06.2016.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolderKlasse> {

    public class ViewHolderKlasse extends RecyclerView.ViewHolder{

        TextView itemTextView;
        ImageView itemImageView;

        public ViewHolderKlasse(View itemView) {
            super(itemView);
            itemTextView = (TextView) itemView.findViewById(R.id.textViewItem);
            itemImageView = (ImageView) itemView.findViewById(R.id.imageViewItem);

        }
    }


    @Override
    public ViewHolderKlasse onCreateViewHolder(ViewGroup viewGroup, int i)
    {

        View itemView1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rview_category_item, null);

        return new ViewHolderKlasse(itemView1);
    }

    @Override
    public void onBindViewHolder(ViewHolderKlasse ViewHolderKlasse, int i) {

        ViewHolderKlasse.itemTextView.setText(CategoryShowActivity.itemListe.get(i));
        ViewHolderKlasse.itemImageView.setImageResource(CategoryShowActivity.itemIconId.get(i));



    }

    @Override
    public int getItemCount() {


        return CategoryShowActivity.itemListe.size();
    }
}
