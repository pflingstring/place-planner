package swp.swp16_impl_nst.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.categories.model.Category;
import swp.swp16_impl_nst.categories.activities.fragments.CategoryDetailsFragment;
import swp.swp16_impl_nst.categories.activities.fragments.CategoryEditFragment;


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

            if (category != null)
            {
                if (category.getName() != null)
                {
                    name.setText(category.getName());
                    description.setText(category.getDescription());
                }
            }

            ((CategoryEditFragment) fragment).setName(name);
            ((CategoryEditFragment) fragment).setDescription(description);
        }
        else // is instance of CategoryDetailsFragment
        {
            TextView name    = (TextView) view.findViewById(R.id.category_name);
            TextView description = (TextView) view.findViewById(R.id.description);

            if (category != null)
            {
                if (description != null)
                    description.setText(category.getDescription());

                name.setText(category.getName());
            }

            ((CategoryDetailsFragment) fragment).setName(name);
            ((CategoryDetailsFragment) fragment).setDescription(description);
        }
    }
}
