public class KMPAlgorithm{

    private static void computeLPSArray(String pattern, int[] lps){
        int length = 0;
        lps[0] = 0;
        int i = 1;

        while(i<pattern.length()){
            if(pattern.charAt(i) == pattern.charAt(length)){
                length++;
                lps[i] = length;
                i++;
            } else{
                if(length !=0)
            }
        }
    }
}