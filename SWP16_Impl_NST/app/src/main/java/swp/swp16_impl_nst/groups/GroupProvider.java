package swp.swp16_impl_nst.groups;

import java.util.ArrayList;
import java.util.List;

import swp.swp16_impl_nst.groups.model.Group;

/**
 * Created by Simon on 04.07.2016.
 */
public class GroupProvider {

    public static final List<Group> groups = new ArrayList<>();

    public static void add(Group group)
    {
        groups.add(group);
    }
}
