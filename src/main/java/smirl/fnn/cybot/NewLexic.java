package smirl.fnn.cybot;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import smirl.fnn.cybot.R;
import android.widget.EditText;
import smirl.fnn.cybot.util.Lexic;
import smirl.fnn.cybot.util.Dictionary;
import java.util.ArrayList;

public class NewLexic extends Activity {
 EditText keyET, alterET, respET;
 String key = null;
 int index = 0;
 private Lexic lexic;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
	// TODO: Implement this method
	super.onCreate(savedInstanceState);
	setContentView(R.layout.new_lexic);
	key = getIntent().getStringExtra("key");
	index = getIntent().getIntExtra("index", 0);
	keyET = (EditText)findViewById(R.id.keyET);
	alterET = (EditText)findViewById(R.id.alterET);
	respET = (EditText)findViewById(R.id.respET);
	lexic = new Lexic(key);
	keyET.setText(key);
	display();
 }

 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
	// TODO: Implement this method
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.menu_new_lexic, menu);
	return true;
 }

 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
	// TODO: Implement this method
	switch (item.getItemId()) {
	 case R.id.m_n_l_save:
		save();
		break;
	 case R.id.m_n_l_delete:
		lexic = Bot.dictionary.getElementOfKey(
		 keyET.getText().toString());

		remove(lexic);
		break;
	}

	return super.onOptionsItemSelected(item);
 }

 private void display() {
	if (Bot.dictionary.getElementAt(index)
			.getKey().equalsIgnoreCase(key)) {
	 lexic = Bot.dictionary.getElementAt(index);
	} else {
	 lexic = Bot.dictionary.getElementOfKey(key);
	}
	alterET.setText(formatted(lexic.getAlternativeStatements()));
	respET.setText(formatted(lexic.getResponse()));
 }

 private String formatted(ArrayList<String> array) {
	String s = "##" + array.get(0);

	for (int i = 1; i < array.size(); i++) {
	 s += System.lineSeparator() + "##" + array.get(i);
	}
	return s;
 }

 private ArrayList<String> formatBack(String txt) {
	ArrayList<String> al = new ArrayList<String>();
	txt = txt.replaceFirst("##", "");
	String[] st = txt.toUpperCase().split("\\r?\\n");
	for (String s : st) {
	 al.add(s.replaceFirst("##", ""));
	}
	return al;
 }

 private void save() {
	key = keyET.getText().toString().toUpperCase();
	if (lexic.getKey().equalsIgnoreCase(key)) {
	 lexic.replaceAlternativeStatements(
		formatBack(alterET.getText().toString()));
	 lexic.replaceResponses(
		formatBack(respET.getText().toString()));
	 Bot.dictionary.add(lexic);
	} else {
	 lexic = new Lexic(key);
	 lexic.replaceAlternativeStatements(
		formatBack(alterET.getText().toString()));
	 lexic.replaceResponses(
		formatBack(respET.getText().toString()));
	 Bot.dictionary.add(lexic);
	}
	Bot.store();
 }

 private void remove(Lexic lex) {
	Bot.dictionary.remove(lex);
	Bot.store();
 }
}
