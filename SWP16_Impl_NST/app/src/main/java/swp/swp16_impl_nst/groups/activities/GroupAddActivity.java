package swp.swp16_impl_nst.groups.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.groups.GroupProvider;
import swp.swp16_impl_nst.groups.model.Group;

/**
 * Created by Simon on 04.07.2016.
 */
public class GroupAddActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_add);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_group_add, menu);
        return true;
    }

    public void addGroup(View button){

        View view = button.getRootView();

        EditText nameView  = (EditText) view.findViewById(R.id.group_name);

        String name = nameView.getText().toString();

        if (name.length() == 0) {
            Toast toast = Toast.makeText(this, "Die Gruppe muss einen Namen besitzen", Toast.LENGTH_SHORT);
            toast.show();
        }

        else {
            GroupProvider.add(new Group(name));
            Toast toast = Toast.makeText(this, "Gruppe wurde hinzugefügt", Toast.LENGTH_SHORT);
            toast.show();
            navigateBack(null);
        }
    }

    public void navigateBack(View view)
    { NavUtils.navigateUpFromSameTask(this); }
}
