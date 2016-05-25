package swp.swp16_impl_nst.locations.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import swp.swp16_impl_nst.R;


/**
 * Created by Simon on 18.05.2016.
 */
public class LocationImportActivity extends AppCompatActivity{

    private Button importButton;
    private Button cancelButton;
    private Button durchsuchenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_import);

        importButton = (Button) findViewById(R.id.importButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        durchsuchenButton = (Button) findViewById(R.id.durchsuchenButton);

        importButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Import Button pressed", Toast.LENGTH_SHORT);
                toast.show();   //TODO: Import der JSON-Datei hier implementieren
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Cancel Button pressed", Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        });
        durchsuchenButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Durchsuchen Button pressed", Toast.LENGTH_SHORT);
                toast.show();   //TODO: Durchsuchen-Funktionen hier implementieren
            }
        });
    }

        public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location_import, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}