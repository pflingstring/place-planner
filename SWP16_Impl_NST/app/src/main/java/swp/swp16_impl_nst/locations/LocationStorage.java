package swp.swp16_impl_nst.locations;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class LocationStorage
{
    private final static String DIR_NAME = Environment.DIRECTORY_PODCASTS;
    private final static File   PATH_DIR = createDir(".place-planner");

    public static File createDir(String name)
    {
        File folder = Environment.getExternalStoragePublicDirectory(DIR_NAME);
        File file =  new File(folder, name);
        file.mkdirs();

        return file;
    }

    public static void writeToFile(String data, String fileName)
    {
        try
        {
            File file = new File(PATH_DIR, fileName);
            OutputStream stream = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(data);
            writer.close();
        }
        catch (IOException e)
        {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static String readFromFile(String fileName)
    {
        String result = "";
        try
        {
            File file = new File(PATH_DIR, fileName);
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String receiveString;
            StringBuilder stringBuilder = new StringBuilder();
            while ((receiveString = reader.readLine()) != null)
                { stringBuilder.append(receiveString); }
            inputStream.close();

            result = stringBuilder.toString();
        }
        catch (IOException e)
            { e.printStackTrace(); }

        return result;
    }

    public static List<String> getSavedLocations()
    {
        List<String> result = new ArrayList<>();
        File folder = new File(PATH_DIR.getPath());
        File[] files = folder.listFiles();

        if (files != null)
        {
            for (File file : files)
                if (file.isFile())
                    result.add(file.getName());
        }

        return result;
    }
}
