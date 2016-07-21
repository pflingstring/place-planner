package swp.swp16_impl_nst.friends;

import java.util.ArrayList;
import java.util.List;

import swp.swp16_impl_nst.friends.model.Friend;

/**
 * Created by Simon on 23.06.2016.
 */
public class FriendProvider {

    public static final List<Friend> friends = new ArrayList<>();

    public static void add(Friend friend)
    {
        friends.add(friend);
    }
}
