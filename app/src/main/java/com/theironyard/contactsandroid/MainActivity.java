package com.theironyard.contactsandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {

    ArrayAdapter<String> items;

    ListView listView;
    EditText name;
    EditText phone;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        addButton =  (Button) findViewById(R.id.addButton);

        name.setOnKeyListener(new View.OnKeyListener() { //adds contact when user hits ENTER
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    addContactToList();
                }
                return false;
            }
        });

        phone.setOnKeyListener(new View.OnKeyListener() { //adds contact when user hits ENTER
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    addContactToList();
                }
                return false;
            }
        });

        addButton.setOnClickListener(this);
        listView.setOnItemLongClickListener(this);

        items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(items);

        phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher()); //formats phone # with parenthesis & dashes
    }

    @Override
    public void onClick(View v) {
        addContactToList();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String itemToRemove = items.getItem(position);
        items.remove(itemToRemove);
        return true;
    }

    private void addContactToList() {
        String text = name.getText().toString();
        String text2 = phone.getText().toString();
        items.add(text + " (" + text2 + ")");

        name.setText("");
        phone.setText("");
    }
}
