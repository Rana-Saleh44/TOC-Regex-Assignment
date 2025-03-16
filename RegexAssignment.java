import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            case 1:
                return validateMACAddress(input);
            case 2:
                return validateAandB(input);
            case 3:
                return validateDate(input);

            case 4:
                return validateIP(input);

            case 5:
                return validateCVaribles(input);

            case 6:
                return validateODDBs(input);

            case 7:
                //return validateOddAB(input);


            case 10:
                return validateMathExpression(input);

            case 11:
                return validateSevenWords(input);

            case 12:
                return validateAlternateLettersDigits(input);

            case 13:
                return validateWindowsFilePath(input);
            default:
                return "Invalid problem number";
        }
    }

    private static String validateMACAddress(String input) {
        return input.matches("(^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$)|(^[0-9A-Fa-f]{12}$)") ? "valid" : "invalid";
    }

    private static String validateAandB(String input) {
        return input.matches("^(?:b|a(bb|b{3}))*$") ? "valid" : "invalid";
    }

    private static String validateDate(String input) {
        return input.matches("^(\\d{4}[-/]\\d{1,2}[-/]\\d{1,2}|\\d{1,2}[-/]\\d{1,2}[-/]\\d{4})$") ? "valid" : "invalid";
    }

    private static String validateIP(String input) {
        String in = "^(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\."
                + "(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\."
                + "(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\."
                + "(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])$";

        return input.matches(in) ? "valid" : "invalid";
    }

    private static String validateCVaribles(String input) {
        String in = "^[a-zA-Z_][a-zA-Z0-9_]*$";
        return input.matches(in) ? "valid" : "invalid";
    }

    private static String validateODDBs(String input) {
        String in = "^[aA]*([bB][aA]*){1}([bB][aA]*)([bB][aA]*)$";
        return input.matches(in) ? "valid" : "invalid";
    }

    private static String validateMathExpression(String input) {
        String in = "^[a-zA-Z0-9]+([+\\-*/%^][a-zA-Z0-9]+)*=[a-zA-Z0-9]+([+\\-*/%^][a-zA-Z0-9]+)*$";
        return input.matches(in) ? "valid" : "invalid";
    }    
    
    private static String validateSevenWords(String input) {
        String in = "^(\\S+\\s+){6}\\S+$";
        return input.matches(in) ? "valid" : "invalid";
    }    
    
    private static String validateAlternateLettersDigits(String input) {
        String in = "^([a-zA-Z][0-9]){1,}[a-zA-Z]?$|^([0-9][a-zA-Z]){1,}[0-9]?$";
        return input.matches(in) ? "valid" : "invalid";
    }

    private static String validateWindowsFilePath(String input) {
        String in = "^[CDEG]:\\\\([\\w\\s]+\\\\)*[\\w\\s]+\\.\\w+$";
        return input.matches(in) ? "valid" : "invalid";
    }

}
