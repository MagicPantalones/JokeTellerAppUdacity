package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;

class MyEndpointAsyncTask extends AsyncTask<Void, Void, String> {

    private static JokeApi jokeApi = null;
    private EndpointTaskListener listener;

    public interface EndpointTaskListener {
        void onPostExecute(String s);
    }

    public MyEndpointAsyncTask(EndpointTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
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

        try {
            return jokeApi.visitJoker().execute().getData();
        } catch (IOException e) {
            Log.e(MyEndpointAsyncTask.class.getSimpleName(), e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        listener.onPostExecute(s);
    }
}
