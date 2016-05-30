package swp.swp16_impl_nst.locations.model.filters;

import swp.swp16_impl_nst.locations.model.Location;

public interface Predicate
{
    boolean invoke(Location location);
}
