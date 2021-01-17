package com.example.recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    ArrayList<Person> items = new ArrayList<>();
    OnPersonItemClickedListener listener;

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 뷰홀더가 만들어 진것을 재사용
        Person item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() { //전에 들어있는 객체가 몇개인지
        return items.size();
    }

    public void addItem(Person item){
        items.add(item);
    }

    public void setItems(ArrayList<Person> items){
        this.items = items;
    }

    public Person getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, Person item){
        items.set(position,item);
    }

    public void setOnItemClickedListener(OnPersonItemClickedListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //뷰홀더 생성지점에 호출되는 메서드

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //  LayoutInflater 객체 참조
        View itemView = inflater.inflate(R.layout.person_item,parent,false);
        return new ViewHolder(itemView,listener);
    }

//--------------------------------------------------------------------------------------------//

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1, textView2;

        public ViewHolder(@NonNull View itemView, final OnPersonItemClickedListener listener) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition(); // 몇번째 뷰인지 값으로 받음
                    //listener로 정보를 받는다.
                    if(listener != null){
                        listener.onItemClicked(ViewHolder.this,v,position);
                    }
                }
            });
        }

        public void setItem(Person item){
            textView1.setText(item.getName());
            textView2.setText(item.getPhone());
        }
    }
}

