import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BareBones {

    public Map<String, Integer> values = new HashMap<>();

    //sets the values for the map
    public void setValue(String name, Integer value){
        this.values.put(name,value);
    }

    //return the map
    public Integer getValue(String name){
        return values.get(name);

    }

    //Handles the "clear" case
    public void clearParser(String str){

        String[] words = str.split(" ");
        String word = words[words.length - 1];

        if(values.get(word) == null) {
            word = word.substring(0, word.length() - 1);
            setValue(word, 0);
        }
        else{
            values.replace(word,0);
        }

    }

    //Handles the "incr" case
    public void incrParser(String str){

        String[] words = str.split(" ");
        String word = words[words.length - 1];
        word = word.substring(0,word.length() - 1);
        this.values.replace(word,values.get(word) + 1);

    }

    //Handles the "decr" case
    public void decrParser(String str){

        String[] words = str.split(" ");
        String word = words[words.length - 1];
        word = word.substring(0,word.length() - 1);
        this.values.replace(word,values.get(word) - 1);

    }

    //handles the "while" case
    public String whileParser(String str){

        if(str.indexOf("while") != 0){
            str = str.substring(str.indexOf("while"));
        }
        String[] words = str.split(" ");
        String word = words[1];
        return word;

    }


    //prints the keys and their assigned values which are found in the map
    public void printValues(){

        for(String key : values.keySet()){
            String value = values.get(key).toString();
            System.out.println(key + " = " + value);
        }

    }

    //treats the cases involving variable changes
    public void cases(BareBones bb, String str){

        if(str.indexOf("clear") != -1){
            bb.clearParser(str);
        }
        else{
            if(str.indexOf("incr") != -1){
                bb.incrParser(str);
            }
            else{
                if(str.indexOf("decr") != -1){
                    bb.decrParser(str);
                }
            }
        }
    }

    public static void main(String[] args)throws FileNotFoundException, IOException, InterruptedException {

        File file = new File("C:\\Users\\tudor\\Downloads\\BareBones\\src\\Input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        BareBones b = new BareBones();
        String line;

        while ((line = br.readLine()) != null){

            System.out.println(line);
            System.out.println();

            TimeUnit.SECONDS.sleep(1);

            System.out.println("The current variables have the following values");
            System.out.println();

            TimeUnit.SECONDS.sleep(1);

            b.cases(b,line);
            if(line.indexOf("while") != -1){

                String str;
                Integer var = b.getValue(b.whileParser(line));
                Integer i;

                while((str = br.readLine()) != "end;" && str != null) {

                    System.out.println(str);
                    for (i = 0; i < var; i++) {
                        b.cases(b, str);
                    }

                        if (str.indexOf("while") != -1) {


                            Integer var1 = b.getValue(b.whileParser(str));
                            Integer j;

                            while ((!(str = br.readLine()).contains("end;")) && str != null) {

                                for (j = 0; j < var1*var;j++) {
                                    b.cases(b, str);
                                }
                            }
                        }
                    }
                    //if(str.equals("end;"))
                        //System.out.println(str);

            }

            b.printValues();
            TimeUnit.SECONDS.sleep(1);
            System.out.println();

        }

    }
}
