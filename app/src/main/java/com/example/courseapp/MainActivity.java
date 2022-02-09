package com.example.courseapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5;
    String getCourseTitle,getDescription,getDuration,getVenue,getDate;
    AppCompatButton b1;
    String apiUrl="http://mountzioncollege.herokuapp.com/addcourse";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText) findViewById(R.id.coursetitle);
        ed2=(EditText) findViewById(R.id.description);
        ed3=(EditText) findViewById(R.id.duration);
        ed4=(EditText) findViewById(R.id.venue);
        ed5=(EditText) findViewById(R.id.date);
        b1=(AppCompatButton) findViewById(R.id.sub);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCourseTitle=ed1.getText().toString();
                getDescription=ed2.getText().toString();
                getDuration=ed3.getText().toString();
                getVenue=ed4.getText().toString();
                getDate=ed5.getText().toString();

                StringRequest sr=new StringRequest(Request.Method.POST, apiUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                                ed1.setText("");
                                ed2.setText("");
                                ed3.setText("");
                                ed4.setText("");
                                ed5.setText("");

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String>hm =new HashMap<>();
                        hm.put("courseTitle",getCourseTitle);
                        hm.put("courseDescription",getDescription);
                        hm.put("courseDuration",getDuration );
                        hm.put("courseVenue",getVenue);
                        hm.put("courseDate",getDate);
                        return hm;
                    }
                };
                RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
                rq.add(sr);
            }
        });
    }
}