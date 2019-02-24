package com.example.moon.dailyexpences;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {


    PieChart pieChart;
    String[] names = {"Road","Food","","Kamal","Helal"};
    int[] values = {10,20,30,40,50};
    Button btn_day_,btn_month_,btn_week_,btn_add_cost_;
    SQLHelper sqlHelper;
    //link is https://github.com/PhilJay/MPAndroidChart
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        sqlHelper = new SQLHelper(getApplicationContext());

        btn_add_cost_= (Button)findViewById(R.id.btn_add_cost);
        btn_day_ = (Button)findViewById(R.id.btn_day);
        btn_month_ = (Button)findViewById(R.id.btn_month);
        btn_week_ = (Button)findViewById(R.id.btn_week);


        addListeners();

        pieChart = (PieChart)findViewById(R.id.pieChart);

        // pieChart.setDescription('a');
        pieChart.setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Daily Expences");
        pieChart.setCenterTextSize(14);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!


        btn_day_expence();

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        finishAffinity();
    }



    private void addListeners(){

        btn_day_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_day_expence();
            }
        });


        btn_month_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = sdf.format(c.getTime());


                String Year_ = strDate.substring(0,4);
                String Month_ = strDate.substring(5,7);




                Cursor cursor = sqlHelper.readValue(Year_,Month_,"road");
                int total_road =  getTotal(cursor);
                Log.d("ROAD",Integer.toString(total_road));
                values[0] = total_road;



                Cursor cursor1 = sqlHelper.readValue(Year_,Month_,"food");
                int total_food =  getTotal(cursor1);
                Log.d("FOOD",Integer.toString(total_food));
                values[1]  = total_food;


                Cursor cursor2 = sqlHelper.readValue(Year_,Month_,"cigarate");
                int total_cigarate =  getTotal(cursor2);
                Log.d("CIGARATE",Integer.toString(total_cigarate));
                values[2] = total_cigarate;


                Cursor cursor3 = sqlHelper.readValue(Year_,Month_,"mobile");
                int total_mobile =  getTotal(cursor3);
                Log.d("MOBILE",Integer.toString(total_mobile));
                values[3] = total_mobile;


                Cursor cursor4 = sqlHelper.readValue(Year_,Month_,"other");
                int total_other =  getTotal(cursor4);
                Log.d("OTHER",Integer.toString(total_other));
                values[4] = total_other;

                setData();

            }
        });


        btn_week_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    int day = calendar.get(Calendar.DAY_OF_WEEK);

                    Log.d("DAY",Integer.toString(day));
                    switch (day){
                        case Calendar.SATURDAY:
                            calculateForWeek(0);
                            break;
                        case Calendar.SUNDAY:
                            calculateForWeek(1);
                            break;
                        case Calendar.MONDAY:
                            calculateForWeek(2);
                            break;
                        case Calendar.TUESDAY:
                            calculateForWeek(3);
                            break;
                        case Calendar.WEDNESDAY:
                            calculateForWeek(4);
                            break;
                        case Calendar.THURSDAY:
                            calculateForWeek(5);
                            break;
                        case Calendar.FRIDAY:
                            calculateForWeek(6);
                            break;
                    }

            }
        });


        btn_add_cost_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,AddingCosts.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });


    }

    private void btn_day_expence() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(c.getTime());


        String Year_ = strDate.substring(0,4);
        String Month_ = strDate.substring(5,7);
        String Day_ = strDate.substring(8,10);



        Cursor cursor = sqlHelper.readValue(Year_,Month_,Day_,"road");
        int total_road =  getTotal(cursor);
        Log.d("ROAD",Integer.toString(total_road));
        values[0] = total_road;



        Cursor cursor1 = sqlHelper.readValue(Year_,Month_,Day_,"food");
        int total_food =  getTotal(cursor1);
        Log.d("FOOD",Integer.toString(total_food));
        values[1]  = total_food;


        Cursor cursor2 = sqlHelper.readValue(Year_,Month_,Day_,"cigarate");
        int total_cigarate =  getTotal(cursor2);
        Log.d("CIGARATE",Integer.toString(total_cigarate));
        values[2] = total_cigarate;


        Cursor cursor3 = sqlHelper.readValue(Year_,Month_,Day_,"mobile");
        int total_mobile =  getTotal(cursor3);
        Log.d("MOBILE",Integer.toString(total_mobile));
        values[3] = total_mobile;


        Cursor cursor4 = sqlHelper.readValue(Year_,Month_,Day_,"other");
        int total_other =  getTotal(cursor4);
        Log.d("OTHER",Integer.toString(total_other));
        values[4] = total_other;

        setData();
    }

    private void calculateForWeek(int i) {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(c.getTime());


        String Year_ = strDate.substring(0,4);
        String Month_ = strDate.substring(5,7);
        String Day_ = strDate.substring(8,10);
        int current_day = Integer.parseInt(Day_);

        int total_road_week = 0;
        int total_food_week = 0;
        int total_cigarate_week = 0;
        int total_mobile_week = 0;
        int total_other_week = 0;

        for(int j=0;j<=i;j++){

            String Day_c = Integer.toString(current_day);

            if(current_day<10) {
              Day_c = "0"+Day_c;
            }


            Cursor cursor = sqlHelper.readValue(Year_,Month_,Day_c,"road");
            int total_road =  getTotal(cursor);
            Log.d("ROAD",Integer.toString(total_road));
            total_road_week+=total_road;


            Cursor cursor1 = sqlHelper.readValue(Year_,Month_,Day_c,"food");
            int total_food =  getTotal(cursor1);
            Log.d("FOOD",Integer.toString(total_food));
            total_food_week+=total_food;


            Cursor cursor2 = sqlHelper.readValue(Year_,Month_,Day_c,"cigarate");
            int total_cigarate =  getTotal(cursor2);
            Log.d("CIGARATE",Integer.toString(total_cigarate));
            total_cigarate_week+=total_cigarate;


            Cursor cursor3 = sqlHelper.readValue(Year_,Month_,Day_c,"mobile");
            int total_mobile =  getTotal(cursor3);
            Log.d("MOBILE",Integer.toString(total_mobile));
            total_mobile_week+=total_mobile;


            Cursor cursor4 = sqlHelper.readValue(Year_,Month_,Day_c,"other");
            int total_other =  getTotal(cursor4);
            Log.d("OTHER",Integer.toString(total_other));
            total_other_week+=total_other;

            current_day-=1;

        }

        values[0] = total_road_week;
        values[1] = total_food_week;
        values[2] = total_cigarate_week;
        values[3] = total_mobile_week;
        values[4] = total_other_week;

        Log.d("Total_of_week",Integer.toString(values[0] )+ "\n" +Integer.toString(values[1])+"\n" + Integer.toString(values[2])+"\n"+Integer.toString(values[3])+"\n"+Integer.toString(values[4]));

        setData();

    }

    private int getTotal(Cursor cursor) {
        int total = 0;
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++)
        {
            total+=cursor.getInt(4);
            cursor.moveToNext();
        }
        return total;
    }


    private void setData() {
        ArrayList<PieEntry> yData = new ArrayList<PieEntry>();
        ArrayList<String> xData = new ArrayList<>();
        for(int i=0;i<names.length;i++){
            yData.add(new PieEntry(values[i],i));
        }

        for(int i=0;i<names.length;i++){
            xData.add(names[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(yData,"Road\nFood\nCigarate\nMobile\nOthers");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(18);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.MAGENTA);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }
}
