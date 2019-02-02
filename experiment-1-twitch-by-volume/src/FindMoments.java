import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class FindMoments {

    public static void main(String[] args) throws Exception {
		String dataFile = args[0];
		String videoFile = args[1];
		String outDir = args[2];
		String outFile = args[3];
		int freq = 10000;
		int top = 20;
		int before = 5;
		int after = 4;
		int excludeGap = 10;

		BufferedReader br = new BufferedReader(new FileReader(new File(dataFile)));
		String line;
		List<Integer> list = new ArrayList<Integer>();
		while ((line = br.readLine()) != null) {
			list.add(Integer.parseInt(line));
		}
		br.close();

		int[] maxValues = new int[list.size() / freq + 1];
		for(int i = 0; i < list.size(); i += freq) {
			for(int j = i; j < i + freq && j < list.size(); j++) {
				if(Math.abs(list.get(j)) > maxValues[i/freq]) maxValues[i/freq] = Math.abs(list.get(j));
			}
		}
		int[] maxValuesSorted = Arrays.copyOf(maxValues, maxValues.length);
		Arrays.sort(maxValuesSorted);

		int treshold = maxValuesSorted[maxValuesSorted.length - 20];

		String ffmpeg = "ffmpeg -y -i "+videoFile+" ";
		PrintWriter writer = new PrintWriter(new FileWriter(outFile));
		for(int i = 0; i < maxValues.length; i++) {
			if(maxValues[i] >= treshold) {
				int start = i - before;
				int end = i + after;
				int max = maxValues[i];
				boolean found = false;
				do {
					found = false;
					for(i = end + 1; i < end + excludeGap + before && i < maxValues.length; i++) {
						if(maxValues[i] >= treshold) {
							found = true;
							end = i + after;
						}
					}
				} while (found);

				ffmpeg += " -ss "+start+" -t "+(end-start+1)+" -c copy "+outDir+"/"+start+".ts ";
				writer.println("file '"+start+".ts'");
			}
		}
		writer.close();
		System.out.println(ffmpeg);


		// int[] data = new WaveData().extractAmplitudeFromFile(new File(args[0]));
		// PrintWriter writer = new PrintWriter(new FileWriter(args[1]));
		// for(int i = 0; i < data.length; i++) writer.println(data[i]);
		// writer.close();
    }
}
