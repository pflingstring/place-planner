package swp.swp16_impl_nst.locations.model.filters;

import swp.swp16_impl_nst.locations.model.Address;
import swp.swp16_impl_nst.locations.model.Location;

public class CountryFilter
//        implements Predicate
{
//    @Override
    public boolean invoke(Location location, String country)
    {
        Address address = location.getAddress();
        return  address != null && address.getCountry().equals(country);
    }
}
