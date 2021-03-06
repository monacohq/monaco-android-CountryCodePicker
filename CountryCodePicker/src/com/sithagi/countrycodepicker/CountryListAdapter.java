package com.sithagi.countrycodepicker;

import java.lang.reflect.Field;
import java.text.Normalizer;
import java.util.List;
import java.util.Locale;

import com.sithagi.countrycodepicker.R.drawable;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CountryListAdapter extends BaseAdapter {

    private Context context;
    List<Country> countries;
    LayoutInflater inflater;
    CountryPicker.Mode mode;

    private int getResId(String drawableName) {

        try {
//            String packageName = context.getPackageName();
//            return context.getResources().getIdentifier(drawableName, "drawable", packageName);
            Class<drawable> res = R.drawable.class;
            Field field = res.getField(drawableName);
            int drawableId = field.getInt(null);
            return drawableId;
        } catch (Exception e) {
            Log.e("CountryCodePicker", "Failure to get drawable id.", e);
        }
        return -1;
    }

    public CountryListAdapter(Context context, List<Country> countries, CountryPicker.Mode mode) {
        super();
        this.context = context;
        this.countries = countries;
        this.mode = mode;
        inflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View cellView = convertView;
        Cell cell;
        Country country = countries.get(position);

        if (mode == CountryPicker.Mode.Currency) {
            if (convertView == null) {
                cell = new Cell();
                cellView = inflater.inflate(R.layout.currency_row, null);
                cell.textView = (TextView) cellView.findViewById(R.id.row_title);
                cell.imageView = (ImageView) cellView.findViewById(R.id.row_icon);
                cell.currencyView = (TextView) cellView.findViewById(R.id.row_currency);
                cellView.setTag(cell);
            } else {
                cell = (Cell) cellView.getTag();
            }

            cell.textView.setText(country.getName());

            String drawableName = country.getCode().toLowerCase();
            if (drawableName == "do") drawableName = "dmm";
            cell.imageView.setImageResource(getResId(drawableName));
            cell.currencyView.setText(country.getCurrency());
        } else if (mode == CountryPicker.Mode.Tel) {
            if (convertView == null) {
                cell = new Cell();
                cellView = inflater.inflate(R.layout.row, null);
                cell.textView = (TextView) cellView.findViewById(R.id.row_title);
//                cell.imageView = (ImageView) cellView.findViewById(R.id.row_icon);
                cell.extView = (TextView) cellView.findViewById(R.id.row_ext);
                cellView.setTag(cell);
            } else {
                cell = (Cell) cellView.getTag();
            }

            cell.textView.setText(country.getName());
            cell.extView.setText(country.getDialCode());

//            String drawableName = country.getCode().toLowerCase();
//            if (drawableName.equals("do")) {
//                drawableName = "dmm";
//            }
//            cell.imageView.setImageResource(getResId(drawableName));
        }
        return cellView;
    }

    static class Cell {
        public TextView textView;
        public ImageView imageView;
        public TextView currencyView;
        public TextView extView;

    }

}