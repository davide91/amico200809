package boundary;

import java.io.File;

import executor.BaseExecutor;


public class DriverFileSystem {
	
	private static DriverFileSystem m_singleDFS;
	
	public static DriverFileSystem getInstance() {
		if ( m_singleDFS == null )
			m_singleDFS = new DriverFileSystem();
		return m_singleDFS;
	}

	private String m_defaultSavePath;

	public void leggi(String path, BaseExecutor executor) {
		
	}
	
	public void letto(File file) {
		
	}
	
	public void salva(File file, String path, BaseExecutor executor) {
		
	}
	
	public void scrittoFile() {
		
	}
	
	public String getDefaultSavePath()
	{
		return m_defaultSavePath;
	}
	
	public void setDefaultSavePath(String defaultPath)
	{
		m_defaultSavePath = defaultPath;
	}
	
}
