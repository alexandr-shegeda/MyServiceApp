package com.example.alexandrshegeda.myserviceapp;

import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexandr Shegeda on 04.03.2015.
 */
public class MyParser {
    String path = "D:\\myjson.txt";

    List<SomeModel> list;
    SomeModel model;

    public List<SomeModel> testJSONPars() throws IOException {
        list = new LinkedList<>();

        String line = "";
        StringBuilder json = new StringBuilder();
        BufferedReader br;
        List<SomeModel> modelList;

        JsonReader reader = new JsonReader(new FileReader(new File(path)));
        try {
            return getModelList(reader);
        } finally {
            reader.close();
        }
    }

    private List<SomeModel> getModelList(JsonReader reader) {
        List<SomeModel> modelList = new ArrayList<>();
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                modelList.add(getModel(reader));
            }
            reader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelList;
    }

    private SomeModel getModel(JsonReader reader) {
        SomeModel model = new SomeModel();

        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String val = reader.nextName();
                if (val.equals("tags")) {
                    reader.beginArray();
                    List<String> tags = new LinkedList<>();
                    while (reader.hasNext()) {
                        tags.add(reader.nextString());
                    }
                    reader.endArray();
                    model.tags = tags;
                } else if (val.equals("phone")) {
                    model.phone = reader.nextString();
                } else if (val.equals("index")) {
                    model.index = reader.nextLong();
                } else if (val.equals("favoriteFruit")) {
                    model.favoriteFruit = reader.nextString();
                } else if (val.equals("greeting")) {
                    model.greeting = reader.nextString();
                } else if (val.equals("about")) {
                    model.about = reader.nextString();
                } else if (val.equals("guid")) {
                    model.guid = reader.nextString();
                } else if (val.equals("isActive")) {
                    model.isActive = reader.nextBoolean();
                } else if (val.equals("picture")) {
                    model.picture = reader.nextString();
                } else if (val.equals("balance")) {
                    model.balance = reader.nextString();
                }
                if (val.equals("friends")) {
                    List<Friend> friends = new LinkedList<>();
                    reader.beginArray();
                    while (reader.hasNext()) {
                        friends.add(getFriends(reader));
                    }
                    reader.endArray();
                } else if (val.equals("_id")) {
                    model._id = reader.nextString();
                } else if (val.equals("address")) {
                    model.address = reader.nextString();
                } else if (val.equals("eyeColor")) {
                    model.eyeColor = reader.nextString();
                } else if (val.equals("email")) {
                    model.email = reader.nextString();
                } else if (val.equals("registered")) {
                    model.registered = reader.nextString();
                } else if (val.equals("age")) {
                    model.age = reader.nextLong();
                } else if (val.equals("name")) {
                    model.name = reader.nextString();
                } else if (val.equals("company")) {
                    model.company = reader.nextString();
                } else if (val.equals("gender")) {
                    model.gender = reader.nextString();
                } else if (val.equals("longitude")) {
                    model.longitude = reader.nextDouble();
                } else if (val.equals("latitude")) {
                    model.latitude = reader.nextDouble();
                } else {
                    reader.skipValue();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return model;
    }

    private Friend getFriends(JsonReader reader) {
        Friend friend = new Friend();
        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String val = reader.nextName();
                if (val.equals("id")) {
                    friend.id = reader.nextInt();
                }else if (val.equals("name"))
                {
                    friend.name = reader.nextString();
                }else {
                    reader.skipValue();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return friend;
    }

}
//        model._id = (String) js.get("_id");
//        model.phone = (String) js.get("phone");
//        model.index = (long) js.get("index");
//        model.favoriteFruit = (String) js.get("favoriteFruit");
//        model.greeting = (String) js.get("greeting");
//        model.about = (String) js.get("about");
//        model.guid = (String) js.get("guid");
//        model.isActive = (boolean) js.get("isActive");
//        model.picture = (String) js.get("picture");
//        model.balance = (String) js.get("balance");
//        model.address = (String) js.get("address");
//        model.eyeColor = (String) js.get("eyeColor");
//        model.email = (String) js.get("email");
//        model.age = (long) js.get("age");
//        model.name = (String) js.get("name");
//        model.company = (String) js.get("company");
//        model.gender = (String) js.get("gender");
//        model.longitude = (double) js.get("longitude");
//        model.latitude = (double) js.get("latitude");
//
//        JSONArray arr = (JSONArray) js.get("tags");
//        List<String> tags = new ArrayList<>();
//
//        for (Object tg : arr) {
//        tags.add(tg.toString());
//        }
//
//        model.tags = tags;
//
//        List<Friend> friends = new LinkedList<>();
//        arr = (JSONArray) js.get("friends");
//        Friend friend;
//        for (Object f : arr) {
//        JSONObject jfriend = (JSONObject) f;
//        friend = new Friend();
//        friend.id = (long) jfriend.get("id");
//        friend.name = (String) jfriend.get("name");
//        friends.add(friend);
//        }
//
//        model.friends = friends;
//
//        list.add(model);
//        }
//
//        return modelList;