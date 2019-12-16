package com.inei.appdatacrim.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.inei.appdatacrim.R;

import java.util.ArrayList;

public class DialogDelitosGraficos extends AppCompatDialogFragment {
    public static final String TAG = DialogDelitosGraficos.class.getSimpleName();
    private static final String ANIO = "ANIO";
    PieChart grafico_pie;
    ArrayList<String> Ejex = new ArrayList<>();
    ArrayList<Float>  datos2016 = new ArrayList<>();
    ArrayList<Float>  datos2017 = new ArrayList<>();
    ArrayList<Float>  datos2018 = new ArrayList<>();
    ArrayList<Float>  datos2019 = new ArrayList<>();

    private String delito_anio;



    /*RECIBE DATOS DE ACTIVIDAD*/
    public static DialogDelitosGraficos newInstance(String anio) {
        Bundle args = new Bundle();
        args.putString(ANIO,anio);

        DialogDelitosGraficos frag = new DialogDelitosGraficos();
        frag.setArguments(args);

        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.ThemeOverlay_MaterialComponents_Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_delitos_graficos, null);
        grafico_pie = view.findViewById(R.id.chart_pie);
        delito_anio = ""+getArguments().get(ANIO);
        Ejex.add("Delitos contra el patrimonio");
        Ejex.add("Delitos contra la vida, el cuerpo y la salud");
        Ejex.add("Delitos contra la seguridad pública");
        Ejex.add("Delitos contra la libertad");
        Ejex.add("Delitos contra la administración pública");

        datos2016.add(79.02f);
        datos2016.add(8.72f);
        datos2016.add(6.92f);
        datos2016.add(3.3f);
        datos2016.add(0.82f);

        datos2017.add(75.56f);
        datos2017.add(9.8f);
        datos2017.add(7.97f);
        datos2017.add(3.68f);
        datos2017.add(1.27f);

        datos2018.add(75.66f);
        datos2018.add(8.6f);
        datos2018.add(8.5f);
        datos2018.add(4.13f);
        datos2018.add(1.13f);

        datos2019.add(78.68f);
        datos2019.add(7.57f);
        datos2019.add(6.68f);
        datos2019.add(4.58f);
        datos2019.add(1.58f);
        if(delito_anio.equals("2016"))
        {setearGrafico(grafico_pie,datos2016);}
        else
        if(delito_anio.equals("2017"))
        {setearGrafico(grafico_pie,datos2017);}
        else
        if(delito_anio.equals("2018"))
        {setearGrafico(grafico_pie,datos2018);}
        else
        if(delito_anio.equals("2019"))
        {setearGrafico(grafico_pie,datos2019);}


        builder.setView(view)
                .setTitle("Delitos Agrupados "+getArguments().getString(ANIO))
                .setIcon(R.drawable.ic_pie_chart)
                .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });


        return builder.create();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public PieChart setearGrafico(PieChart grafico,ArrayList<Float> datos){
        PieChart chart = grafico;
        ArrayList<PieEntry> entries = new ArrayList<>();

        int subindicadorsize=datos.size();


        // NOTE: The order of the entries when being added to the entries array determines their position around the center of the chart.
        for (int i = 0; i < subindicadorsize ; i++) {
            entries.add(new PieEntry(datos.get(i),Ejex.get(i)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 0));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);


        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(chart));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        chart.setData(data);
        chart.setDrawEntryLabels(false);
        chart.setDrawCenterText(false);

        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 5, 5, 5);
        chart.setDragDecelerationFrictionCoef(0.95f);
        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);
        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);
        chart.setHoleRadius(40f);
        chart.setTransparentCircleRadius(43f);
        chart.setDrawCenterText(true);
        chart.setRotationAngle(0);
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);
        chart.animateY(1400, Easing.EaseInOutQuad);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(22.5f);

        // entry label styling
        chart.setEntryLabelColor(Color.BLACK);
        chart.setEntryLabelTextSize(12f);

        // undo all highlights
        chart.highlightValues(null);
        chart.invalidate();

        return chart;
    }



}
