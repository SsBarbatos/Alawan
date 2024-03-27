package com.example.alawan.Class;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import com.example.alawan.Class.Adapter.AdapterListeAnimalAlerte;

import java.util.ArrayList;

public class DescriptionDialog extends AlertDialog.Builder
{
    private EditText editText;
    AdapterListeAnimalAlerte adapterListeAnimalAlerte = new AdapterListeAnimalAlerte(new ArrayList<>());

    public DescriptionDialog(Context context)
    {
        super(context);

        setTitle("Informations supplémentaires");
        editText = new EditText(context);
        setView(editText);

        setPositiveButton("Lancer l'alerte", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                String description;

                if(editText.getText().toString().equals(""))
                    description = "Aucune description";
                else
                    description = editText.getText().toString();

                adapterListeAnimalAlerte.setDescription(description);
            }
        });
        setNegativeButton("Annuler", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
            }
        });
    }

    @Override
    public AlertDialog show()
    {
        return super.show();
    }
}
