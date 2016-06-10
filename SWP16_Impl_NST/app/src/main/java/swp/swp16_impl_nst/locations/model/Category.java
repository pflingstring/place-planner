package swp.swp16_impl_nst.locations.model;

public class Category
{
    private int iconId;
    private String name;
    private String description;

    public Category(int icon, String name, String description)
    {
        this.iconId = icon;
        this.name = name;
        this.description = description;
    }

    // constructor
    private Category(Builder builder)
    { edit(builder); }

    public void edit(Builder builder)
    {
        name    = builder.name;
        iconId   = builder.icon;
        description = builder.description;
    }


    // Category Builder
    public static class Builder
    {
        // required parameters
        private String name;
        private String description;
        private int icon;



        // constructor
        public Builder (String name)
        { this.name = name;}


        public Builder icon(int icon)
        { this.icon = icon; return this;}

        public Builder description(String description)
        { this.description = description; return this;}


        public Category build()
        {
            if (name == null)
                throw new IllegalArgumentException("Address and category may not be null");

            return new Category(this);
        }
    }


    @Override
    public String toString()
    {
        String result = "";
        result += this.name + this.description;
        return result;
    }



    //Getter
    public String getName()
        { return  this.name; }

    public int getIconId()
    { return iconId; }

    public String getDescription()
    {   return this.description;   }
}
