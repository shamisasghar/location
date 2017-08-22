package com.sha.location;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button buttonStartService = (Button) findViewById(R.id.startservice);
        Button buttonStopService = (Button) findViewById(R.id.stopservice);


        buttonStartService.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Main2Activity.this, com.sha.location.NotifyService.class);
                Main2Activity.this.startService(intent);
            }
        });
        buttonStopService.setOnClickListener(new Button.OnClickListener()

        {

            @Override
            public void onClick (View arg0){
                // TODO Auto-generated method stub
                stopService(new Intent(Main2Activity.this,NotifyService.class));

            }



        });

    }



    }

