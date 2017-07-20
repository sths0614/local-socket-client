package com.example.clientsample;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static String SOCKET_ADDRESS = "your.local.socket.address";

    public static void writeSocket(String message) throws IOException {
        LocalSocket sender = new LocalSocket();
        sender.connect(new LocalSocketAddress(SOCKET_ADDRESS));
        sender.getOutputStream().write(message.getBytes());
        sender.getOutputStream().close();
    }

    public void callWriteSocket() throws IOException {
        EditText editText = (EditText) findViewById(R.id.editText);
        writeSocket(editText.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendButton = (Button) findViewById(R.id.button);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    callWriteSocket();
                } catch (IOException e) {
                    Log.e(getClass().getName(), e.getMessage());
                }
            }
        });
    }
}
