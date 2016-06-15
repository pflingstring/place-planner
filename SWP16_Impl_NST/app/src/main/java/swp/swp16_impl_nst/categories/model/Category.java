package swp.swp16_impl_nst.categories.model;

public class Category
{
    private int iconId;
    private String name;
    private String description;

    public Category(int icon, String name)
    {
        this.iconId = icon;
        this.name = name;
    }

    public Category(int icon, String name, String description)
    {
        this.iconId = icon;
        this.name = name;
        this.description = description;
    }

    //Getter
    public String getName()
    { return  this.name; }

    public int getIconId()
    { return iconId; }

    public String getDescription()
    { return this.description; }
}
