package swp.swp16_impl_nst.locations.model.filters;

import swp.swp16_impl_nst.locations.model.Location;
import swp.swp16_impl_nst.locations.model.User;

public class StringFilter
{
    public boolean invoke(Location location, String str)
    {
        if (location != null)
        {
            User owner = location.getOwner();
            boolean inOwner = owner != null && owner.getName() != null && owner.getName().contains(str);
            boolean inName  = location.getName() != null && location.getName().contains(str);
            boolean inComment  = location.getComment()  != null && location.getComment().contains(str);
            boolean inMediaUrl = location.getMediaUrl() != null && location.getMediaUrl().contains(str);
            return inOwner || inName || inComment || inMediaUrl;
        }
        return false;
    }
}
