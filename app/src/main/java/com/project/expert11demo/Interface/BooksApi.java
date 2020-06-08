/**
 * Created by Maniraj Agarwal on 8/6/20.
 */
package com.project.expert11demo.Interface;

import com.project.expert11demo.Model.Book;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BooksApi {
    @GET("api/Books")
    Call<ArrayList<Book>> getBooksList();
}
