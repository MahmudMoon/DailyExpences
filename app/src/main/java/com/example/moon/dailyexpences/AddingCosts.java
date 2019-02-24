package com.example.moon.dailyexpences;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.daimajia.easing.linear.Linear;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddingCosts extends AppCompatActivity {


    LinearLayout layout;
    ImageButton road_cost_,cigarate,foods_,mobile_bill_,other_cost_,Unknown;
    ImageButton add_,cancel;
    EditText amount;
    public static SQLHelper sqlHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_costs);
        //layout = (LinearLayout)findViewById(R.id.linear);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        init_views();
        init_variables();
        init_listenters();


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddingCosts.this,Main2Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    private void init_variables() {
        sqlHelper = new SQLHelper(getApplicationContext());
    }

    private void init_listenters() {
        road_cost_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindowView(v);
            }
        });

        cigarate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindowView(v);
            }
        });

        foods_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindowView(v);
            }
        });

        mobile_bill_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindowView(v);
            }
        });

        other_cost_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindowView(v);
            }
        });

        Unknown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"We are working on it....",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init_views() {
        road_cost_ = (ImageButton)findViewById(R.id.road_cost);
        cigarate = (ImageButton)findViewById(R.id.cigarate_cost);
        foods_ = (ImageButton)findViewById(R.id.food_cost);
        mobile_bill_ = (ImageButton)findViewById(R.id.mobile_bill_cost);
        other_cost_ = (ImageButton)findViewById(R.id.other_cost);
        Unknown = (ImageButton)findViewById(R.id.unknown_cost);

    }

    public void popUpWindowView(final View ImageButtonId){



        LayoutInflater popUpView = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUp = popUpView.inflate(R.layout.pop_up_window,null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;



        add_ = (ImageButton)popUp.findViewById(R.id.add_cost);
        cancel = (ImageButton)popUp.findViewById(R.id.cancle_window);
        amount = (EditText)popUp.findViewById(R.id.inset_expence);

        switch (ImageButtonId.getId()){
            case R.id.road_cost:
                amount.setHint("Add Road Cost");
                break;

            case R.id.cigarate_cost:
                amount.setHint("Add cigarate Cost");
                break;

            case R.id.food_cost:
                amount.setHint("Add food Cost");
                break;

            case R.id.mobile_bill_cost:
                amount.setHint("Add Mobile bill");
                break;

            case R.id.other_cost:
               amount.setHint("Add Other cost");
                break;

            default:

                break;
        }

        final PopupWindow popupWindow = new PopupWindow(popUp,width,height,true);
        popupWindow.showAtLocation(popUp, Gravity.CENTER,0,0);




        add_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount_taka = Integer.parseInt(amount.getText().toString().trim());
                int WhichButton =  ImageButtonId.getId();

                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = sdf.format(c.getTime());
               // Toast.makeText(getApplicationContext(),strDate,Toast.LENGTH_SHORT).show();

                String Year_ = strDate.substring(0,4);
                String Month_ = strDate.substring(5,7);
                String Day_ = strDate.substring(8,10);

                switch (WhichButton){
                    case R.id.road_cost:
                        boolean ins_res = sqlHelper.insertValues(Year_,Month_,Day_,"road",amount_taka);
                        Toast.makeText(getApplicationContext(),Integer.toString(amount_taka)+" Has Been Added to " + "Road Cost" + Boolean.toString(ins_res),Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.cigarate_cost:
                        boolean ins_res_cigarate  = sqlHelper.insertValues(Year_,Month_,Day_,"cigarate",amount_taka);
                        Toast.makeText(getApplicationContext(),Integer.toString(amount_taka)+" Has Been Added to " + "cigarate Cost"+ Boolean.toString(ins_res_cigarate),Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.food_cost:
                        boolean ins_res_food  = sqlHelper.insertValues(Year_,Month_,Day_,"food",amount_taka);
                        Toast.makeText(getApplicationContext(),Integer.toString(amount_taka)+" Has Been Added to " + "food Cost"+ Boolean.toString(ins_res_food),Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.mobile_bill_cost:
                        boolean ins_res_mobile  = sqlHelper.insertValues(Year_,Month_,Day_,"mobile",amount_taka);
                        Toast.makeText(getApplicationContext(),Integer.toString(amount_taka)+" Has Been Added to " + "mobile Cost"+ Boolean.toString(ins_res_mobile),Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.other_cost:
                        boolean ins_res_other  = sqlHelper.insertValues(Year_,Month_,Day_,"other",amount_taka);
                        Toast.makeText(getApplicationContext(),Integer.toString(amount_taka)+" Has Been Added to " + "other Cost"+ Boolean.toString(ins_res_other),Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        Toast.makeText(getApplicationContext(),Integer.toString(amount_taka)+" Has Been Added to " + "Unknown Cost",Toast.LENGTH_SHORT).show();
                        break;
                }

                popupWindow.dismiss();

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });



    }
}
