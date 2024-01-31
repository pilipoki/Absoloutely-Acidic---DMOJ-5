import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // scanner for input
        Scanner sc = new Scanner(System.in);
        // store number of sensors
        int numberOfSensors = sc.nextInt();
        // store the reading for each sensor in an array
        int[] readings = new int[numberOfSensors];
        for (int i = 0; i < readings.length; i++) {
            readings[i] = sc.nextInt();
        }
        // we now have an array unorganized with readings

        // we should organize this array into an array that keeps track of the frequency
        // of each reading

        int[][] frequencyArray = new int[1000][numberOfSensors];
        for (int i = 0; i < readings.length; i++) {
            // first index of the array should be the actual value of the array while the
            // second index will be the frequency of the value appearing.

            // if its the first time this frequency has appeared set this index to 1
            if (frequencyArray[readings[i]][0] == 0) {
                frequencyArray[readings[i]][0] = 1;
            } else {
                // if the index is already one then we need to increase the frequency of this
                // reading
                for (int j = 0; j < frequencyArray[readings[i]].length; j++) {
                    if (frequencyArray[readings[i]][j] == 0) {
                        frequencyArray[readings[i]][j] = 1;
                        break;
                    }
                }
            }
        }

        // we now have a 2d array with the first index equal to the value of the reading
        // and the second index with the frequency of the reading with the number of the
        // last element =1 indicating the frequency

        // we could sort this array into another 1D array to store just the first index
        // of the values with those defined and the value of them as the frequency
        int[] sortedFrequency;
        int maxReading = readings[0];
        for (int i = 0; i < readings.length; i++) {
            if (maxReading < readings[i]) {
                // change
                maxReading = readings[i];
            }
            // do nothing if its not less
        }
        sortedFrequency = new int[maxReading + 1];
        for (int i = 0; i < frequencyArray.length; i++) {
            int freq = 0;
            for (int j = 0; j < frequencyArray[i].length; j++) {
                // go through every element to find valid readings
                if (frequencyArray[i][j] != 0) {
                    // if this value actually has at least one frequency, start counting the
                    // frequency and store in the sorted array
                    // keep going
                    // increase freq
                    freq++;
                    // you have reached the index where its equal to 0 so now stop and store in
                    // sorted
                    sortedFrequency[i] = freq;
                } else {
                    // do nothing
                }
            }
        }

        // we now have the sortedFrequency array that has all the readings values in the
        // index and the frequency in the elements

        // we now need to determine the value of the highest frequency readings

        System.out.println(Arrays.toString(readings));
        System.out.println(Arrays.deepToString(frequencyArray));
        System.out.println(Arrays.toString(sortedFrequency));

    }
}
