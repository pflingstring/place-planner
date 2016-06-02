package swp.swp16_impl_nst.locations.model;

public class Address
{
    private String city;
    private String number;
    private String street;
    private String country;
    private String zip;

    public Address() {}

    public Address(String _street, String _number, String _postal_code,
                   String _city,   String _country)
    {
        city = _city;
        street = _street;
        number = _number;
        country = _country;
        zip = _postal_code;
    }

    public boolean containsString(String str)
    {
        return  city.contains(str)
                || number.contains(str)
                || str.contains(str)
                || country.contains(str)
                || zip.contains(str);
    }


    // getters
    public String getZip()
    { return zip; }

    public String getStreet()
    { return street; }

    public String getCountry()
    { return country; }

    public String getNumber()
    { return number; }

    public String getCity()
    { return city; }
}
