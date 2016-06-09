package swp.swp16_impl_nst.locations.model;

public class Category
{
    private int iconId;
    private String name;

    public Category(int icon, String name)
    {
        this.iconId = icon;
        this.name = name;
    }

    public String getName()
        { return  this.name; }

    public int getIconId()
    { return iconId; }
}
