package com.example.sharedprefsanddialog;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sp;

    Dialog d;
    EditText etUserName;
    EditText etPass;
    Button btnCustomLogin;

    Button btnLogin;
    private TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay=findViewById(R.id.tvDisplay);
        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        
        sp=getSharedPreferences("details1",0);

        String strFname = sp.getString("fname",null);
        String strLname = sp.getString("lname",null);

        if(strLname!=null && strFname !=null)
        {
            tvDisplay.setText("Welcome " + strFname + " " + strLname);
        }
    }

    public void createLoginDialog()
    {
        d= new Dialog(this);
        d.setContentView(R.layout.custom_layout);
        d.setTitle("Login");
        d.setCancelable(true);
        etUserName= d.findViewById(R.id.etUserName);
        etPass= (EditText)d.findViewById(R.id.etPassword);
        btnCustomLogin=(Button)d.findViewById(R.id.btnDialogLogin);
        btnCustomLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnCustomLogin==v)
                {
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("fname",etUserName.getText().toString());
                    editor.putString("lname",etPass.getText().toString());
                    editor.commit();
                    d.dismiss();
                }
            }
        });
        d.show();
    }

    @Override
    public void onClick(View v)
    {
        if(v==btnLogin)
        {
            createLoginDialog();
        }
        else if(v==btnCustomLogin)
        {
            Toast.makeText(this,"username password saved",Toast.LENGTH_LONG).show();
            d.dismiss();
        }
    }
}