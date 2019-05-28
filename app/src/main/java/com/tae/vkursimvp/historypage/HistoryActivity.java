package com.tae.vkursimvp.historypage;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.tae.vkursimvp.NbuInterface;
import com.tae.vkursimvp.PojoVal;
import com.tae.vkursimvp.R;
import com.tae.vkursimvp.RetrofitClient;
import com.tae.vkursimvp.listcurrency.MainPresenter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class HistoryActivity extends AppCompatActivity implements HistoryMainContract.View{

    PojoVal pojoParcel;
    String currClick;
    Double valCur;
    Retrofit retrofit;//
    RetrofitClient retrofitClient;//
    Date today;//
    Date yesterday;//
    Date datebeforeyesterday;//
    ArrayList<PojoVal> pojoVals = new ArrayList<PojoVal>();
    GraphView graph;
    DataPoint[] dataPoints = new DataPoint[12];
    LineGraphSeries<DataPoint> series;
    StaticLabelsFormatter staticLabelsFormatter;
    SeekBar seekBar;
    //new
    ArrayList <Date> datesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Bundle bundle = getIntent().getExtras();

        retrofit= retrofitClient.callRetrofit();//
        NbuInterface nbuInterface = retrofit.create(NbuInterface.class);//

        if (bundle!=null)
            pojoParcel = bundle.getParcelable("Parcel");
        System.out.println(pojoParcel.getCc());
        currClick = pojoParcel.getCc();
        System.out.println(pojoParcel.getRate());
        valCur = pojoParcel.getRate();

        today = new Date();//
        DateFormat dateformat = new SimpleDateFormat("yyyyMMdd");//
        System.out.println(dateformat.format(today));//



        series = new LineGraphSeries<DataPoint>();
        graph = (GraphView) findViewById(R.id.graph);
        graph.addSeries(series);
        staticLabelsFormatter= new StaticLabelsFormatter(graph);
        // dates~~~~~~~~~
        Calendar calendar = Calendar.getInstance();//
        calendar.setTime(today);//
        calendar.add(Calendar.DATE, -1);//
        yesterday= calendar.getTime();//
        System.out.println(dateformat.format(yesterday)+ "THIS IS YESTERDAY ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");//
        calendar.add(Calendar.DATE, -1);//
        datebeforeyesterday= calendar.getTime();//
        System.out.println(dateformat.format(datebeforeyesterday)+ "THIS IS DATE BEFORE YESTERDAY ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");//

        HistoryPresenter historyPresenter = new HistoryPresenter(this);
        seekBar=findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              //  graph.removeAllSeries();
                historyPresenter.calendar(10, currClick);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



//        historyPresenter.getHistory(currClick, dateformat.format(today));
//        historyPresenter.getHistory(currClick, dateformat.format(yesterday));
        System.out.println("FORMAT OF THE DATE ____________---------------- " + dateformat.format(yesterday));


//historyPresenter.calendar(10, currClick);
             Toast.makeText(this,pojoParcel.getCc()+ " - this is the currency rate",Toast.LENGTH_LONG).show();
    }

    public void createGraphView (DataPoint dataPoint) {
        series.appendData(dataPoint, false, 11);
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Дати");
        // StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        DateFormat dateformat1 = new SimpleDateFormat("dd.MM");
        graph.setBackgroundColor(Color.argb(0, 10, 10, 10));
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(dateformat1.format(yesterday));

         staticLabelsFormatter.setHorizontalLabels((String[]) arrayList.toArray());
            staticLabelsFormatter.setHorizontalLabels(new String[] {
                    dateformat1.format(datebeforeyesterday),
                    dateformat1.format(yesterday),
                    dateformat1.format(today)});

        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
    }

    @Override
    public void handleResults(ArrayList<PojoVal> pojoNbu) {
        if (pojoNbu != null ) {
            int j=0;
            PojoVal pojoVal = pojoNbu.get(j++);
            pojoVals.add(pojoNbu.get(0));
            int i = 0;
            DataPoint dataPoint = new DataPoint(pojoVals.size(), pojoVal.getRate());
            createGraphView(dataPoint);
        } else
            {
            Toast.makeText(this, "NO RESULTS FOUND",
                    Toast.LENGTH_LONG).show();
        }
    }
    public void handleError(Throwable t){
        System.out.println(t+"!!!!!!!!!!!!!!!  ERROR  !!!!!!!!!!!!!!");
    }
}
// новый графвью, сикбар - увеличиает колво точек и график должен перерисовываться
