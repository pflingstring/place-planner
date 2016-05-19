package swp.swp16_impl_nst.locations.model.fields;

public class Contact
{
    private String webAddress;
    private String email;
    private String phoneNumber;

    public Contact(String _webAddress, String _email, String _phoneNumber)
    {
        webAddress = _webAddress;
        email = _email;
        phoneNumber = _phoneNumber;
    }

    public String getPhoneNumber()
    { return phoneNumber; }

    public String getEmail()
    { return email; }

    public String getWebAddress()
    { return webAddress; }
}
