package sg.edu.np.mad.madpractical;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    ArrayList<User> data;

    public UserAdapter(ArrayList<User> input){
        data = input;
    }

    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.user,parent, false);
        return new UserViewHolder(item);
    }

    public void onBindViewHolder(UserViewHolder holder, int position){
        User listObjects;
        listObjects = data.get(position);
        holder.txt.setText(listObjects.getName());
        holder.txtx.setText(listObjects.getDescription());
        if (listObjects.getName().endsWith("7"))
        {
            holder.bigImg.setVisibility(View.VISIBLE);
        }
        else {
            holder.bigImg.setVisibility(View.GONE);
        }
        holder.imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.imgview.getContext());
                builder.setTitle("Profile");
                builder.setMessage(listObjects.getName());
                builder.setNegativeButton("Close", null);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                @Override
                  public void onClick(DialogInterface dialogInterface, int i) {

                    Bundle mBundle = new Bundle();
                    mBundle.putInt("id", position);
                    Intent wassup = new Intent(holder.imgview.getContext(), MainActivity.class);
                    wassup.putExtras(mBundle);
                    holder.imgview.getContext().startActivity(wassup);
                }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public int getItemCount(){
        return data.size();
    }
}
