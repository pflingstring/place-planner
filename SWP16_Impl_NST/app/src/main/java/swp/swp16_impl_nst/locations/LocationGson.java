package swp.swp16_impl_nst.locations;

import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import swp.swp16_impl_nst.locations.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.List;

public class LocationGson
{
    private final static File PATH_DIR = getDataSetPath(".place-planner");

    public static void writeToFile(String data, String fileName)
    {
        try
        {
            File dir = getDataSetPath(".place_planner_OK");
            dir.mkdirs();
            File file = new File(dir, fileName);


            Log.i("@@@FILE@@@", file.getPath() + "<>||<> " + file.getAbsolutePath());
            OutputStream stream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(stream);
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e)
        {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static String readFromFile(String fileName)
    {
        String result = "";        Log.i("@@@FILE@@@", )

        try
        {
            File dir = getDataSetPath(".place_planner_OK");
            dir.mkdirs();
            File file = new File(dir, fileName);

            InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ((receiveString = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(receiveString);
            }

            inputStream.close();
            result = stringBuilder.toString();
        }
        catch (FileNotFoundException e)
        {
            Log.e("login activity", "File not found: " + e.toString());
        }
        catch (IOException e)
        {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        Log.i("!FILE_gson_readFF", result);
        return result;
    }

    public static File getDataSetPath(String path)
    {
        File file =  new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS), path);

        if (!file.mkdirs())
            Log.e("!FILE_ERROR", "Dir norrrr created" + file.getAbsolutePath());
        else
            Log.i("!FILE", "Dir maybe? created" + file.getName());


        return file;
    }

    static
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Location loc_orig_0 = new Location.Builder
                ("Mr King")
                .address(new Address("Rudolphsplatz", "33", "35037", "Marburg", "Germany"))
                .category(new Category(2, "FastFood"))
                .comment("JawohlJawohl")
                .build();

        Location loc_orig_1 = new Location.Builder
                ("Schloss")
                .address(new Address("Gisonenweg", "1", "35037", "Marburg", "Germany"))
                .category(new Category(1, "default_cat"))
                .comment("First Location to Json")
                .build();

        LocationProvider.locations.add(loc_orig_0);
        LocationProvider.locations.add(loc_orig_1);

        String json = gson.toJson(LocationProvider.locations);

        Type type = (new TypeToken<List<Location>>(){}).getType();
        List<Location> parsedLocations = gson.fromJson(json, type);

        String allLocations = "";
        for (Location location : LocationProvider.locations)
            allLocations += location.toString() + "\n";
    }
}
