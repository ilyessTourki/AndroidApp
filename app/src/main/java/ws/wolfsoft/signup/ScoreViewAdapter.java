package ws.wolfsoft.signup;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ws.wolfsoft.signup.models.topchallenger;

public class ScoreViewAdapter extends RecyclerView.Adapter<ScoreViewAdapter.RecyclerViewHolder> {
    private ArrayList<topchallenger>ChallengeList;
    private OnClickListener mOnNoteListenner ;

    public static  class  RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        private TextView text1 ;
        private TextView text2 ;
        private TextView text3 ;
        Button part;
        CardView crd;
        OnClickListener onClickListener ;
        public RecyclerViewHolder(@NonNull View itemView , OnClickListener onClickListener ) {
            super(itemView);
            text1=itemView.findViewById(R.id.list_item_titre);
            text2=itemView.findViewById(R.id.list_item_datefin);


            crd= itemView.findViewById(R.id.card_view);
            crd.setBackgroundColor(Color.parseColor("#50663d00"));

            this.onClickListener=onClickListener;

            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View v) {
            onClickListener.OnItemClick(getAdapterPosition());
        }
    }

    public ScoreViewAdapter(ArrayList<topchallenger>topchallengers , OnClickListener onClickListener){
        this.ChallengeList=topchallengers;
        this.mOnNoteListenner=onClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_item,parent,false);
        RecyclerViewHolder rvh = new RecyclerViewHolder(v,mOnNoteListenner) ;

        return rvh ;


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        topchallenger chall =ChallengeList.get(position);
        holder.text2.setText(chall.getId());
        holder.text1.setText(chall.getResponse());



    }

    @Override
    public int getItemCount() {
        return ChallengeList.size();
    }

    public interface OnClickListener {

        void OnItemClick(int position);
    }


}
