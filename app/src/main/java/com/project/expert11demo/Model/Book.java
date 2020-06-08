package com.project.expert11demo.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(indices = @Index(value = {"id"}, unique = true))
public class Book implements Serializable {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int ID;
    @ColumnInfo(name = "title")
    private String Title;
    @ColumnInfo(name = "description")
    private String Description;
    @ColumnInfo(name = "pageCount")
    private int PageCount;
    @ColumnInfo(name = "excerpt")
    private String Excerpt;
    @ColumnInfo(name = "publishDate")
    private String PublishDate;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPageCount() {
        return PageCount;
    }

    public void setPageCount(int pageCount) {
        PageCount = pageCount;
    }

    public String getExcerpt() {
        return Excerpt;
    }

    public void setExcerpt(String excerpt) {
        Excerpt = excerpt;
    }

    public String getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(String publishDate) {
        PublishDate = publishDate;
    }


}
