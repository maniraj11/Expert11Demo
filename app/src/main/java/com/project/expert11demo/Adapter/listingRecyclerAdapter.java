/**
 * Created by Maniraj Agarwal on 8/6/20.
 */
package com.project.expert11demo.Adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.project.expert11demo.Model.Book;
import com.project.expert11demo.R;
import com.project.expert11demo.Ui.Activity.MainActivity;
import com.project.expert11demo.Ui.CommonAndroid.CommonFunction;
import com.project.expert11demo.Ui.Fragment.DetailFragment;

import java.util.ArrayList;

public class listingRecyclerAdapter extends RecyclerView.Adapter<listingRecyclerAdapter.MyViewHolder> {

    private ArrayList<Book> bookList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, publishDate;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.bookName);
            publishDate = (TextView) view.findViewById(R.id.bookPublishDate);
        }
    }


    public listingRecyclerAdapter(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_listing_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Book book = bookList.get(position);
        holder.title.setText(book.getTitle());
        holder.publishDate.setText(book.getPublishDate());
        final Book clickedBook = book;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("bookDetail", clickedBook);
                Fragment detailFragmentFragment = new DetailFragment();
                detailFragmentFragment.setArguments(bundle);
                FragmentManager fragmentManager = ((AppCompatActivity) v.getContext()).getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frameLayout, detailFragmentFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
