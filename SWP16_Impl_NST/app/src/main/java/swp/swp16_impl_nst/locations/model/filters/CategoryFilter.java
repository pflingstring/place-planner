package swp.swp16_impl_nst.locations.model.filters;

import java.util.List;

import swp.swp16_impl_nst.locations.model.Location;

public class CategoryFilter implements Predicate
{
    private List<Location> locations;

    @Override
    public boolean invoke(Location location)
    {
        return false;
    }
}
