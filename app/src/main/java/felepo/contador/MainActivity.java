package felepo.contador;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    //Variable para llevar el numero de veces que se preciona un boton
    public int contador;

    TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textoResultado = (TextView) findViewById(R.id.contadorPulsaciones);

        contador = 0;
    }

    public void incrementarContador(View vista)
    {
        contador++;

        if( contador < 0 )
        {
            CheckBox casillaNegativo = (CheckBox) findViewById(R.id.negativos);

            if( !casillaNegativo.isChecked() )
            {
                contador = 0;
            }
        }

        textoResultado.setText("" + contador);
    }

    public void decrementarContador(View vista)
    {
        contador--;

        if( contador < 0 )
        {
            CheckBox casillaNegativo = (CheckBox) findViewById(R.id.negativos);

            if( !casillaNegativo.isChecked() )
            {
                contador = 0;
            }
        }

        textoResultado.setText("" + contador);
    }

    public void reiniciarContador(View vista)
    {
        EditText numeroReset = (EditText) findViewById(R.id.inNumber);
        CheckBox casillaNegativo = (CheckBox) findViewById(R.id.negativos);

        try
        {
            contador = Integer.parseInt(numeroReset.getText().toString());
        }
        catch(Exception e)
        {
            contador = 0;
        }

        if( ( !casillaNegativo.isChecked() ) && ( contador < 0 ) )
        {
            contador = 0;
        }

        numeroReset.setText("");
        textoResultado.setText("" + contador);
    }
}
