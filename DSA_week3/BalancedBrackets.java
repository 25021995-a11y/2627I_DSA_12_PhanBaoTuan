import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;

class Result {

    /*
     * Complete the 'isBalanced' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isBalanced(String s) {
        // Write your code here
        Stack stack=new Stack<>();

        for(int i=0;i<s.length();i++){
            Character c=Character.valueOf(s.charAt(i));
            if(c=='{' || c=='['||c=='('){
                stack.push(c);
            }
            else if((c=='}' || c==']'||c==')') && stack.size()!=0){
                String result=""+stack.peek()+c;
                if(result.equals("{}")||result.equals("[]")||result.equals("()")){
                    stack.pop();
                }
                else{return "NO";}
            }
            else{return "NO";}
        }
        return stack.isEmpty() ? "YES" : "NO";
    }

}

public class BalancedBrackets {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                String result = Result.isBalanced(s);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}