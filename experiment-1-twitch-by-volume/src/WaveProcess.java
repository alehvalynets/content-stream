import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

class WaveProcess {
    public static void main(String[] args) throws Exception {
		int[] data = new WaveData().extractAmplitudeFromFile(new File(args[0]));
		PrintWriter writer = new PrintWriter(new FileWriter(args[1]));
		for(int i = 0; i < data.length; i++) writer.println(data[i]);
		writer.close();
    }
}
