package com.example.huy.httpurlconnection;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImage_picture;
    private TextView mText_content;
    private Button mButton_json;
    private Button mButton_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mButton_json.setOnClickListener(this);
        mButton_picture.setOnClickListener(this);
    }

    private void initView() {
        mImage_picture = findViewById(R.id.image_picture);
        mText_content = findViewById(R.id.text_content);
        mButton_json = findViewById(R.id.button_json);
        mButton_picture = findViewById(R.id.button_picture);
    }

    private boolean checkInternetConnection() {
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo == null) {
            Toast.makeText(this, "No network", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isConnected()) {
            Toast.makeText(this, "Network is not connected", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isAvailable()) {
            Toast.makeText(this, "Network not available", Toast.LENGTH_LONG).show();
            return false;
        }
        Toast.makeText(this, "Network OK", Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_json:
                String jsonURL = getResources().getString(R.string.jsonURL);
                new DownloadJsonTask(mText_content).execute(jsonURL);
                break;
            case R.id.button_picture:
                String pictureURL = getResources().getString(R.string.pictureURL);
                new DownloadPictureTask(mImage_picture).execute(pictureURL);
                break;
            default:
                break;
        }
    }
}
