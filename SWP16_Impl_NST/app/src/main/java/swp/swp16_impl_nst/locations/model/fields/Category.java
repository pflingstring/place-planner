package swp.swp16_impl_nst.locations.model.fields;

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
