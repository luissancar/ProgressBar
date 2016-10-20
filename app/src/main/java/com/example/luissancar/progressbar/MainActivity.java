package com.example.luissancar.progressbar;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import static com.example.luissancar.progressbar.R.id.progressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar bar1;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar1=(ProgressBar)findViewById(progressBar);
        bar1.setMax(100);
    }



    public void pulsar(View v){
        bar1.setVisibility(View.VISIBLE);
        if (i>100)
            i=0;
        bar1.setProgress(i++);
    }

    public void oculta(View v){
        if (bar1.getVisibility()==ProgressBar.VISIBLE)
            bar1.setVisibility(View.GONE);
        else
            bar1.setVisibility(View.VISIBLE);
    }




    public void hilo(View v){
        AsyncTaskCargaDatos as=new AsyncTaskCargaDatos();
        as.execute();

    }



    //
    // ASYNKTASCK
    //

    public class AsyncTaskCargaDatos extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            for(int i=1; i<=10; i++) {
                //tareaLarga();
               try {
               Thread.sleep(1000);}
               catch (InterruptedException e) {}

                publishProgress(i*10);

                if(isCancelled())
                    break;
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();

            bar1.setProgress(progreso);
        }

        @Override
        protected void onPreExecute() {
            bar1.setMax(100);
            bar1.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
                Toast.makeText(MainActivity.this, "Tarea finalizada!",
                        Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(MainActivity.this, "Tarea cancelada!",
                    Toast.LENGTH_SHORT).show();
        }

    }// fin asynctask




}
