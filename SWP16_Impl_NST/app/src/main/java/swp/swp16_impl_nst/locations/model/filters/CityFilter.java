package swp.swp16_impl_nst.locations.model.filters;

import swp.swp16_impl_nst.locations.model.Address;
import swp.swp16_impl_nst.locations.model.Location;

public class CityFilter
//        implements Predicate
{
//    @Override
    public boolean invoke(Location location, String city)
    {
        Address address = location.getAddress();
        return  address != null && address.getCity().equals(city);
    }
}
