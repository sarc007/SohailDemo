package com.example.demoapp01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txt;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {

            final Button add = (Button) findViewById(R.id.addbtn);
            final Button del = (Button) findViewById(R.id.delbtn);
            txt = (EditText) findViewById(R.id.editText);
            listView = (ListView)findViewById(R.id.list);
            listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
            listView.setAdapter(adapter);
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice,arrayList);
            arrayList = new ArrayList<>();

            final ListView listView = findViewById(R.id.list);

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String result= txt.getText().toString();
                        arrayList.add(result);
                        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_multiple_choice,arrayList);
                        listView.setAdapter(adapter);
                        ((EditText) findViewById(R.id.editText)).setText("");
                        adapter.notifyDataSetChanged();
                        }

                });

                                    final View.OnClickListener listener = new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                                    builder.setMessage("Do You want to delete")
                                                    .setTitle("Are You Sure???")
                                                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            SparseBooleanArray checkedItemPosition = listView.getCheckedItemPositions();
                                                            int itemcount = listView.getCount();
                                                            for (int i=itemcount-1;i>=0;i--){
                                                                if (checkedItemPosition.get(i)){
                                                                    arrayList.remove(i);
                                                                }
                                                            }
                                                            checkedItemPosition.clear();
                                                            adapter.notifyDataSetChanged();

                                                        }

                                                        })
                                                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    dialog.cancel();
                                                                }
                                                            });
                                                    AlertDialog alert = builder.create();
                                                    alert.show();
                                        }
                                    };
                                    del.setOnClickListener(listener);
        }
        catch (Exception e) {
            System.out.println("test test test");
        }
    }
}












