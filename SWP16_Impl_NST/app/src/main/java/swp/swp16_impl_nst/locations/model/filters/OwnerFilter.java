package swp.swp16_impl_nst.locations.model.filters;


import swp.swp16_impl_nst.locations.model.Location;
import swp.swp16_impl_nst.locations.model.User;

public class OwnerFilter
//        implements Predicate
{
//    @Override
    public boolean invoke(Location location, String owener)
    {
        User user = location.getOwner();
        return user != null && user.getName() != null && user.getName().equals(owener);
    }
}
