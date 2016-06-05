package swp.swp16_impl_nst.locations.views;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import swp.swp16_impl_nst.R;

/**
 * Created by Simon on 01.06.2016.
 */
public class CategoryAddActivity extends AppCompatActivity {


    Button addCategory;
    EditText editText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_add);


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_category_add, menu);
        return true;
    }



    public void navigateBack(View view)
    { NavUtils.navigateUpFromSameTask(this); }
}
