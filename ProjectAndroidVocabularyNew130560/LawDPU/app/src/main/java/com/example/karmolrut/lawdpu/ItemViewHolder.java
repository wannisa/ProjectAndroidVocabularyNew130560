package com.example.karmolrut.lawdpu;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import static com.example.karmolrut.lawdpu.R.id.student_name_TextView;


public class ItemViewHolder extends RecyclerView.ViewHolder {
    public TextView student_name_TextView;
    public TextView activity_name_TextView;
    public TextView idtext;
    public ImageButton imageButton;
    public ImageButton starOn, starOOF;

    public ItemViewHolder(View itemView) {
        super(itemView);
        itemView.setClickable(true);
        student_name_TextView = (TextView) itemView.findViewById(R.id.student_name_TextView);
        activity_name_TextView = (TextView) itemView.findViewById(R.id.activity_name_TextView);
        starOOF = (ImageButton) itemView.findViewById(R.id.staroof);
        starOn = (ImageButton) itemView.findViewById(R.id.staron);

    }

    public void bind(CountryModel countryModel) {
        student_name_TextView.setText(countryModel.getThai());
        activity_name_TextView.setText(countryModel.getEnglish());
        //idtext.setText(countryModel.getID());

        if(countryModel.getFavo().equals("true"))
        {
            starOOF.setVisibility(View.INVISIBLE);
            starOn.setVisibility(View.VISIBLE);
        }
        else
        {

            starOOF.setVisibility(View.VISIBLE);
            starOn.setVisibility(View.INVISIBLE);
        }
    }


}
