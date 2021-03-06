package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import io.magics.jokedisplay.JokeActivity;

import static android.widget.Toast.LENGTH_LONG;


public class MainActivity extends AppCompatActivity implements MainActivityFragment.MainFragListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void jokeButtonClicked() {
        new MyEndpointAsyncTask(new MyEndpointAsyncTask.EndpointTaskListener() {
            @Override
            public void onPostExecute(String s) {
                if (s != null) {
                    Intent intent = new Intent(MainActivity.this, JokeActivity.class);
                    intent.putExtra(JokeActivity.KEY_JOKE, s);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this,
                            getString(R.string.api_error), LENGTH_LONG).show();
                }
            }
        }).execute();
    }
}
