import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Email {

    public static void main(String[] args)throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter the email you need to find");
        String email = br.readLine();

        System.out.println();

        System.out.println("The searched email is: " + email);

        String WebAdress = "https://www.ecs.soton.ac.uk/people/" + email;
        URL url = new URL(WebAdress);
        BufferedReader wr = new BufferedReader(new InputStreamReader(url.openStream()));

        String inputLine;
        int input = 0;
        String searchedLine = null;
        String line = "property=" + '"' + "name" + '"';
        while((inputLine = wr.readLine()) != null) {
            //System.out.println(inputLine);
            if(inputLine.indexOf(line) != -1){
                input = inputLine.indexOf(line);
                searchedLine = inputLine;
            }
        }
        wr.close();
        System.out.println();
        //System.out.println(searchedLine);

        int beginIndex = searchedLine.indexOf('>',input);
        int endIndex = searchedLine.indexOf('<',input);
        String name = searchedLine.substring(beginIndex+1,endIndex);

        System.out.println("The email coresponds to " + name);
    }
}
