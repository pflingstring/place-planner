package swp.swp16_impl_nst.locations;

import android.os.Environment;
import android.util.Log;

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
import java.util.ArrayList;
import java.util.List;

public class LocationStorage
{
    // a File object pointing to the /sdcard0/Podcasts/.place-planner
    private final static File PATH_DIR = createDir(".place-planner");

    public static File createDir(String path)
    {
        File file =  new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS), path);
        file.mkdirs();

        return file;
    }

    public static void writeToFile(String data, String fileName)
    {
        try
        {
            File file = new File(PATH_DIR, fileName);
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
        String result = "";

        try
        {
            File file = new File(PATH_DIR, fileName);
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

        return result;
    }

    public static List<String> getSavedLocations()
    {
        List<String> result = new ArrayList<>();
        File folder = new File(PATH_DIR.getPath());
        File[] files = folder.listFiles();

        for (File file : files)
            if (file.isFile())
                result.add(file.getName());

        return result;
    }

}
