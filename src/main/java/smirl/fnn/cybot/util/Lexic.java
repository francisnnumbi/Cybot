package smirl.fnn.cybot.util;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Lexic
{
 private String key;
 private ArrayList<String> asList = new ArrayList<String>();
 private ArrayList<String> resList = new ArrayList<String>();
 
 
 public Lexic(String key) {
	this.key = key;
 }

 @Override
 public String toString() {
	// TODO: Implement this method
	return key;
 }
 
 
 
 public String getKey(){
	return key;
 }
 
 public boolean keyContains(String str){
	return key.contains(str);
 }
 
 public void addAlternativeStatement(String as){
	if(!asList.contains(as))asList.add(as); 
 }
 
 public void replaceAlternativeStatements(ArrayList<String> list){
	asList.clear();
	asList = list;
 }
 public void replaceResponses(ArrayList<String> list){
	resList.clear();
	resList = list;
 }
 
 
 public void addResponse(String resp){
	if(!resList.contains(resp))resList.add(resp);
 }
 
 public String getAlternativeStatementAt(int index){
	return asList.get(index);
 }
 
 public ArrayList<String> getAlternativeStatements(){
	return asList;
 }
 
 public String getResponseAt(int index){
	return resList.get(index);
 }
 
 public ArrayList<String> getResponse(){
	return resList;
 }
 
 public boolean containsAlternativeStatement(String as){
	return asList.contains(as);
 }
 
 public boolean containsResponse(String resp){
	return resList.contains(resp);
 }
 
 public int getAlternativeStatementsSize(){
	return asList.size();
 }
 
 public int getResponsesSize(){
	return resList.size();
 }
 
 public JSONArray toJSONArray(){
	JSONArray a = new JSONArray();
	a.addAll(asList);
	JSONObject aa = new JSONObject();
	aa.put("AQ", a);
	
	JSONArray b = new JSONArray();
	b.addAll(resList);
	JSONObject bb = new JSONObject();
	bb.put("ANSWER", b);
	
	JSONArray x = new JSONArray();
	x.add(0, aa);
	x.add(1, bb);
	return x;
 }
 
}
