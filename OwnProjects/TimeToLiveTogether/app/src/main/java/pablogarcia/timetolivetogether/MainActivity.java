package pablogarcia.timetolivetogether;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import pablogarcia.timetolivetogether.CustomAnimation.BlinkAnimation;

public class MainActivity extends AppCompatActivity {

    TextView dotsTextView;
    Integer counter = 0;
    Integer delay = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding();
        startAnimationText();
    }

    private void binding() {
        dotsTextView = (TextView) findViewById(R.id.dotsTextView);

    }

    private void startAnimationText() {
        final Handler handler = new Handler();
        final BlinkAnimation anim = new BlinkAnimation(0.0f, 1.0f, delay/2);

        handler.postDelayed(new Runnable() {

            public void run() {
                if (counter < 30) {
                    dotsTextView.startAnimation(anim);
                    dotsTextView.setText(dotsTextView.getText().toString().concat("."));
                    counter++;
                    handler.postDelayed(this, delay);
                }
            }

        }, delay);

    }

}
