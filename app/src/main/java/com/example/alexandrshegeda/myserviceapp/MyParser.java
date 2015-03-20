//package com.example.alexandrshegeda.myserviceapp;
//
//import android.util.JsonReader;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * Created by Alexandr Shegeda on 04.03.2015.
// */
//public class MyParser
//{
//    String path = "D:\\myjson.txt";
//
//    List<SomeModel> list;
//    SomeModel model;
//
//    public void testJSONPars(){
//        list = new LinkedList<>();
//
//        String line = "";
//        StringBuilder json = new StringBuilder();
//        BufferedReader br;
//        List<SomeModel> modelList;
//
//        try {
//            JsonReader reader = new JsonReader(new FileReader(new File(path)));
//           reader.beginArray();
//            while (reader.hasNext())
//            {
//
//            }
//            modelList = getModelList(reader);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private List<SomeModel> getModelList(JsonReader reader)
//    {
//        List<SomeModel> modelList = new ArrayList<>();
//
//        try {
//            reader.beginObject();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////        JSONArray jsonArray = (JSONArray) obj;
////        for (Object item : jsonArray) {
////            JSONObject js = (JSONObject) item;
////            model = new SomeModel();
////
////            model._id = (String) js.get("_id");
////            model.phone = (String) js.get("phone");
////            model.index = (long) js.get("index");
////            model.favoriteFruit = (String) js.get("favoriteFruit");
////            model.greeting = (String) js.get("greeting");
////            model.about = (String) js.get("about");
////            model.guid = (String) js.get("guid");
////            model.isActive = (boolean) js.get("isActive");
////            model.picture = (String) js.get("picture");
////            model.balance = (String) js.get("balance");
////            model.address = (String) js.get("address");
////            model.eyeColor = (String) js.get("eyeColor");
////            model.email = (String) js.get("email");
////            model.age = (long) js.get("age");
////            model.name = (String) js.get("name");
////            model.company = (String) js.get("company");
////            model.gender = (String) js.get("gender");
////            model.longitude = (double) js.get("longitude");
////            model.latitude = (double) js.get("latitude");
////
////            JSONArray arr = (JSONArray) js.get("tags");
////            List<String> tags = new ArrayList<>();
////
////            for(Object tg : arr)
////            {
////                tags.add(tg.toString());
////            }
////
////            model.tags = tags;
////
////            List<Friend> friends = new LinkedList<>();
////            arr = (JSONArray) js.get("friends");
////            Friend friend;
////            for (Object f : arr) {
////                JSONObject jfriend = (JSONObject) f;
////                friend = new Friend();
////                friend.id = (long) jfriend.get("id");
////                friend.name = (String) jfriend.get("name");
////                friends.add(friend);
////            }
//
////            model.friends = friends;
////
////            list.add(model);
////        }
//
////        return modelList;
////    }
//
//
//}
