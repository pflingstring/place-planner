package swp.swp16_impl_nst.friends.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.friends.FriendProvider;
import swp.swp16_impl_nst.friends.model.Friend;

public class FriendAddActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_add);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_friend_add, menu);
        return true;
    }

    public void addFriend(View button){
        View view = button.getRootView();
        EditText nameView  = (EditText) view.findViewById(R.id.friend_name);
        EditText streetView = (EditText) view.findViewById(R.id.friend_street);
        EditText streetNumberView  = (EditText) view.findViewById(R.id.friend_street_number);
        EditText zipView = (EditText) view.findViewById(R.id.friend_zip);
        EditText cityView  = (EditText) view.findViewById(R.id.friend_city);
        EditText countryView = (EditText) view.findViewById(R.id.friend_country);
        EditText telephoneView  = (EditText) view.findViewById(R.id.friend_telephone);
        EditText emailView = (EditText) view.findViewById(R.id.friend_email);

        String name = nameView.getText().toString();
        String street = streetView.getText().toString();
        String streetNumber = streetNumberView.getText().toString();
        String zip = zipView.getText().toString();
        String city = cityView.getText().toString();
        String country = countryView.getText().toString();
        String telephone = telephoneView.getText().toString();
        String email = emailView.getText().toString();

        if (name.length() == 0) {
            Toast toast = Toast.makeText(this, "Der Freund muss einen Namen besitzen", Toast.LENGTH_SHORT);
            toast.show();
        }

        else {
            FriendProvider.add(new Friend(name, street, streetNumber, zip, city, country, telephone, email));
            Toast toast = Toast.makeText(this, "Freund wurde hinzugefügt", Toast.LENGTH_SHORT);
            toast.show();
            navigateBack(null);
        }
    }

    public void navigateBack(View view)
    { NavUtils.navigateUpFromSameTask(this); }
}
