import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RegexAssignment {
    public static void main(String[] args) {
        String inputFile = "sample_input.txt";
        String outputFile = "sample_output.txt";
        processFile(inputFile, outputFile);
    }

    private static void processFile(String inputFile, String outputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            
            String line;
            int problemNumber = 0; 
            
            while ((line = br.readLine()) != null) {
                line = line.trim(); 
                if (line.matches("\\d+")) {
                    problemNumber = Integer.parseInt(line);
                    bw.write(problemNumber + "\n");
                } else if (line.equals("end")) {
                    bw.write("x\n");
                } else {
                    
                        String result = solveProblem(problemNumber, line);
                        bw.write(result + "\n");
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solveProblem(int problemNumber, String input) {
        switch (problemNumber) {
            case 1: return validateMACAddress(input);
            default: return "Invalid problem number";
        }
    }

    private static String validateMACAddress(String input) {
        return input.matches("(^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$)|(^[0-9A-Fa-f]{12}$)") ? "valid" : "invalid";
    }
}
