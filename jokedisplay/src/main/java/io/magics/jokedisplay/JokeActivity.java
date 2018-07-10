package io.magics.jokedisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class JokeActivity extends AppCompatActivity {

    public static final String KEY_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        String joke = getIntent().getStringExtra(KEY_JOKE);
        if (joke != null) {
            ((TextView)findViewById(R.id.joke_text)).setText(joke);
        } else {
            Toast.makeText(this, "Intent was null", Toast.LENGTH_LONG).show();
        }
    }

}
