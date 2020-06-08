/**
 * Created by Maniraj Agarwal on 8/6/20.
 */
package com.project.expert11demo.Ui.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.expert11demo.Adapter.listingRecyclerAdapter;
import com.project.expert11demo.Model.Book;
import com.project.expert11demo.R;

import java.util.ArrayList;

public class ListingFragment extends Fragment {

    View view;
    ArrayList<Book> bookList;
    private listingRecyclerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.listing_fragment,container,false);
        bookList = (ArrayList<Book>) getArguments().getSerializable("listOfBook");
        RecyclerView recyclerView =(RecyclerView)view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mAdapter = new listingRecyclerAdapter(bookList);
        recyclerView.setAdapter(mAdapter);
        return view;
    }
}
