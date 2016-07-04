package swp.swp16_impl_nst.friends;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import swp.swp16_impl_nst.friends.model.Friend;

/**
 * Created by Simon on 23.06.2016.
 */
public class FriendProvider {

    public static final List<Friend> friends = new ArrayList<>();

    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final static Type type = (new TypeToken<List<Friend>>(){}).getType();

    public static void add(Friend friend)
    {
        friends.add(friend);
    }
}
