package controladores;

public enum UserSingleton {
	SINGLETON;

	private static String nombre;
	private static String domicilio;
	private static String edad;
	private static String sangre;
	private static String numEmer;

	public static String getNombre() {
		return nombre;
	}

	public static void setNombre(String nombre) {
		UserSingleton.nombre = nombre;
	}

	public static String getDomicilio() {
		return domicilio;
	}

	public static void setDomicilio(String domicilio) {
		UserSingleton.domicilio = domicilio;
	}

	public static String getEdad() {
		return edad;
	}

	public static void setEdad(String edad) {
		UserSingleton.edad = edad;
	}

	public static String getSangre() {
		return sangre;
	}

	public static void setSangre(String sangre) {
		UserSingleton.sangre = sangre;
	}

	public static String getNumEmer() {
		return numEmer;
	}

	public static void setNumEmer(String numEmer) {
		UserSingleton.numEmer = numEmer;
	}

	// private static SharedPreferences app_preferences;
	// private static SharedPreferences.Editor editor;
	//
	// public static void iniciarPreferencias(Context context){
	// app_preferences = PreferenceManager.getDefaultSharedPreferences(context);
	// }
	//
	// public static String getNombre() {
	// return app_preferences.getString("nombre", "");
	// }
	//
	// public static void setNombre(String nombre) {
	// editor = app_preferences.edit();
	// UserSingleton.nombre = nombre;
	// editor.putString("nombre", UserSingleton.nombre);
	// editor.commit();
	// }
	//
	// public static String getDomicilio() {
	// return app_preferences.getString("domicilio", "");
	// }
	//
	// public static void setDomicilio(String domicilio) {
	// editor = app_preferences.edit();
	// UserSingleton.domicilio = domicilio;
	// editor.putString("domicilio", UserSingleton.domicilio);
	// editor.commit();
	// }
	//
	// public static String getEdad() {
	// return app_preferences.getString("edad", "");
	// }
	//
	// public static void setEdad(String edad) {
	// editor = app_preferences.edit();
	// UserSingleton.edad = edad;
	// editor.putString("edad", UserSingleton.edad);
	// editor.commit();
	// }
	//
	// public static String getSangre() {
	// return app_preferences.getString("sangre", "");
	// }
	//
	// public static void setSangre(String sangre) {
	// editor = app_preferences.edit();
	// UserSingleton.sangre = sangre;
	// editor.putString("sangre", UserSingleton.sangre);
	// editor.commit();
	// }
	//
	// public static String getNumEmer() {
	// return app_preferences.getString("number", "");
	// }
	//
	// public static void setNumEmer(String numEmer) {
	// editor = app_preferences.edit();
	// UserSingleton.numEmer = numEmer;
	// editor.putString("number", UserSingleton.numEmer);
	// editor.commit();
	// }

}
