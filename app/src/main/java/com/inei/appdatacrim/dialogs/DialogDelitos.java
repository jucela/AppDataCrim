package com.inei.appdatacrim.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.inei.appdatacrim.R;

import java.util.ArrayList;

public class DialogDelitos extends AppCompatDialogFragment {
    public static final String TAG = DialogDelitos.class.getSimpleName();
    private static final String ANIO = "ANIO";
    private static final String ESTADO1 = "ESTADO1";
    private static final String ESTADO2 = "ESTADO2";
    private static final String ESTADO3 = "ESTADO3";
    private static final String ESTADO4 = "ESTADO4";
    private static final String ESTADO5 = "ESTADO5";
    private static final String ESTADO6 = "ESTADO6";
    private static final String ESTADO7 = "ESTADO7";
    private static final String ESTADO8 = "ESTADO8";
    private static final String ESTADO9 = "ESTADO9";
    private static final String ESTADO10 = "ESTADO10";
    private static final String ESTADO11 = "ESTADO11";
    private static final String ESTADO12 = "ESTADO12";
    private static final String ESTADO13 = "ESTADO13";
    private static final String ESTADO14 = "ESTADO14";
    private static final String ESTADO15 = "ESTADO15";
    private static final String ESTADO16 = "ESTADO16";
    private CheckBox check0,check1,check2,check3,check4,check5,check6,check7,check8,check9,check10,check11,check12,check13,check14,check15,check16;
    private SendDialogListener listener;
    int numero;


    public static DialogDelitos newInstance(String anio,ArrayList<Boolean> StateCheckCrime) {
        Bundle args = new Bundle();
        args.putString(ANIO,anio);
        args.putBoolean(ESTADO1,StateCheckCrime.get(1));
        args.putBoolean(ESTADO2,StateCheckCrime.get(2));
        args.putBoolean(ESTADO3,StateCheckCrime.get(3));
        args.putBoolean(ESTADO4,StateCheckCrime.get(4));
        args.putBoolean(ESTADO5,StateCheckCrime.get(5));
        args.putBoolean(ESTADO6,StateCheckCrime.get(6));
        args.putBoolean(ESTADO7,StateCheckCrime.get(7));
        args.putBoolean(ESTADO8,StateCheckCrime.get(8));
        args.putBoolean(ESTADO9,StateCheckCrime.get(9));
        args.putBoolean(ESTADO10,StateCheckCrime.get(10));
        args.putBoolean(ESTADO11,StateCheckCrime.get(11));
        args.putBoolean(ESTADO12,StateCheckCrime.get(12));
        args.putBoolean(ESTADO13,StateCheckCrime.get(13));
        args.putBoolean(ESTADO14,StateCheckCrime.get(14));
        args.putBoolean(ESTADO15,StateCheckCrime.get(15));
        args.putBoolean(ESTADO16,StateCheckCrime.get(16));

        DialogDelitos frag = new DialogDelitos();
        frag.setArguments(args);

        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.ThemeOverlay_MaterialComponents_Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_denuncias, null);
        check0 = view.findViewById(R.id.id_check0);
        check1 = view.findViewById(R.id.id_check1);
        check2 = view.findViewById(R.id.id_check2);
        check3 = view.findViewById(R.id.id_check3);
        check4 = view.findViewById(R.id.id_check4);
        check5 = view.findViewById(R.id.id_check5);
        check6 = view.findViewById(R.id.id_check6);
        check7 = view.findViewById(R.id.id_check7);
        check8 = view.findViewById(R.id.id_check8);
        check9 = view.findViewById(R.id.id_check9);
        check10 = view.findViewById(R.id.id_check10);
        check11 = view.findViewById(R.id.id_check11);
        check12 = view.findViewById(R.id.id_check12);
        check13 = view.findViewById(R.id.id_check13);
        check14 = view.findViewById(R.id.id_check14);
        check15 = view.findViewById(R.id.id_check15);
        check16 = view.findViewById(R.id.id_check16);
        check1.setChecked(getArguments().getBoolean(ESTADO1));
        check2.setChecked(getArguments().getBoolean(ESTADO2));
        check3.setChecked(getArguments().getBoolean(ESTADO3));
        check4.setChecked(getArguments().getBoolean(ESTADO4));
        check5.setChecked(getArguments().getBoolean(ESTADO5));
        check6.setChecked(getArguments().getBoolean(ESTADO6));
        check7.setChecked(getArguments().getBoolean(ESTADO7));
        check8.setChecked(getArguments().getBoolean(ESTADO8));
        check9.setChecked(getArguments().getBoolean(ESTADO9));
        check10.setChecked(getArguments().getBoolean(ESTADO10));
        check11.setChecked(getArguments().getBoolean(ESTADO11));
        check12.setChecked(getArguments().getBoolean(ESTADO12));
        check13.setChecked(getArguments().getBoolean(ESTADO13));
        check14.setChecked(getArguments().getBoolean(ESTADO14));
        check15.setChecked(getArguments().getBoolean(ESTADO15));
        check16.setChecked(getArguments().getBoolean(ESTADO16));


        builder.setView(view)
                .setTitle("Denuncias Delitos "+getArguments().getString(ANIO))
                .setIcon(R.drawable.ic_place)
                .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean estado0 = check0.isChecked();
                        boolean estado1 = check1.isChecked();
                        boolean estado2 = check2.isChecked();
                        boolean estado3 = check3.isChecked();
                        boolean estado4 = check4.isChecked();
                        boolean estado5 = check5.isChecked();
                        boolean estado6 = check6.isChecked();
                        boolean estado7 = check7.isChecked();
                        boolean estado8 = check8.isChecked();
                        boolean estado9 = check9.isChecked();
                        boolean estado10 = check10.isChecked();
                        boolean estado11 = check11.isChecked();
                        boolean estado12 = check12.isChecked();
                        boolean estado13 = check13.isChecked();
                        boolean estado14 = check14.isChecked();
                        boolean estado15 = check15.isChecked();
                        boolean estado16 = check16.isChecked();
                        ArrayList<Boolean> estados = new ArrayList<>();
                        estados.add(estado0);
                        estados.add(estado1);
                        estados.add(estado2);
                        estados.add(estado3);
                        estados.add(estado4);
                        estados.add(estado5);
                        estados.add(estado6);
                        estados.add(estado7);
                        estados.add(estado8);
                        estados.add(estado9);
                        estados.add(estado10);
                        estados.add(estado11);
                        estados.add(estado12);
                        estados.add(estado13);
                        estados.add(estado14);
                        estados.add(estado15);
                        estados.add(estado16);
                        listener.applyTexts(estados,getArguments().getString(ANIO));
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

        try {
            listener = (SendDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface SendDialogListener {
        void applyTexts(ArrayList<Boolean> estados,String anio);
    }



}
