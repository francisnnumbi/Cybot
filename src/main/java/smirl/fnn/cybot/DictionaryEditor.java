package smirl.fnn.cybot;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import smirl.fnn.cybot.R;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import smirl.fnn.cybot.util.Lexic;
import smirl.fnn.cybot.util.Dictionary;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

public class DictionaryEditor extends Activity {
 ListView dict_list;
 ArrayAdapter<Lexic> adaptor;
 @Override
 protected void onCreate(Bundle savedInstanceState) {
	// TODO: Implement this method
	super.onCreate(savedInstanceState);
	setContentView(R.layout.dictionary_editor);

	dict_list = (ListView)findViewById(R.id.dictionary_editor_list_view);
	dict_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
		 // TODO: Implement this method
		Lexic lee = (Lexic) p1.getItemAtPosition(p3);
		String key = lee.getKey();
		 launchNewIntent(key, p3);
		}

 
});

	ArrayList<Lexic> li = new ArrayList<Lexic>();
	li.addAll(Bot.dictionary.getDictionary());
	adaptor = new ArrayAdapter<Lexic>(getApplicationContext(),
																		R.layout.dic_edit_list_model, R.id.dic_list_model, li);

	dict_list.setAdapter(adaptor);
 }
 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
	// TODO: Implement this method
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.menu_dic_editor, menu);
	return true;
 }

 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
	// TODO: Implement this method
	switch (item.getItemId()) {
	 case R.id.m_d_e_new:
		Toast.makeText(getApplicationContext(),
		"Having a bug", Toast.LENGTH_SHORT).show();
		//startActivity( new Intent(getApplicationContext(), NewLexic.class));
		break;
	}

	return true;
 }

private void launchNewIntent(String _key, int _index){
 Intent intent = new Intent(getApplicationContext(), NewLexic.class);
 intent.putExtra("key", _key);
 intent.putExtra("index",_index);
 startActivity(intent);
}
}
