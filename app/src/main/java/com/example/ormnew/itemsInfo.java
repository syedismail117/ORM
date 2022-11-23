package com.example.ormnew;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//create a table

@DatabaseTable

public class itemsInfo {

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    String Title;

    @DatabaseField
    String description;


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


