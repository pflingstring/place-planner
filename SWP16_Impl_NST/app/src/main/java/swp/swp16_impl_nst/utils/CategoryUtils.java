package swp.swp16_impl_nst.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.model.Category;
import swp.swp16_impl_nst.locations.views.fragments.CategoryDetailsFragment;
import swp.swp16_impl_nst.locations.views.fragments.CategoryEditFragment;


public class CategoryUtils {
    public static void populateViews(
            @NonNull Fragment fragment,
            @NonNull View view,
            Category category)
    {
        if (fragment instanceof CategoryEditFragment)
        {
            EditText name  = (EditText) view.findViewById(R.id.name);
            EditText description = (EditText) view.findViewById(R.id.description);
            EditText icon  = (EditText) view.findViewById(R.id.icon);

            if (category != null)
            {
                if (category.getName() != null)
                {
                    name.setText(category.getName());
                    description.setText(category.getDescription());
                    icon.setText(category.getIconId());
                }




            }

            ((CategoryEditFragment) fragment).setName(name);
            ((CategoryEditFragment) fragment).setDescription(description);
            ((CategoryEditFragment) fragment).setIconId(icon);

        }
        else // is instance of CategoryDetailsFragment
        {

            TextView name    = (TextView) view.findViewById(R.id.category_name);
            TextView description = (TextView) view.findViewById(R.id.description);
            TextView icon = (TextView) view.findViewById(R.id.icon);


            if (category != null)
            {
                name.setText(category.getName());
                description.setText(category.getDescription());
                icon.setText(category.getIconId());






            }

            ((CategoryDetailsFragment) fragment).setName(name);
            ((CategoryDetailsFragment) fragment).setDescription(description);
            ((CategoryDetailsFragment) fragment).setIconId(icon);
        }
    }
}
