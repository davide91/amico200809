package calculator;

import java.io.File;

import store.POJO.Condominio;

public class FormatoAmICo {

	public static boolean  fileInFormatoAmICo(File file) {
		return true;
	}
	
	public static Condominio daFileACondominio(File file) {
		return new Condominio();
	}
	
	public static File daCondominioAFile(Condominio condominio) {
		return new File(new String());
	}
}
