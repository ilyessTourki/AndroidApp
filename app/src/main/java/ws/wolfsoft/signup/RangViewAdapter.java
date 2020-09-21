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


public class RangViewAdapter extends RecyclerView.Adapter<RangViewAdapter.RecyclerViewHolder> {
    private ArrayList<Integer> ChallengeList = new ArrayList<>();
    private OnClickListener mOnNoteListenner ;

    public static  class  RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        private TextView text1 ;


        CardView crd;
        OnClickListener onClickListener ;
        public RecyclerViewHolder(@NonNull View itemView , OnClickListener onClickListener ) {
            super(itemView);
            text1=itemView.findViewById(R.id.list_item_number);



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

    public RangViewAdapter(ArrayList<Integer> topchallengers , OnClickListener onClickListener){
        this.ChallengeList=topchallengers;
        this.mOnNoteListenner=onClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rang_item,parent,false);
        RecyclerViewHolder rvh = new RecyclerViewHolder(v,mOnNoteListenner) ;

        return rvh ;


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        //topchallenger chall =ChallengeList.get(position);
       // int i=ChallengeList.get(position);
        holder.text1.setText(String.valueOf(ChallengeList.get(position)));

    }

    @Override
    public int getItemCount() {
        return ChallengeList.size();
    }

    public interface OnClickListener {

        void OnItemClick(int position);
    }


}
