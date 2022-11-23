package com.example.ormnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView title,Dis;
    Button add,view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        title=findViewById ( R.id.editText );
        Dis=findViewById ( R.id.editText2 );

        add=findViewById ( R.id.addBtn );
        view=findViewById ( R.id.viewBtn );

        DataBaseController.getInstance().fillContext(getApplicationContext());
        DataBaseController.getInstance().fetchUserData();


        if (DataBaseController.getInstance().data != null) {
            itemsInfo info = DataBaseController.getInstance().data;

            title.setText(info.getTitle ());
            Dis.setText(info.getDescription ());

        }

        view.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        } );

        add.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                itemsInfo itemsinfo = new itemsInfo ();
                itemsinfo.setTitle ( title.getText ().toString () );
                itemsinfo.setDescription (Dis.getText().toString());

                if(title.length () == 0 && Dis.length () == 0){
                    Toast.makeText(getApplicationContext(), "field is empty", Toast.LENGTH_SHORT).show();

                }else if ((DataBaseController.getInstance().data != null)) {
                    DataBaseController.getInstance ().data = itemsinfo;
                    DataBaseController.getInstance ().updateUserData ( DataBaseController.getInstance ().data );
                    Intent intent = new Intent ( MainActivity.this, MainActivity2.class );
                    startActivity ( intent );

                    Toast.makeText ( getApplicationContext (), "update data successfully", Toast.LENGTH_SHORT ).show ();

                }else {
                    if ((DataBaseController.getInstance().insertUserData(itemsinfo))){
                        Toast.makeText ( MainActivity.this, "user added successfully", Toast.LENGTH_SHORT ).show ();
                        title.setText ( " " );
                        Dis.setText ( " " );

                        Toast.makeText ( getApplicationContext (), "update data successfully", Toast.LENGTH_SHORT ).show ();

                    }else {
                        Toast.makeText(getApplicationContext(), "not storing the data", Toast.LENGTH_SHORT).show();
                    }
                }
                    }
        } );
    }
}

