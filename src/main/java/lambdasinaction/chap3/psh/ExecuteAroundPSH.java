package lambdasinaction.chap3.psh;

import lambdasinaction.chap3.ExecuteAround;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAroundPSH {

	public static void main(String ...args) throws IOException{

		String result = processFileLimited();
		//System.out.println(result);

		String s = processFile((BufferedReader bufferReader) -> bufferReader.readLine());
		String s2 = processFile((BufferedReader bufferReader) -> bufferReader.readLine()+bufferReader.readLine());
		System.out.println(s2);
	}

	public static String processFileLimited() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(getFiilePath()));
		return bufferedReader.readLine();
	}

	//返回 bufferReader 预处理
	public static String processFile(BufferedReaderProcessor p) throws IOException{
		try (BufferedReader br =
					 new BufferedReader(new FileReader(getFiilePath()))) {
			return p.process(br);
		}
	}

	//行为λ接口
	@FunctionalInterface
	public interface BufferedReaderProcessor{
		public String process(BufferedReader bufferedReader) throws IOException;
	}



	public static String getFiilePath(){
		return ExecuteAroundPSH.class.getClassLoader().getResource("lambdasinaction/chap3/data.txt").getFile();
	}

}
