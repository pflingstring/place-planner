package swp.swp16_impl_nst.locations.model;

public class Address
{
    private String city;
    private String number;
    private String street;
    private String country;

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    private String zip;

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

    @Override
    public String toString()
    {
        String result = "";
        if (zip  != null) result += zip + " ";
        if (city != null) result += city + " ";
        if (street  != null) result += street + " ";
        if (number  != null) result += number + " ";
        if (country != null) result += country + " ";

        return result;
    }

    public boolean isEmpty()
    {
        if (zip == null || city == null || street == null || number == null || country == null)
            return false;

            return zip.isEmpty()    && city.isEmpty() && street.isEmpty() &&
                   number.isEmpty() && country.isEmpty();
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
