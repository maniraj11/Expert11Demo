/**
 * Created by Maniraj Agarwal on 8/6/20.
 */
package com.project.expert11demo.Ui.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.project.expert11demo.Model.Book;
import com.project.expert11demo.R;

public class DetailFragment extends Fragment {

    View view;
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    Book book;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detail_fragment,container,false);
        book = (Book) getArguments().getSerializable("bookDetail");
        initialiseViews(book);
        return view;
    }

    private void initialiseViews(Book book) {
        tv1 = (TextView) view.findViewById(R.id.textview1);
        tv1.setText(String.format("%s%s", getString(R.string.bookId), String.valueOf(book.getID())));
        tv2 = (TextView) view.findViewById(R.id.textview2);
        tv2.setText(String.format("%s%s", getString(R.string.bookName), book.getTitle()));
        tv3 = (TextView) view.findViewById(R.id.textview3);
        tv3.setText(String.format("%s%s", getString(R.string.bookPublishDate), book.getPublishDate()));
        tv4 = (TextView) view.findViewById(R.id.textview4);
        tv4.setText(String.format("%s%s", getString(R.string.bookExcerpt), book.getExcerpt()));
        tv5 = (TextView) view.findViewById(R.id.textview5);
        tv5.setText(String.format("%s%s", getString(R.string.bookDesc), book.getDescription()));
        tv6 = (TextView) view.findViewById(R.id.textview6);
        tv6.setText(String.format("%s%s", getString(R.string.numberOfPages), String.valueOf(book.getPageCount())));
    }

}
