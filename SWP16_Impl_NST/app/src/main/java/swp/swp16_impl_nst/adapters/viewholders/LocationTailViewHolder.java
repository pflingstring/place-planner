package swp.swp16_impl_nst.adapters.viewholders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import swp.swp16_impl_nst.R;

public class LocationTailViewHolder extends AbstractExpandableItemViewHolder
{
    TextView commentView;
    TextView streetView;
    TextView streerNrView;
    TextView postalCodeView;
    TextView cityView;
    TextView country;
    TextView categoryView;
    TextView homepageView;
    TextView phonenumberView;
    TextView emailView;
    View warning;

    ImageView homeIcon;
    ImageView contactsIcon;
    ImageButton editBtn;
    ImageButton deleteBtn;
    ImageButton gpsMapBtn;

    public LocationTailViewHolder(View view)
    {
        super(view);
        cityView = (TextView) view.findViewById(R.id.address_city);
        country = (TextView) view.findViewById(R.id.country);
        streetView = (TextView) view.findViewById(R.id.address_street);
        streerNrView = (TextView) view.findViewById(R.id.street_number);
        postalCodeView = (TextView) view.findViewById(R.id.address_postal_code);
        commentView = (TextView) view.findViewById(R.id.comment);
        categoryView = (TextView) view.findViewById(R.id.category_main_name);
        homepageView = (TextView) view.findViewById(R.id.homepage);
        phonenumberView = (TextView) view.findViewById(R.id.phone_number);
        homeIcon = (ImageView) view.findViewById(R.id.icon_home);
        warning = view.findViewById(R.id.warning);
        emailView = (TextView) view.findViewById(R.id.email);
        contactsIcon = (ImageView) view.findViewById(R.id.icon_contacts);
        editBtn = (ImageButton) view.findViewById(R.id.edit_btn);
        deleteBtn = (ImageButton) view.findViewById(R.id.delete_btn);
        gpsMapBtn = (ImageButton) view.findViewById(R.id.gpsMap_btn);
    }

    // setters
    public void warningMakeVisible(boolean flag)
    {
        if (flag)
            warning.setVisibility(View.VISIBLE);
        else
            warning.setVisibility(View.GONE);
    }

    public void addressMakeVisible(boolean flag)
    {
        if (flag)
        {
            streetView.setVisibility(View.VISIBLE);
            streerNrView.setVisibility(View.VISIBLE);
            postalCodeView.setVisibility(View.VISIBLE);
            cityView.setVisibility(View.VISIBLE);
            country.setVisibility(View.VISIBLE);
            homeIcon.setVisibility(View.VISIBLE);
        }
        else
        {
            streetView.setVisibility(View.GONE);
            streerNrView.setVisibility(View.GONE);
            postalCodeView.setVisibility(View.GONE);
            cityView.setVisibility(View.GONE);
            country.setVisibility(View.GONE);
            homeIcon.setVisibility(View.GONE);
        }
    }

    public void contactMakeVisible(boolean flag)
    {
        if (flag)
        {
            phonenumberView.setVisibility(View.VISIBLE);
            emailView.setVisibility(View.VISIBLE);
            homepageView.setVisibility(View.VISIBLE);
        }
        else
        {
            phonenumberView.setVisibility(View.GONE);
            emailView.setVisibility(View.GONE);
            homepageView.setVisibility(View.GONE);
        }
    }

    public void setCommentView(String commentView)
    {
        this.commentView.setText(commentView);
    }

    public void setStreetView(String streetView)
    {
        this.streetView.setText(streetView);
    }

    public void setStreerNrView(String streerNrView)
    {
        this.streerNrView.setText(streerNrView);
    }

    public void setPostalCodeView(String postalCodeView)
    {
        this.postalCodeView.setText(postalCodeView);
    }

    public void setCityView(String cityView)
    {
        this.cityView.setText(cityView);
    }

    public void setCountry(String country)
    {
        this.country.setText(country);
    }

    public void setCategoryView(String categoryView)
    {
        this.categoryView.setText(categoryView);
    }

    public void setHomepageView(String homepageView)
    {
        this.homepageView.setText(homepageView);
    }

    public void setPhonenumberView(String phonenumberView)
    {
        this.phonenumberView.setText(phonenumberView);
    }

    public void setEmailView(String email)
    {
        this.emailView.setText(email);
    }


    // getters
    public ImageButton getEditBtn()
    {
        return editBtn;
    }

    public ImageView getContactsIcon()
    {
        return contactsIcon;
    }


    public View getWarning()
    {
        return warning;
    }

    public ImageView getHomeIcon()
    {
        return homeIcon;
    }

    public TextView getEmailView()
    {
        return emailView;
    }

    public TextView getPhonenumberView()
    {
        return phonenumberView;
    }

    public TextView getHomepageView()
    {
        return homepageView;
    }

    public TextView getCategoryView()
    {
        return categoryView;
    }

    public TextView getCountry()
    {
        return country;
    }

    public TextView getCityView()
    {
        return cityView;
    }

    public TextView getPostalCodeView()
    {
        return postalCodeView;
    }

    public TextView getStreerNrView()
    {
        return streerNrView;
    }

    public TextView getStreetView()
    {
        return streetView;
    }

    public TextView getCommentView()
    {
        return commentView;
    }

    public ImageButton getDeleteBtn()
    {
        return deleteBtn;
    }

    public ImageButton getGpsMapBtn()
    {
        return gpsMapBtn;
    }
}
