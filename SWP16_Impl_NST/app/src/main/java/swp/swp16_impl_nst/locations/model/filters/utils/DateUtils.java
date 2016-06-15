package swp.swp16_impl_nst.locations.model.filters.utils;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtils
{
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    public static OnDateSetListener getOnDateSetListener (
            final EditText editText
            , final Calendar calendar)
    {
        return new OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                calendar.set(year, monthOfYear, dayOfMonth);
                editText.setText(dateFormat.format(calendar.getTime()));
        }};
    }

    public static View.OnClickListener showDatePickerDialog(
            final Context context
            , final EditText editText
            , final Calendar calendar)
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                OnDateSetListener onDateSetListener = DateUtils.getOnDateSetListener(editText, calendar);

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(context, onDateSetListener, year, month, day);
                datePicker.show();
        }};
    }
}
