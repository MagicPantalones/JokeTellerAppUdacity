package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;

import io.magics.jokedisplay.JokeActivity;

class MyEndpointAsyncTask extends AsyncTask<Context, Void, String> {

    private static JokeApi jokeApi = null;
    private Context context;

    @Override
    protected String doInBackground(Context... params) {
        if (jokeApi == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });

            jokeApi = builder.build();
        }

        context = params[0];
        try {
            return jokeApi.visitJoker().execute().getData();
        } catch (IOException e) {
            Log.e(MyEndpointAsyncTask.class.getSimpleName(), e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        if (s != null) {
            Intent intent = new Intent(context, JokeActivity.class);
            intent.putExtra(JokeActivity.KEY_JOKE, s);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, context.getString(R.string.api_error), Toast.LENGTH_LONG)
                    .show();
        }
    }
}
