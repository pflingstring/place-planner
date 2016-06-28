package swp.swp16_impl_nst.friends.model;

public class Friend {
    private String name;
    private String street;
    private String streetNumber;
    private String zip;
    private String city;
    private String country;
    private String telephone;
    private String email;

    public Friend (String name, String street, String streetNumber, String zip, String city, String country, String telephone, String email){
        this.name=name;
        this.street=street;
        this.streetNumber=streetNumber;
        this.zip=zip;
        this.city=city;
        this.country=country;
        this.telephone=telephone;
        this.email=email;
    }

    //Getter
    public String getName()
    { return  this.name; }

    public String getStreet()
    { return  street; }

    public String getStreetNumber()
    { return  streetNumber; }

    public String getZip()
    { return  zip; }

    public String getCity()
    { return  city; }

    public String getCountry()
    { return  country; }

    public String getTelephone()
    { return telephone; }

    public String getEmail()
    { return  email; }

}
