package swp.swp16_impl_nst.locations.model.fields;

public class Address
{
    private String city;
    private String number;
    private String street;
    private String country;
    private String postal_code;

    public Address() {}

    public Address(String _street, String _number, String _postal_code,
                   String _city,   String _country)
    {
        city = _city;
        street = _street;
        number = _number;
        country = _country;
        postal_code = _postal_code;
    }


    // getters
    public String getPostal_code()
    { return postal_code; }

    public String getStreet()
    { return street; }

    public String getCountry()
    { return country; }

    public String getNumber()
    { return number; }

    public String getCity()
    { return city; }
}
