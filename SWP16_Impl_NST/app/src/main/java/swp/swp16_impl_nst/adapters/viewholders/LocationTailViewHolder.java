package swp.swp16_impl_nst.adapters.viewholders;

import android.view.View;
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
    View warning;

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
        warning = view.findViewById(R.id.warning);
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
        }
        else
        {
            streetView.setVisibility(View.GONE);
            streerNrView.setVisibility(View.GONE);
            postalCodeView.setVisibility(View.GONE);
            cityView.setVisibility(View.GONE);
            country.setVisibility(View.GONE);
        }
    }

    public void setAllInvisible()
    {
        commentView.setVisibility(View.GONE);
        homepageView.setVisibility(View.GONE);
        phonenumberView.setVisibility(View.GONE);
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
}
