package com.containerlab.labarep5;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@RestController
@RequestMapping("/")
public class Controller {

    private MongoClient client = null;
    private String url = "54.226.144.57:27017";
    private MongoDatabase db = null;
    private MongoCollection<Document> collec;

    public void createConnection(){
        
        client = new MongoClient(url);
        db = client.getDatabase("admin");
        collec = db.getCollection("database1");

    }


    @GetMapping()
    public ResponseEntity<?> getLogs(){
        createConnection();
        ArrayList<String> logs = new ArrayList<>();
        FindIterable<Document> rawLogs = collec.find();
        for (Document document : rawLogs) {
            logs.add(document.toJson());
        }
        if(logs.size() > 10){
            logs = (ArrayList<String>) logs.subList(logs.size()-11, logs.size()-1);
        }
        return  new ResponseEntity<>(new Gson().toJson(logs), HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<?> createLog(@RequestBody String newLog){
        createConnection();
        Document d = new Document();
        Date fecha = new Date();
        d.put("log", newLog);
        d.put("fecha", new SimpleDateFormat("dd/MM/yy").format(fecha));
        collec.insertOne(d);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
