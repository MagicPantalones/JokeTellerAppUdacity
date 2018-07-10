package com.udacity.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class AsyncJokeTest {
    //Help to write test from Keerthana S's answer here:
    //https://stackoverflow.com/questions/2321829/android-asynctask-testing-with-android-test-framework

    @Test
    public void testAsyncTask() {
        final CountDownLatch signal = new CountDownLatch(1);
        final MyEndpointAsyncTask task = new MyEndpointAsyncTask() {
            @Override
            protected void onPostExecute(String s) {
                assertNotNull(s);
                assertTrue(s.length() > 0);
                signal.countDown();

            }
        };

        try {
            task.execute(InstrumentationRegistry.getTargetContext());
            signal.await();
        } catch (InterruptedException e) {
            fail();
        }
    }

}
