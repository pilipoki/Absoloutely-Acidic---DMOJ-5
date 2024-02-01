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

        int maxReading = readings[0];
        for (int i = 0; i < readings.length - 1; i++) {
            if (maxReading < readings[i + 1]) {
                // change
                maxReading = readings[i + 1];
            }
            // do nothing if its not less
        }

        short[] frequencyArray = new short[maxReading + 1];
        for (int i = 0; i < readings.length; i++) {
            for (int j = 0; j < frequencyArray.length; j++) {
                if (readings[i] == j) {
                    // copies in readings into a frequency array where the index is the value of
                    // reading and the element is the frequency of that reading
                    frequencyArray[j]++;
                }
            }
        }

        // short[][] frequencyArray = new short[maxReading+1][numberOfSensors/2];
        // for (int i = 0; i < readings.length; i++) {
        // // first index of the array should be the actual value of the array while the
        // // second index will be the frequency of the value appearing.

        // // if its the first time this frequency has appeared set this index to 1
        // if (frequencyArray[readings[i]][0] == 0) {
        // frequencyArray[readings[i]][0] = 1;
        // } else {
        // // if the index is already one then we need to increase the frequency of this
        // // reading
        // for (int j = 0; j < frequencyArray[readings[i]].length; j++) {
        // if (frequencyArray[readings[i]][j] == 0) {
        // frequencyArray[readings[i]][j] = 1;
        // break;
        // }
        // }
        // }
        // }

        // we now have a 2d array with the first index equal to the value of the reading
        // and the second index with the frequency of the reading with the number of the
        // last element =1 indicating the frequency

        // we could sort this array into another 1D array to store just the first index
        // of the values with those defined and the value of them as the frequency
        // short[] sortedFrequency;
        // sortedFrequency = new short[maxReading + 1];
        // for (int i = 0; i < frequencyArray.length; i++) {
        // int freq = 0;
        // for (int j = 0; j < frequencyArray[i].length; j++) {
        // // go through every element to find valid readings
        // if (frequencyArray[i][j] != 0) {
        // // if this value actually has at least one frequency, start counting the
        // // frequency and store in the sorted array
        // // keep going
        // // increase freq
        // freq++;
        // // you have reached the index where its equal to 0 so now stop and store in
        // // sorted
        // sortedFrequency[i] = (short)freq;
        // } else {
        // // do nothing
        // }
        // }
        // }

        // free up memory
        // frequencyArray = null;
        // readings =null;

        // we now have the sortedFrequency array that has all the readings values in the
        // index and the frequency in the elements

        // we now need to determine the value of the highest frequency readings

        // bubble sort the sortedFrequency array to get highest frequencies in order,
        // than backcheck in original array to find the corresonding index(Value) of the
        // readings
        short[] highestFrequency = new short[frequencyArray.length];

        short[] tempArr = Arrays.copyOf(frequencyArray, frequencyArray.length);

        Arrays.sort(tempArr);

        // copy in reverse order into the highest freq array

        for (int i = 0; i < tempArr.length; i++) {
            highestFrequency[tempArr.length - i - 1] = tempArr[i];
        }

        // we now have an array called highestFrequency storing the highest frequencies
        // of each in order, we now need to check this against the original sorted array
        boolean highest = false;
        boolean twoHighest = false;
        boolean done = false;
        int highestFrequencyReading = 0;
        int lowestFrequencyReading = 0;
        int lowestFrequencyReading2 = 0;
        int highestFrequencyReading2 = 0;
        int absoluteDifference = 0;
        int tempHighestReading = 0;
        int tempLowestReading = 1001;

        for (int i = 0; i < highestFrequency.length; i++) {
            if (done) {
                // done
            } else {
                // backcheck agaisnt original array
                for (int j = 0; j < frequencyArray.length; j++) {
                    if (highestFrequency[i] == frequencyArray[j]) {
                        // if frequency found in the OG array, take the index as the value of the
                        // highest frequency reading.
                        if (!highest) {
                            if (j > tempHighestReading) {
                                // make sure we store the highest value of reading regardless
                                highestFrequencyReading = j;
                                tempHighestReading = j;
                            }
                            if (j < tempLowestReading) {
                                // also keep track of the lowest reading
                                tempLowestReading = j;
                                lowestFrequencyReading = j;
                            }

                        } else {
                            if (j > tempHighestReading) {
                                highestFrequencyReading2 = j;
                                tempHighestReading = j;
                            }
                            if (j < tempLowestReading) {
                                // also keep track of the lowest reading
                                tempLowestReading = j;
                                lowestFrequencyReading2 = j;
                            }
                        }
                    }
                }

                tempHighestReading = 0;
                tempLowestReading = 1001;
                // we have already passed through once so we have secured one of the highest
                // readings.
                if (highestFrequency[0] == highestFrequency[1]) {
                    // we have two highest frequencies or more... stop and exit loop
                    done = true;
                    twoHighest = true;
                }
                if (highestFrequency[i] > highestFrequency[i + 1]) {
                    if (highest) {
                        // if we have already gone through the cycle for the second highest and the next
                        // frequencys are less, we are done
                        done = true;
                    }
                    // only if there is one highest frequency
                    highest = true;
                }

            }
        }

        // we now have both the highs and lows of the most frequent readings

        // calculate highest diffrence
        if (twoHighest) {
            absoluteDifference = highestFrequencyReading - lowestFrequencyReading;
        } else {
            absoluteDifference = Math.abs(highestFrequencyReading - lowestFrequencyReading2);

            if (absoluteDifference < Math.abs(lowestFrequencyReading - highestFrequencyReading2)) {
                absoluteDifference = Math.abs(lowestFrequencyReading - highestFrequencyReading2);
            }
        }

        System.out.println(absoluteDifference);
    }
}
