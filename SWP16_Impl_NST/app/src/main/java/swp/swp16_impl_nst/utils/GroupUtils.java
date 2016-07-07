package swp.swp16_impl_nst.utils;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.groups.activities.fragments.GroupDetailsFragment;
import swp.swp16_impl_nst.groups.activities.fragments.GroupEditFragment;
import swp.swp16_impl_nst.groups.model.Group;

public class GroupUtils {
    public static void populateViews(
            @NonNull Fragment fragment,
            @NonNull View view,
            Group group)
    {
        if (fragment instanceof GroupEditFragment)
        {
            EditText name  = (EditText) view.findViewById(R.id.group_name);

            if (group != null)
            {
                if (group.getName() != null)
                {
                    name.setText(group.getName());
                }
            }

            ((GroupEditFragment) fragment).setName(name);
        }
        else // is instance of GroupDetailsFragment
        {
            TextView name    = (TextView) view.findViewById(R.id.group_name);

            if (group != null)
            {
                name.setText(group.getName());
            }

            ((GroupDetailsFragment) fragment).setName(name);
        }
    }
}
