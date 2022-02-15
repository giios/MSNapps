package com.cursoandroid.msnapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.cursoandroid.msnapp.R;
//import com.github.rtoshiro.util.format.SimpleMaskFormatter;
//import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class LoginActivity extends AppCompatActivity {

    private EditText telefone;
    private String ultimoCaracterDigitado = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        telefone = findViewById(R.id.edit_telefone);

        telefone.addTextChangedListener(new TextWatcher() {
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
                    if(!ultimoCaracterDigitado.equals(" ")){
                        telefone.append("-");
                    }else{
                        telefone.getText().delete(tamanhodigitos -1, tamanhodigitos);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //telefone.addTextChangedListener(MaskEditUtil.mask(telefone, "(##) #####-#####"));

        //telefone.addTextChangedListener();

    }
}