package com.example.warriorbookstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warriorbookstore.Model.FeedbackModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class FeedAdapter extends FirebaseRecyclerAdapter<FeedbackModel,FeedAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FeedAdapter(@NonNull @NotNull FirebaseRecyclerOptions<FeedbackModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull myViewHolder myViewHolder, int i, @NonNull @NotNull FeedbackModel feedback) {

        myViewHolder.name.setText(feedback.getName());
        myViewHolder.phone.setText(feedback.getPhone());
        myViewHolder.rate.setText(feedback.getFeedrate());
        myViewHolder.message.setText(feedback.getMessage());


    }

    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item,parent,false);
        return new myViewHolder(view);
    }

    class  myViewHolder extends RecyclerView.ViewHolder{

        TextView name, phone, rate, message;

        public myViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.userName);
            phone = (TextView) itemView.findViewById(R.id.userPhone);
            rate = (TextView) itemView.findViewById(R.id.userRate);
            message = (TextView) itemView.findViewById(R.id.userMessage);

        }
    }

}
