import java.io.*;
import java.util.*;

public class SimpleTextEditor {

    static class TextEditor {
        private StringBuilder currentText;
        private Stack<String> history;
        public TextEditor() {
            currentText = new StringBuilder();
            history = new Stack<>();
        }


        public void append(String w) {
            history.push(currentText.toString());
            currentText.append(w);

        }


        public void delete(int k) {
            history.push(currentText.toString());
            int len = currentText.length();
            currentText.delete(len - k, len);
        }


        public void print(int k) {
            System.out.println(currentText.charAt(k - 1));

        }


        public void undo() {
            if (!history.isEmpty()) {
                currentText = new StringBuilder(history.pop());
            }

        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;

        int q = Integer.parseInt(line.trim());
        TextEditor editor = new TextEditor();

        for (int i = 0; i < q; i++) {
            line = br.readLine();
            if (line == null) break;

            String[] parts = line.split(" ");
            int type = Integer.parseInt(parts[0]);

            switch (type) {
                case 1:
                    editor.append(parts[1]);
                    break;
                case 2:
                    editor.delete(Integer.parseInt(parts[1]));
                    break;
                case 3:
                    editor.print(Integer.parseInt(parts[1]));
                    break;
                case 4:
                    editor.undo();
                    break;
            }
        }
    }
}