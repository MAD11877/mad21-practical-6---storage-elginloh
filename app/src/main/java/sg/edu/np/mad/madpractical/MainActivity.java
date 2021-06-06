package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    User user = new User("Elgin Loh", "Hi i am currently from IT Year 2 and i love playing games and used to be in E-sports for mobile legends", 0, false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int id = getIntent().getIntExtra("id",0);
        User currentUser = ListActivity.UserInfoList.get(id);
        TextView DisplayDesc = (TextView) findViewById((R.id.displayDesc));
        TextView DisplayName = (TextView) findViewById(R.id.displayName);
        DisplayName.setText(currentUser.name);
        DisplayDesc.setText(currentUser.description);
        Button button = findViewById(R.id.btnFollow);
        if(currentUser.followed == false)
        {
            button.setText("Follow");
        }
        else
        {
            button.setText("Unfollow");
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser.followed == false) {
                    currentUser.followed = true;
                    button.setText("Unfollow");
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_LONG).show();
                } else {
                    currentUser.followed = false;
                    button.setText("Follow");
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_LONG).show();
                }
                DBHandler db = new DBHandler(MainActivity.this);
                db.updateUser(currentUser);
            }

        });
    }
}