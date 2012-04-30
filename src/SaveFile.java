import java.io.*;
import java.net.*;
import java.util.regex.*;

public class SaveFile
{
	public static String getFileContent(String fileName)
	{
		StringBuffer sb = new StringBuffer();
		try
		{
			DataInputStream in = new DataInputStream(new FileInputStream(fileName));
			String line = in.readLine();
			while (line != null)
			{
				sb.append(line + "\n");
				line = in.readLine();
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR : " + e);
		}
		return sb.toString();
	}

	public static void writeToFile(String str, String fileName)
	{
		try
		{
			FileOutputStream fout = new FileOutputStream(new File(fileName));
			fout.write(str.getBytes());
			fout.close();
		}
		catch(Exception e)
		{
			System.out.println("ERROR : " + e);
		}
	}
}
