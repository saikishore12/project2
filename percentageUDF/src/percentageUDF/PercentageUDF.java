package percentageUDF;

import java.io.IOException;
import org.apache.pig.FilterFunc;
import org.apache.pig.data.Tuple;
public class PercentageUDF extends FilterFunc {
	//passing the objective and performance and reading them as tuple
	public Boolean exec(Tuple input) throws IOException {
		try {
			 String District=(String)Input.get(0);
			int objective= (int)input.get(1);
			int performance= (int)input.get(2);
			
			int temp=(objective-performance)/objective;
			int percentage=temp*100;
			if (percentage==80) {
				return District;
			}
			else {
				return null;
			}
		}
		catch (Exception e) {
			throw new IOException(e);
		}
	}	
}
