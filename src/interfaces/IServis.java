package interfaces;

import java.util.Properties;

public interface IServis {
	
	public void start(Properties prop) throws Exception;
	
	public void stop();
}
