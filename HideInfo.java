import java.io.*;
import java.util.Scanner;
import java.util.regex.*;


public class HideInfo {
    public static void main(String[] args) {

        String filename = "input.txt";
        processFile(filename);
    }

    private static void processFile(String filePath) {
        StringBuilder content = new StringBuilder();

        // Read the content from the file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        // Process the content to hide sensitive information
        String sanitizedContent = hideSensitiveInfo(content.toString());

        // Write the sanitized content back to the same file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(sanitizedContent);
            System.out.println("Processing complete. Check " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static String hideSensitiveInfo(String text) {
        //i used \\b ro ensure to take the whole regex as if i found phone num in the IBAN
        // it will change the IBAN so this ensure to take it as whole
        String emailRegex = "\\b[\\w.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\\b";
        String phoneRegex = "\\b(010|012|011|015)\\d{8}\\b";
        //The mandatory points are : street, Government, Egypt.
        String addressRegex = "\\b.*?,\\s*(?:Cairo|Giza|Alexandria|Aswan|Luxor|Port Said|Suez|Ismailia|Dakahlia|Sharqia|Minya|Sohag|Qena|Asyut|Faiyum|New Cairo|Downtown Cairo)(?:,\\s*\\d{5})?,\\s*Egypt\\b";
        String accNumRegex = "\\b\\d{4}\\s?\\d{4}\\s?\\d{4}\\s?\\d{5}\\b";
        String IBANRegex = "\\bEG\\d{2}(\\s?\\d{4}){6}\\s?\\d{1}\\b";

        //national ID = "(century year (month 0-9) day (01-31) (code 1-88) any5digits"
        String nationalIDRegex ="\\b[123]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-8]\\d)\\d{5}\\b";
        String swiftCodeRegex ="^([A-Z]{8}|[A-Z]{11})$" ;


        //String asterisk = "*****";
        text= replaceWithAsterisks(text,emailRegex);
        text= replaceWithAsterisks(text,phoneRegex);
        text= replaceWithAsterisks(text,addressRegex);
        text= replaceWithAsterisks(text,accNumRegex);
        text= replaceWithAsterisks(text,IBANRegex);
        text= replaceWithAsterisks(text,nationalIDRegex);
        text= replaceWithAsterisks(text,swiftCodeRegex);

        return text;

    }
    public static String replaceWithAsterisks(String text, String regex) {
        //this function to automatically replace all character of the sensitive
        // info whatever of there number with "*"

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String replacement = "*".repeat(matcher.group().length());
            //System.out.println(matcher.group().length());
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}