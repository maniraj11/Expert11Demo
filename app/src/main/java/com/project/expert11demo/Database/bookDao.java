/**
 * Created by Maniraj Agarwal on 8/6/20.
 */
package com.project.expert11demo.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.project.expert11demo.Model.Book;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface bookDao {

    @Query("SELECT * FROM Book")
    List<Book> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMultipleListRecord(ArrayList<Book> bookList);

    @Delete
    void delete(Book book);

    @Update
    void update(Book book);
}
