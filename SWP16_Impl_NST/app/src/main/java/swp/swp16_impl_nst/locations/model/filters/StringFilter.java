package swp.swp16_impl_nst.locations.model.filters;

import swp.swp16_impl_nst.locations.model.Location;

public class StringFilter
//        implements Predicate
{
//    @Override
    public boolean invoke(Location location, String str)
    {
        return location.containsString(str);
    }
}
