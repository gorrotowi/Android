package controladores;

import java.util.regex.Pattern;

import android.app.Activity;
import android.widget.EditText;

public enum Validaciones {
	INSTANCE;
	
	private final static Pattern EMAIL_ADDRESS_PATTERN = Pattern
			.compile(/*"^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
					+ "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
					+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
					+ "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
					+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
					+ "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"*/
					"[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}");
	
	public static boolean isMail(String email) {
		return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
	}

	public static boolean errorEditCero(Activity activity, EditText ed, int errStr) {
		String cadena = ed.getText().toString();
		if (cadena.length()==0) {
			ed.setError(activity.getString(errStr));
			return true;
		}else{
			ed.setError(null);
			return false;
		}
	}
	
	
	
}
