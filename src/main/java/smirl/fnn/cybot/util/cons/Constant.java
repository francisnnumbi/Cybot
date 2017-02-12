package smirl.fnn.cybot.util.cons;

import java.util.*;
import android.os.*;
import java.io.*;

public interface Constant
{
	final static String delim = "[?!.;,]";
	final static int maxInput = 1;
	final static int maxResp = 6;
	final static File extStorage = Environment.getExternalStorageDirectory();
	final static File dir = new File(extStorage, "Android/data/smirl.fnn.cybot");
	final static String idx0 = " :: ";
	final static String idx1 = " \\| ";

}
