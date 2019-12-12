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

    private CheckBox check1,check2,check3,check4,check5,check6,check7,check8,check9,check10,check11,check12,check13,check14,check15,check16,check17;
    private SendDialogListener listener;
    int numero;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //numero = getArguments().getInt("num");
        numero=1;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_denuncias, null);
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
        check17 = view.findViewById(R.id.id_check17);

        builder.setView(view)
                .setTitle(""+numero)
                .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
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
                        boolean estado17 = check17.isChecked();
                        ArrayList<Boolean> estados = new ArrayList<>();
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
                        estados.add(estado17);
                        listener.applyTexts(estados);
                    }
                });


        return builder.create();
        //return super.onCreateDialog(savedInstanceState);
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
        void applyTexts(ArrayList<Boolean> estados);
    }



}
