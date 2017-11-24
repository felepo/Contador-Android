package felepo.contador;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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

        textoResultado.setText("" + contador);

        //Establecer el escuchador para el EditText de nReinicios
        EventoTeclado teclado = new EventoTeclado();
        EditText nReinicios = (EditText) findViewById(R.id.nReinicios);
        nReinicios.setOnEditorActionListener(teclado);

    }

    //------------------COMENTAMOS EL USO DEL BUNDLE PARA LA PERSISTENCIA DE DATOS.-----------------
    /*
    Con el método onSaveInstanceState() guardo el estado de cuenta en un Bundle si algo pasa. Por
    ejemplo: otra aplicación se coloca en primer plano, se cambia la dirección del teléfono, etc.
    */
    /*
    public void onSaveInstanceState(Bundle estado)
    {
        estado.putInt("cuenta", contador);

        super.onSaveInstanceState(estado);
    }
    */

    //Con onRestoreInstanceState() recupero la información que previamente guarde en el Bundle.
    /*
    public void onRestoreInstanceState(Bundle estado)
    {
        super.onRestoreInstanceState(estado);

        contador = estado.getInt("cuenta");

        textoResultado.setText("" + contador);
    }
    */
    //----------------------------------------------------------------------------------------------


    public void onResume()
    {
        super.onResume();

        //Se crea un objeto SharedPreferences
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        contador = datos.getInt("cuenta", 0);

        textoResultado.setText("" + contador);
    }

    public void onPause()
    {
        super.onPause();

        //Se crea un objeto SharedPreferences
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        //Se hace editable el objeto recien creado
        SharedPreferences.Editor miEditor = datos.edit();

        //Establecer información a editar
        miEditor.putInt("cuenta", contador);
        //Transferir la información al SharedPreferences
        miEditor.apply();
    }


    /*
        Clase para indicar el Evento cuando se presiona la tecla Done del teclado, el contador
        se actualiza.
    */
    class EventoTeclado implements TextView.OnEditorActionListener
    {

        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
        {
            if( i == EditorInfo.IME_ACTION_DONE )
            {
                reiniciarContador(null);
            }

            return false;
        }
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
        EditText numeroReset = (EditText) findViewById(R.id.nReinicios);
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

        //Código para ocultar el teclado luego de ingresar número de reinicio
        InputMethodManager miTeclado = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        miTeclado.hideSoftInputFromWindow(numeroReset.getWindowToken(), 0);
    }
}
