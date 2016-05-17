package swp.swp16_impl_nst.models.locations.fields;

public class Category
{
    private int icon;
    private String name;

    public Category(int icon, String name)
    {
        this.icon = icon;
        this.name = name;
    }

    public String getName()
    { return  this.name; }
}
