package com.maciekjanusz.taledemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.maciekjanusz.tale.Tale;

import static android.util.Log.ERROR;

public class TaleDemoActivity extends AppCompatActivity {
    
    Tale tale = new Tale().at();

    {
        new Tale("Hello <init>").at().tell();
    }

    static {
        new Tale("Hello <clinit>").at().tell();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        tale.tell();

        new Tale().tag("Tagged").at().story("Hello log 1").tell();
        new Tale().story("Hello onCreate").at().tell();
        new Tale().how(ERROR).tell();
        new Tale("Some awesome log message").tell();

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        new Tale("Hello runnables!").tell();
                    }
                });
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        new Tale("Hello runnables!").at().tell();
                    }
                });
            }
        });

        new Handler().post(new TestRunnable());
        new Handler().post(new TestStaticRunnable());

        new Handler().post(new TestAbstractRunnable() {
            @Override
            public void run() {
                new Tale("Hello abstract runnable!").tell();
            }
        });
        new Handler().post(new TestInterfaceRunnable() {
            @Override
            public void run() {
                new Tale("Hello test interface runnable!").tell();
            }
        });

        new Tale().at().tell();
    }

    class TestRunnable implements Runnable {

        @Override
        public void run() {
            new Tale("Hello testRunnable!").tell();
        }
    }

    static class TestStaticRunnable implements Runnable {

        @Override
        public void run() {
            new Tale("Hello testStaticRunnable!").tell();
        }
    }

    abstract class TestAbstractRunnable implements Runnable {

    }

    interface TestInterfaceRunnable extends Runnable {

    }
}
