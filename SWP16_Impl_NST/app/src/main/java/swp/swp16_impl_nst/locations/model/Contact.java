package swp.swp16_impl_nst.locations.model;

public class Contact
{
    private String web;
    private String email;
    private String telephone;

    public Contact(String _webAddress, String _email, String _phoneNumber)
    {
        web = _webAddress;
        email = _email;
        telephone = _phoneNumber;
    }

    public String getTelephone()
    { return telephone; }

    public String getEmail()
    { return email; }

    public String getWeb()
    { return web; }

    public boolean isEmpty()
    {
        return web.isEmpty() && email.isEmpty() && telephone.isEmpty();
    }
}
