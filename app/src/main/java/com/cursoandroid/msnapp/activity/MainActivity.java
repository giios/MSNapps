package com.cursoandroid.msnapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cursoandroid.msnapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText nome;
    private EditText telefone;
    //private String ultimoCaracterDigitado = " ";
    private EditText codPais;
    private EditText codValidacao;
    private Button cadastrar;

    //private DatabaseReference referenciaFirebase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        telefone     = (EditText) findViewById(R.id.edit_telefone);
        nome         = (EditText) findViewById(R.id.edit_nome);
        codPais      = (EditText) findViewById(R.id.edit_cod_pais);
        codValidacao = (EditText) findViewById(R.id.edit_cod_validacao);
        cadastrar    = (Button) findViewById(R.id.bt_cadastrar) ;

        telefone.addTextChangedListener(MaskEditUtil.mask(telefone, MaskEditUtil.FORMAT_FONE));
        codPais.addTextChangedListener(MaskEditUtil.mask(codPais, MaskEditUtil.FORMAT_COD_PAIS));


        /*Criando ação de clique no botão cadastrar ao mesmo tempo que formata o numero de telefone, retirando alguns caracteres*/
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeUsuario = nome.getText().toString();
                String telefoneCompleto = codPais.getText().toString() +
                        telefone.getText().toString();

                String telefoneSemFormatacao = telefoneCompleto.replace("+", "");
                telefoneSemFormatacao = telefoneSemFormatacao.replace("-", "");
                telefoneSemFormatacao = telefoneSemFormatacao.replace("(", "");
                telefoneSemFormatacao = telefoneSemFormatacao.replace(")", "");
                telefoneSemFormatacao = telefoneSemFormatacao.replace(" ", "");

                //Gerar token
                Random randomico = new Random();
                int numeroRandomico = randomico.nextInt(999999 - 100000) + 100000;
                String token = String.valueOf( numeroRandomico );


            }
        });


        /*Outra maneira de realizar a formatação, porem mais simples e direta, sendo possível realizar APENAS a formatação de telefone*/
        /*telefone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Integer tamanhodigitos = telefone.getText().toString().length();
                if (tamanhodigitos > 1){
                    ultimoCaracterDigitado = telefone.getText().toString().substring(tamanhodigitos - 1);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Integer tamanhodigitos = telefone.getText().toString().length();
                if(tamanhodigitos == 2){
                    if(!ultimoCaracterDigitado.equals(" ")){
                        telefone.append(" ");
                    }else{
                        telefone.getText().delete(tamanhodigitos -1, tamanhodigitos);
                    }
                }else if(tamanhodigitos == 8){
                    if(!ultimoCaracterDigitado.equals("-")){
                        telefone.append("-");
                    }else{
                        telefone.getText().delete(tamanhodigitos -1, tamanhodigitos);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/


        //referenciaFirebase.child("ponto").setValue(100);
    }
}