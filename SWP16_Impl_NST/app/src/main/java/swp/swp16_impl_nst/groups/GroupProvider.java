package swp.swp16_impl_nst.groups;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import swp.swp16_impl_nst.groups.model.Group;

/**
 * Created by Simon on 04.07.2016.
 */
public class GroupProvider {

    public static final List<Group> groups = new ArrayList<>();

    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final static Type type = (new TypeToken<List<Group>>(){}).getType();

    public static void add(Group group)
    {
        groups.add(group);
    }
}
