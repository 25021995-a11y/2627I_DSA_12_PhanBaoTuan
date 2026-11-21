import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result1 {

    /*
     * Complete the 'missingNumbers' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY arr
     *  2. INTEGER_ARRAY brr
     */

    public static List<Integer> missingNumbers(List<Integer> arr, List<Integer> brr) {
        // Write your code here

        HashMap<Integer,LinkedList<Integer>> map=new HashMap<>();
        List<Integer> rs=new ArrayList<>();
        for(int x:brr){
            if(!map.containsKey(x)){
                map.put(x, new LinkedList<>());}
            map.get(x).add(x);
        }
        for(int x:arr){
            if(map.containsKey(x)){
                map.get(x).pollLast();
            }
        }
        for(Integer key:map.keySet()){
            if(!map.get(key).isEmpty()){
                rs.add(key);
            }
        }
        return rs;
    }
     static List<Integer> missingNumbers1(List<Integer> arr, List<Integer> brr) {
        // Write your code here


        List<Integer> rs=new ArrayList<>();
        Collections.sort(arr);
        Collections.sort(brr);
        int i=0;
        int j=0;
        while(j<brr.size()){
            if(i<arr.size()&& arr.get(i).equals(brr.get(j))){
                i++;
                j++;
            }
            else{
                if(!rs.contains(brr.get(j))){
                    rs.add(brr.get(j));
                    j++;}
                else{j++;}
            }
        }
        return rs;
    }
    public static List<Integer> missingNumbers2(List<Integer> arr, List<Integer> brr) {
        // Write your code here


        List<Integer> rs=new ArrayList<>();
        int min = Collections.min(brr);
        int max = Collections.max(brr);
        int[] count = new int[max - min + 1];
        for (int x : brr) {
            count[x - min]++;
        }
        for (int x : arr) {
            count[x - min]--;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                rs.add(i + min);
            }
        }
        return rs;
    }

}

class Solution1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> brr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result1.missingNumbers(arr, brr);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
