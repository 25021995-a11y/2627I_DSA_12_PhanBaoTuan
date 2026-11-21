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


class Result2 {

    /*
     * Complete the 'runningMedian' function below.
     *
     * The function is expected to return a DOUBLE_ARRAY.
     * The function accepts INTEGER_ARRAY a as parameter.
     */
    static class Node implements Comparable<Node> {
        int val;
        int ind;

        public Node(int val, int ind) {
            this.val = val;
            this.ind = ind;
        }

        @Override
        public int compareTo(Node other) {
            if (this.val != other.val) {
                return Integer.compare(this.val, other.val);
            }
            return Integer.compare(this.ind, other.ind);
        }
    }
    public static List<Double> runningMedian(List<Integer> a) {
        // Write your code here
        List<Double> rs=new ArrayList<>();
        TreeSet<Node> tree=new TreeSet<>();
        Node mid=null;
        for(int i=0;i<a.size();i++){
            Node cur=new Node(a.get(i), i);

            tree.add(cur);
            if(i==0){
                mid=tree.first();
                rs.add((double)mid.val);
            }
            else if(i%2==1){
                if(cur.compareTo(mid)>0){
                    rs.add((double)(mid.val+tree.higher(mid).val)/2);
                }
                else if(cur.compareTo(mid)<0){
                    mid=tree.lower(mid);
                    rs.add((double)(mid.val+tree.higher(mid).val)/2);
                }
            }
            else{
                if(cur.compareTo(mid)>0){
                    mid=tree.higher(mid);
                    rs.add((double)mid.val);
                }
                else if(cur.compareTo(mid)<0){
                    rs.add((double)mid.val);
                }
            }

        }
        return rs;

    }

}

class Solution2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int aCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = IntStream.range(0, aCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Double> result = Result2.runningMedian(a);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
