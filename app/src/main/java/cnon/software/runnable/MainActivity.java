package cnon.software.runnable;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView counterText;
    Button start;
    Button stop;

    Handler handler;
    Runnable runnable;

    int counter = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        counterText = findViewById(R.id.counterText);
        counterText.setText(String.valueOf(counter));
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);

    }


    public void start(View view)
    {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                counter++;
                counterText.setText(String.valueOf(counter));
                handler.postDelayed(this, 1000);

            }
        };

        handler.post(runnable);
        start.setEnabled(false);
        stop.setEnabled(true);

    }

    public void stop(View view)
    {
handler.removeCallbacks(runnable);
stop.setEnabled(false);
start.setEnabled(true);

    }
}