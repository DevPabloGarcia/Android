package pablo.com.webviewgoogleplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webview = new WebView(this);
        setContentView(webview);

        // get input stream handle to file
        try {
            // read the HTML from the file
            InputStream fin = getAssets().open("openApp.html");
            byte[] buffer = new byte[fin.available()];
            fin.read(buffer);
            fin.close();

            // load the HTML
            webview.loadData(new String(buffer), "text/html", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
