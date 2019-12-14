package com.inei.appdatacrim.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.inei.appdatacrim.R;

import java.util.ArrayList;

public class DialogResidencias extends AppCompatDialogFragment {
    public static final String TAG = DialogResidencias.class.getSimpleName();
    private static final String ESTADO1 = "ESTADO1";
    private CheckBox check1;
    private SendDialogResidenciasListener listener;

    int numero;

    /*RECIBE DATOS DE ACTIVIDAD*/
    public static DialogResidencias newInstance(ArrayList<Boolean> StateCheckStation) {
        Bundle args = new Bundle();
        args.putBoolean(ESTADO1,StateCheckStation.get(0));

        DialogResidencias frag = new DialogResidencias();
        frag.setArguments(args);

        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.ThemeOverlay_MaterialComponents_Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_residencias, null);
        check1 = view.findViewById(R.id.id_check_residencia);
        check1.setChecked(getArguments().getBoolean(ESTADO1));


        builder.setView(view)
                .setTitle("Residencia de privados de Libertad")
                .setIcon(R.drawable.ic_action_home)
                .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean estado0 = check1.isChecked();
                        ArrayList<Boolean> estados = new ArrayList<>();
                        estados.add(estado0);
                        listener.receiveResidencia(estados);
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
            listener = (SendDialogResidenciasListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }
    /*ENVIA DATOS A ACTIVIDAD*/
    public interface SendDialogResidenciasListener {
        void receiveResidencia(ArrayList<Boolean> estados);
    }



}
