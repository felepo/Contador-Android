package felepo.contador;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    //Variable para llevar el numero de veces que se preciona un boton
    public int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contador = 0;
    }

    public void incrementarContador(View vista)
    {
        contador++;

        mostrarContador();
    }

    public void decrementarContador(View vista)
    {
        contador--;

        mostrarContador();
    }

    public void reiniciarContador(View vista)
    {
        contador = 0;

        mostrarContador();
    }

    public void mostrarContador()
    {
        //Con esto mando a traer el TextView contadorPulsaciones desde la clase R de recursos
        TextView textoResultado = (TextView) findViewById(R.id.contadorPulsaciones);
        textoResultado.setText("" + contador);
    }
}
