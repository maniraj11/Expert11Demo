/**
 * Created by Maniraj Agarwal on 8/6/20.
 */
package com.project.expert11demo.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.project.expert11demo.Model.Book;

@Database(entities = {Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract bookDao bookDao();
}