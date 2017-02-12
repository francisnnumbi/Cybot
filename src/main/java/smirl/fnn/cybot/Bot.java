package smirl.fnn.cybot;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import smirl.fnn.cybot.util.Brain;
import smirl.fnn.cybot.util.Dictionary;
import smirl.fnn.cybot.util.Lexic;
import smirl.fnn.cybot.util.cons.Constant;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.ImageButton;


public class Bot extends Activity implements Constant, OnClickListener, OnKeyListener {

 @Override
 protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.bot);
	ACTIVITY = this;
	CONTEXT = this.getApplicationContext();
	list_display = (ListView)findViewById(R.id.list_display);
	bot_et = (EditText)findViewById(R.id.bot_et);
	talk_btn = (ImageButton)findViewById(R.id.talk_btn);
	talk_btn.setOnClickListener(this);
	bot_et.setOnKeyListener(this);
	if (!dir.exists()) {
	 if (dir.mkdirs()) {
		Toast.makeText(this, "dir successfully",
									 Toast.LENGTH_SHORT).show();
	 }
	}
	if (!u.exists()) {
	 try {
		if (u.createNewFile()) {
		 Toast.makeText(this, "empty brain db file created successfully",
										Toast.LENGTH_SHORT).show();
		}
	 }
	 catch (Exception e) {}
	}
	retrieve();
	adapter = new
	 ArrayAdapter<String>(getApplicationContext(),
												R.layout.list_row_layout_even,
												R.id.even_text, msgs);								
	adapter.add("this is me");
	list_display.setAdapter(adapter);

 }

 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
	// TODO: Implement this method
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.menu_bot_main, menu);
	return true;
 }

 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
	// TODO: Implement this method
	switch(item.getItemId()){
	 case R.id.m_b_m_dicEd:
	 startActivity(new Intent(getApplicationContext(), DictionaryEditor.class));
	break;
	 }
	
	return super.onOptionsItemSelected(item);
 }
 
 
 @Override
 public boolean onKey(View p1, int p2, KeyEvent p3) {
	// TODO: Implement this method
	if ((p3.getAction() == KeyEvent.ACTION_DOWN) &&
			(p2 == KeyEvent.KEYCODE_ENTER)) {
	 converse();
	 return true;
	}
	return false;
 }

 @Override
 public void onClick(View p1) {
	// TODO: Implement this method
	switch (p1.getId()) {
	 case R.id.talk_btn:

		converse();
		break;
	}
 }

 private void converse() {
	adapter.add(bot_et.getText().toString());
	Brain.putQuestion(bot_et.getText().toString());
	adapter.add(Brain.getResponse());
	bot_et.setText("");
	
 }

 public static void botInitiateConversation() {
	Vector<Lexic> vo = Bot.dictionary.getDictionary();

	int v = random.nextInt(vo.size());
	String txt = vo.get(v).getKey();
	adapter.add(txt);
	Brain.putQuestion(txt);
	//adapter.add(Brain.getResponse());
	bot_et.setText("");
 }

 public static void store() {
	FileWriter w;
	try {
	 w = new FileWriter(u);
	 w.write(dictionary.getRootBrain().toJSONString());
	 w.flush();
	 Toast.makeText(CONTEXT, "saved successfully",
									Toast.LENGTH_SHORT).show();
	}
	catch (IOException ioe) {
	 Toast.makeText(CONTEXT, "saving failed",
									Toast.LENGTH_SHORT).show();

	}

 }

 @Override
 protected void onPause() {
	// TODO: Implement this method
	super.onPause();

 }

 private void retrieve() {
	try {
	 Object ob = parser.parse(new FileReader(u));
	 dictionary = new Dictionary((JSONObject)ob);
	 
	}
	catch (Exception e) {
	 Toast.makeText(Bot.ACTIVITY.getApplicationContext(),
									"What is this?", Toast.LENGTH_SHORT).show();

	}
 }

 // Variables declaration
 public static Activity ACTIVITY;
 public static Context CONTEXT;
 ListView list_display;
 static EditText bot_et;
 ImageButton talk_btn;
 // the address of the file that stores the conversation
 private static String c_file = "/db.json";
 private static File u = new File(dir + c_file);
 ArrayList<String> msgs = new ArrayList<String>();
 static ArrayAdapter<String> adapter;
 JSONParser parser = new JSONParser();
 public static Dictionary dictionary;
 private static Random random = new Random();
}
