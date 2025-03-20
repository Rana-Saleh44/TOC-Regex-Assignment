import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
                return processOddABSubstrings(input);

            case 8 :
                return processWordLengthMultipleOfFive(input);
            case 9:
                return parseLogEntry(input);
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


    // problem 1
    private static String validateMACAddress(String input) {
        return input.matches("(^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$)|(^[0-9A-Fa-f]{12}$)") ? "valid" : "invalid";
    }

    // problem 2
    private static String validateAandB(String input) {
        return input.matches("(?i)^(?:(b)*(a(b|bbb))*)$") ? "valid" : "invalid";
    }

    // problem 3
    private static String validateDate(String input) {
        return input.matches("^(\\d{4}[-/]\\d{1,2}[-/]\\d{1,2}|\\d{1,2}[-/]\\d{1,2}[-/]\\d{4})$") ? "valid" : "invalid";
    }

    // problem 4
    private static String validateIP(String input) {
        String in = "^(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\."
                + "(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\."
                + "(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\."
                + "(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])$";

        return input.matches(in) ? "valid" : "invalid";
    }

    // problem 5
    private static String validateCVaribles(String input) {
        String in = "^[a-zA-Z_][a-zA-Z0-9_]*$";
        return input.matches(in) ? "valid" : "invalid";
    }

    // problem 6
    private static String validateODDBs(String input) {
        String in = "^[aA]*([bB][aA]*){1}([bB][aA]*)([bB][aA]*)$";
        return input.matches(in) ? "valid" : "invalid";
    }
   
    
   //problem 7
    private static String processOddABSubstrings(String input) {
        
        StringBuilder sb = new StringBuilder();
    
        sb.append("**").append(input).append("**\n");
    
        Pattern pattern = Pattern.compile(
            "(?=(?<oddAB>" +
              "(?=(?:[^a]*a[^a]*a)*[^a]*a[^a]*$)" +     // Odd number of 'a's
              "(?=(?:[^b]*b[^b]*b)*[^b]*b[^b]*$)" +     // Odd number of 'b's
              "[ab]+" +                                // Only 'a' or 'b'
            "))"
        );
    
        Matcher matcher = pattern.matcher(input);
    
        // We'll store all matches and their indices
        List<String> matches = new ArrayList<>();
        List<int[]> indices = new ArrayList<>();
    
        // Find all matches, including overlaps
        while (matcher.find()) {
            // Group "oddAB" is the actual substring
            String match = matcher.group("oddAB");
            int start = matcher.start("oddAB"); 
            int end = matcher.end("oddAB") ; // inclusive index
    
            matches.add(match);
            indices.add(new int[]{start, end});
        }
    

        if (matches.isEmpty()) {
            sb.append("number of matched substrings: 0\n");
        } else {
            sb.append("number of matched substrings: ").append(matches.size()).append("\n");
            for (int i = 0; i < matches.size(); i++) {
                sb.append(matches.get(i))
                  .append(" [")
                  .append(indices.get(i)[0])
                  .append(", ")
                  .append(indices.get(i)[1])
                  .append("]\n");
            }
        }
        sb.append("---------------------------------------------\n");
        return sb.toString();
    }
    



    // Problem 8: Words with Length Multiple of 5
    private static String processWordLengthMultipleOfFive(String input) {
        
        StringBuilder sb = new StringBuilder();
        
        Pattern pattern = Pattern.compile("\\b\\w{5}(\\w{5})*\\b");
        Matcher matcher = pattern.matcher(input);

        List<String> matches = new ArrayList<>();
        List<int[]> indices = new ArrayList<>();

        while (matcher.find()) {
            matches.add(matcher.group());
            indices.add(new int[]{matcher.start(), matcher.end()});
        }

        sb.append("**").append(input).append("**\n");

        if (matches.isEmpty()) {
            sb.append("No word matches\n");
        } else {
            sb.append("Number of matched words: ").append(matches.size()).append("\n");
            for (int i = 0; i < matches.size(); i++) {
                sb.append(matches.get(i))
                .append(" [")
                .append(indices.get(i)[0])
                .append(", ")
                .append(indices.get(i)[1])
                .append("]\n");
            }
        }

        return sb.toString();
    }


    // Log Parsing (Problem 9)
    private static String parseLogEntry(String input) {
        String pattern = "\\[(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})\\] \\[(\\w+)\\] (.+)";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);
        
        if (matcher.find()) {
            String timestamp = matcher.group(1);
            String level = matcher.group(2);
            String message = matcher.group(3);
            return "Timestamp: " + timestamp + ", Level: " + level + ", Message: " + message;
        }
            return "Invalid log format";
    }

    // problem 10
    private static String validateMathExpression(String input) {
         String term = "[+-]?(?:[a-zA-Z]+\\d*(?:\\.\\d+)?|\\d+(?:\\.\\d+)?[a-zA-Z]*)";

        String operatorsAndTerm = "\\s*[+\\-*/%^]\\s*" + term;
        String fullRegex = "^\\s*" + term + "(?:" + operatorsAndTerm + ")*\\s*=\\s*" + term + "(?:" + operatorsAndTerm + ")*\\s*$";

        return input.matches(fullRegex) ? "valid" : "invalid";
    }


    // problem 11
    private static String validateSevenWords(String input) {
        String in = "^(\\S+\\s+){6}\\S+$";
        return input.matches(in) ? "valid" : "invalid";
    }    
    
    // problem 12
    private static String validateAlternateLettersDigits(String input) {
        String in = "^([a-zA-Z][0-9]){1,}[a-zA-Z]?$|^([0-9][a-zA-Z]){1,}[0-9]?$";
        return input.matches(in) ? "valid" : "invalid";
    }

    // problem 13
    private static String validateWindowsFilePath(String input) {
        String in = "^[CDEG]:\\\\([\\w\\s]+\\\\)*[\\w\\s]+\\.\\w+$";
        return input.matches(in) ? "valid" : "invalid";
    }



}
