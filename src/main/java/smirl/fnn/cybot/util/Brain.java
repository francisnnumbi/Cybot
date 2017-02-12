package smirl.fnn.cybot.util;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import org.json.simple.JSONObject;
import smirl.fnn.cybot.Bot;
import smirl.fnn.cybot.util.cons.Constant;
import android.widget.Toast;


/**
 * this is the brain of Cybot.
 * the thinking part of the app
 */
public class Brain {
 
 
 public static void putQuestion(String quest) {
	question = cleanString(quest.trim()).toUpperCase();
	think();
 }

 private static void think() {
	if (learnWord) {
	 if (question.startsWith("##")) {
		Lexic l = new Lexic(phraseToLearn);
		l.addAlternativeStatement(phraseToLearn);
		question = cleanString(question.replace("##", "").trim());
		l.addResponse(question);
		Bot.dictionary.add(l);
		learnWord = false;
		phraseToLearn = "";
		Bot.store();
	 } else {
		learnWord = false;
		phraseToLearn = "";
	 }
	} else {
	 if (question.equalsIgnoreCase(prev_question)) {
		response = "you are repeating yourself.";
	 } else if (question.equalsIgnoreCase("BYE")) {
		response = "IT WAS NICE TALKING TO YOU USER, SEE YOU NEXT TIME!";
		Bot.ACTIVITY.finish();
	 } else {
		process();
	 }
	}
 }

 /*** analysing the input statement and select appropriate reply */
 private static void process() {
	Vector<Lexic> dic = Bot.dictionary.getDictionary();
	for (int i = 0; i < dic.size(); i++) {
	 if (dic.get(i).keyContains(question) ||
	 dic.get(i).containsAlternativeStatement(question)) {
		// analyse question in order to select and reorganize reply
		// response = getReformulatedResponseFrom(question);
		ArrayList lili = dic.get(i).getResponse();
		boolean isrep = true;
		
		while (isrep) {
		 int x = random.nextInt(lili.size());
		 response = (String)lili.get(x);
		 if (!response.equalsIgnoreCase(prev_response)) {
			prev_question = question;
			prev_response = response;
			isrep = false;
			return;
		 }
		}
	 }
	} 
	
	phraseToLearn = question;
	learnWord = true;
	response = "i do not understand you! To teach me,\n"
	+ "just write the answer starting with ##";
 }
 

 public static String getResponse() {
	return response;
 }

// remove punctuations
 private static String cleanString(String str) {
	String temp = str;
	 temp = temp.replace(Constant.delim, "");
	while (temp.contains("  ")) {
	 temp = temp.replaceAll("  ", " ");
	}
	return temp.trim();
 }
 
 private String getReformulatedResponse(String input){
	String formulatedResponse = "";
	
	
	return formulatedResponse;
 }

 
 // Variable declaration
 
 private static Random random = new Random();
 private static String question = "";
 private static String response = "";
 private static String prev_question= "";
 private static String prev_response = "";
 private static boolean learnWord = false;
 private static String phraseToLearn = "";
 
}
