package smirl.fnn.cybot.util;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Dictionary {

 public Dictionary(JSONObject root) {
	this.root = root;
	prepareDictionnary(root);
 }

 public Vector<Lexic> getDictionary() {
	return dic;
 }

 private void prepareDictionnary(JSONObject brain) {

	Set bks = brain.keySet();
	Iterator iter0 = bks.iterator();
	while (iter0.hasNext()) {
	 String key0 = (String)iter0.next();
	 Lexic lexic = new Lexic(key0);
	 JSONArray arr0 = (JSONArray) brain.get(key0);

	 JSONObject obj0a = (JSONObject) arr0.get(0);
	 JSONArray arr1a = (JSONArray) obj0a.get("AQ");
	 for (int x = 0; x < arr1a.size(); x++) {
		lexic.addAlternativeStatement((String)arr1a.get(x));
	 }

	 JSONObject obj0b = (JSONObject) arr0.get(1);
	 JSONArray arr1b = (JSONArray) obj0b.get("ANSWER");
	 for (int x = 0; x < arr1b.size(); x++) {
		lexic.addResponse((String)arr1b.get(x));
	 }
	 dic.add(lexic);
	}
 }
 
 public void add(Lexic lexic){
	dic.add(lexic);
 }

 public JSONObject getRootBrain() {
	root.clear();
	for (int i = 0; i < dic.size(); i++) {
	 Lexic l = dic.get(i);
	 root.put(l.getKey(), l.toJSONArray());
	}
	return root;
 }
 
public Lexic getElementOfKey(String key){
	for(int i = 0; i < dic.size(); i++){
	 if(dic.get(i).getKey().equalsIgnoreCase(key)){
		return dic.get(i);
	 }
	}
	return null;
 }
 
 public Lexic getElementAt(int index){
	return dic.elementAt(index);
 }
 
 public boolean remove(Lexic lex){
	return dic.remove(lex);
 }
 
 public void remove(int index){
	dic.remove(index);
 }

 // Variables declaration
 JSONObject root;
 private Vector<Lexic> dic = new Vector<Lexic>();


}
