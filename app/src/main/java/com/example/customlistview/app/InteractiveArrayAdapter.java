package com.example.customlistview.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by byan on 8/30/2014.
 */
public class InteractiveArrayAdapter extends ArrayAdapter<Model> implements NumberPicker.OnValueChangeListener{

    private List<Model> list;
    private Activity context;
    private String qtyStr = "";

    public InteractiveArrayAdapter(Activity context, List<Model> list) {
        super(context, R.layout.list_source, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i2) {
        Log.i("value is : ", "" + i2);
    }

    static class ViewHolder{
        protected TextView text;
        protected CheckBox checkBox;
        protected TextView qty;
        protected Dialog dialog;
    }

    @Override
    public View getView(final int position , final View convertView, final ViewGroup parent){
        View view = null;
        if( convertView == null ){
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.list_source , null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.text);
            viewHolder.qty = (TextView) view.findViewById(R.id.qty);
            viewHolder.checkBox = (CheckBox) view.findViewById(R.id.checkRes);
            viewHolder.checkBox.setTag(viewHolder);
            final View finalView = view;

            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewHolder holder = (ViewHolder) finalView.getTag();
                    Model element = (Model) holder.checkBox.getTag();
                    if(element.getSelected()) {
                        show(holder.qty);
                    }else{
                        holder.qty.setText("-");
                    }
                }
            });

            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    ViewHolder holder = (ViewHolder) finalView.getTag();
                    Model element = (Model) holder.checkBox.getTag();
                    element.setCheckBox(compoundButton.isChecked());
                }
            });

            view.setTag(viewHolder);
            viewHolder.checkBox.setTag(list.get(position));
        }else{
            view = convertView;
            ((ViewHolder) view.getTag()).checkBox.setTag(list.get(position));
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText(list.get(position).getName());
        holder.checkBox.setChecked(list.get(position).getSelected());
        holder.qty.setText(list.get(position).getQty());
        return view;
    }

    public void show(TextView text){

        final TextView finalText = text;
        final Dialog d = new Dialog(context);
        d.setTitle("Number Picker");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button)d.findViewById(R.id.button1);
        Button b2 = (Button)d.findViewById(R.id.button2);
        final NumberPicker numpick = (NumberPicker)d.findViewById(R.id.numberPicker1);
        numpick.setMaxValue(100);
        numpick.setMinValue(1);
        numpick.setWrapSelectorWheel(false);
        numpick.setOnValueChangedListener(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Menu : ", "" + numpick.getValue());
                finalText.setText(String.valueOf(numpick.getValue()));

                d.dismiss();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
            }
        });

        d.show();
    }
}
