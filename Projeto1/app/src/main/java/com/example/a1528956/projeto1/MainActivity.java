package com.example.a1528956.projeto1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import br.com.projetocombustivel.controle.combustivelCotrole;

public class MainActivity extends AppCompatActivity {

    combustivelCotrole cn = new combustivelCotrole();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clique(android.view.View view){
        EditText gasolina = (EditText) findViewById(R.id.txtVlrGas);
        EditText etanol = (EditText) findViewById(R.id.txtVlrAlc);
        TextView textView = (TextView) findViewById(R.id.lblResultado);
        textView.setText(cn.Calcular(gasolina.getText().toString(), etanol.getText().toString()).getDescricao());
    }
}
