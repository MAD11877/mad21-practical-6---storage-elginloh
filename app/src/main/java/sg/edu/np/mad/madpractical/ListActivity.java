package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    static ArrayList<User> UserInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        ImageView imgView = (ImageView) findViewById(R.id.imageView3);
//        imgView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
//                builder.setTitle("Profile");
//                builder.setMessage(UserInfoList.indexOf());
//                builder.setNegativeButton("Close", null);
//                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent randomInt = new Intent(ListActivity.this, MainActivity.class);
//                        randomInt.putExtra("randInt", new Random().nextInt());
//                        startActivity(randomInt);
//                    }
//                });
//                AlertDialog alert = builder.create();
//                alert.show();
//            }
//        });
        DBHandler db = new DBHandler(ListActivity.this);
        UserInfoList = db.getUser();
        RecyclerView recyclerView = findViewById(R.id.rv);
        UserAdapter sAdapter = new UserAdapter(UserInfoList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(sAdapter);
    }


}