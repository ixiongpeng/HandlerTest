package com.example.xiongpeng.handlertest;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public Handler handler = null;

    TextView tv = null;
    Button bt = null;
    String string = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.tv);
        bt = (Button)findViewById(R.id.bt);
        bt.setOnClickListener(this);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                string = msg.arg1 + "";
                switch (msg.what){
                    case 1:
                        tv.setText(string);
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        new StartSenMessage().start();
    }

    class StartSenMessage extends Thread{
        int times = 1;
        @Override
        public void run() {
            while(times != 10) {
                Message msg = new Message();
                msg.what = 1;
                msg.arg1 = times;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                times++;
            }
        }
    }
}


//Loop.prepaer Loop.loop()
//参考http://blog.csdn.net/iispring/article/details/47115879