package Random.AttendancePoints;


import java.util.Arrays;
import java.util.Random;

public class StableMarriage {

    // Method to find a stable matching for n boys and n girls
    public static int[][] smp(int[][] PM, int[][] PD) {
        int n = PM.length;
        int[] w = new int[n]; // w[i] is the current partner of girl i
        int[] m = new int[n]; // m[i] is the current partner of boy i
        int[] mp = new int[n]; // mp[i] is the next girl that boy i will propose to

        Arrays.fill(w, -1); // Initially all girls are free
        Arrays.fill(m, -1); // Initially all boys are free

        while (true) {
            boolean free = false; // True if there is a free girl
            for (int i = 0; i < n; i++) {
                if (m[i] == -1) {
                    // Boy i is free, so he proposes to the next girl on his list
                    int j = mp[i];
                    mp[i]++; // Move to the next girl on the list
                    if (w[j] == -1) {
                        // Girl j is free, so they become a couple
                        w[j] = i;
                        m[i] = j;
                    } else {
                        // Girl j is already engaged, so she compares the two boys
                        int k = w[j];
                        if (PD[j][k] > PD[j][i]) {
                            // Girl j prefers boy i to her current partner k, so they become a couple
                            w[j] = i;
                            m[i] = j;
                            m[k] = -1; // Boy k becomes free
                            free = true; // There is a free girl now
                        }
                    }
                }
            }

            if (!free) {
                // No free girls, so the matching is stable
                break;
            }
        }

        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = i;
            pairs[i][1] = m[i];
        }

        return pairs;
    }

    public static void main(String[] args) {
        int[] nValues = {5, 10, 15, 20};
        for (int n : nValues) {
            int[][] PM = generateRandomPreferences(n); // Preference matrix for boys
            int[][] PD = generateRandomPreferences(n); // Preference matrix for girls

            int[][] pairs = smp(PM, PD);

            System.out.println("Stable pairs for n = " + n + ":");
            for (int[] pair : pairs) {
                System.out.println("Boy " + pair[0] + " and girl " + pair[1]);
            }
            System.out.println();
        }
    }

    // Method to generate a random preference matrix for n boys or girls
    public static int[][] generateRandomPreferences(int n) {
        int[][] preferences = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                preferences[i][j] = j;
            }
            // Shuffle the preference list for each boy or girl
            shuffle(preferences[i]);
        }
        return preferences;
    }

    public static void shuffle(int[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int j = rand.nextInt(array.length);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

}
