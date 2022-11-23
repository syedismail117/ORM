package com.example.ormnew;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.ArrayList;

public class DataBaseController extends OrmLiteBaseActivity {

 public static DataBaseController  controller;
    public DataBaseHelper helper;
    public ArrayList<itemsInfo> dataList = new ArrayList<>();
    public itemsInfo data;


    //create instance

    public static DataBaseController getInstance() {
        if (controller == null) {
            controller = new DataBaseController();
        }
        return controller;
    }

    public void fillContext(Context context1) {

        Log.e("DBStatus", "Fill Context Called");
        helper = new DataBaseHelper ( context1 );
    }

    //insert the userdata into user table
    public boolean insertUserData(itemsInfo userdata) {
        try {
            helper.getUserDao().create(userdata);
            fetchUserData();
            Log.e("fetch", "" + dataList);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Fetching all the user data
    public ArrayList<itemsInfo> fetchUserData() {
        dataList = null;
        dataList = new ArrayList<>();

        try {
            dataList = (ArrayList) helper.getUserDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.e("fetching", "user data fetched successfully" + dataList.size());

        return dataList;
    }

    //updating the dataList
    public void updateUserData(itemsInfo user) {
        try {
            UpdateBuilder<itemsInfo, Integer> updateBuilder = helper.getUserDao().updateBuilder();
            updateBuilder.updateColumnValue("Title", user.getTitle ());
            updateBuilder.updateColumnValue("description", user.getDescription ());
            updateBuilder.where().eq("Title", user.getTitle ());
            updateBuilder.update();

            Log.e("update data", "updated the data successfully");
            Log.e("new user id", "" + user.getTitle () + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Deleting all users in database
    public void deleteUserData(ArrayList<itemsInfo> user) {
        try {
            helper.getUserDao().delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //Deleting single users in database
    public void deleteSingleUserData(itemsInfo user) {
        try {
            helper.getUserDao().delete(user);
            Log.e("delete data", "delete the data successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
