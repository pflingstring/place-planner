package swp.swp16_impl_nst.categories.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.categories.CategoryProvider;
import swp.swp16_impl_nst.categories.model.Category;

public class CategoryAddActivity extends AppCompatActivity
{

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_add);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_category_add, menu);
        return true;
    }

    public void addCategory(View button){

        View view = button.getRootView();

        EditText nameView  = (EditText) view.findViewById(R.id.name);
        EditText descriptionView = (EditText) view.findViewById(R.id.description);

        String name = nameView.getText().toString();
        String description = descriptionView.getText().toString();

        //TODO: funktionsfähig machen
        if (name == null) {
            Toast toast = Toast.makeText(this, "Die Kategorie muss einen Namen besitzen", Toast.LENGTH_SHORT);
            toast.show();
        }

        else {
            CategoryProvider.add(new Category(0, name, description));
            Toast toast = Toast.makeText(this, "Kategorie wurde hinzugefügt", Toast.LENGTH_SHORT);
            toast.show();
            navigateBack(null);
        }
    }

    public void navigateBack(View view)
        { NavUtils.navigateUpFromSameTask(this); }
}
