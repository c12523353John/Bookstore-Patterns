package finalyear.bookstorepatterns;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OpeningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        final Button registerBtn = (Button) findViewById(R.id.btnOpenerRegister);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpeningActivity.this, Register.class);
                OpeningActivity.this.startActivity(intent);
            }
        });

        final Button loginBtn = (Button) findViewById(R.id.btnOpenerLogin);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpeningActivity.this, Login.class);
                OpeningActivity.this.startActivity(intent);
            }
        });

    }
}
