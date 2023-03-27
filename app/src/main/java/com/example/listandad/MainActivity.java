package com.example.listandad;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> users = new ArrayList<String>();
    ArrayList<String> selectedUsers = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView usersList;
    private int point = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Collections.addAll(users, "Math\n"+point, "Physics\n"+point, "Chemistry\n"+point );
        usersList = findViewById(R.id.usersList);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, users);
        usersList.setAdapter(adapter);

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                String user = adapter.getItem(position);
                if(usersList.isItemChecked(position))
                    selectedUsers.add(user);
                else
                    selectedUsers.remove(user);
            }
        });
    }
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @SuppressLint("SetTextI18n")
    public void add(View view){

        EditText userName = findViewById(R.id.subject);
        String user = userName.getText().toString();
        Scanner scanner = new Scanner(user);
        if(!scanner.hasNextInt()) {
            String line = scanner.nextLine();
            boolean result = user.matches("[a-z]+");
            if(result){
                if(!user.isEmpty()){
                        adapter.add(user+"\n"+point);
                        userName.setText("");
                        adapter.notifyDataSetChanged();

                }
            }
        }
    }
    public static String removeAllDigit(String i)
    {

        char[] charArray = i.toCharArray();
        String result = "";
        for (int f = 0; f < charArray.length; f++) {
            if (!Character.isDigit(charArray[f])) {
                result = result + charArray[f];
            }
        }
        return result;
    }
    public void editPoint(View view){
        EditText editPoinT = findViewById(R.id.subject);
        String uPoint = editPoinT.getText().toString();
        editPoinT.setText(uPoint);
        for(int i=0; i< selectedUsers.size();i++){
            System.out.println(selectedUsers.set(i, users.get(i)));
            System.out.println("\n"+removeAllDigit(users.get(i))+uPoint+"\n");
            boolean result = uPoint.matches("[0-9]+");
            if(result){
                if(selectedUsers.set(i, users.get(i))== users.get(i)) {
                adapter.add(removeAllDigit(users.get(i)) + uPoint);
                adapter.notifyDataSetChanged();
                adapter.remove(selectedUsers.get(i));
                }
            }
        }
        usersList.clearChoices();
        adapter.notifyDataSetChanged();
        usersList.clearChoices();
        selectedUsers.clear();
    }
    public void remove(View view){
        for(int i=0; i< selectedUsers.size();i++){
            adapter.remove(selectedUsers.get(i));
        }
        usersList.clearChoices();
        selectedUsers.clear();
        adapter.notifyDataSetChanged();
    }
}