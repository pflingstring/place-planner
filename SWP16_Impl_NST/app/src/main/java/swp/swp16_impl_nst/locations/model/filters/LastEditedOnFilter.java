package swp.swp16_impl_nst.locations.model.filters;

import swp.swp16_impl_nst.locations.model.Location;

public class LastEditedOnFilter
{
    public boolean invoke(Location location, long from, long until)
    {
        long lastEditedOn = location.getLastModifiedTimestamp();
        return from < lastEditedOn && until > lastEditedOn;
    }
}
