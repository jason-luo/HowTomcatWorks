package ex.ch01;

import java.io.*;

public class Request {
    private InputStream input;
    private String uri = null;

    public Request(InputStream input){
        this.input = input;
    }

    public void parse(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line = reader.readLine();
            String[] parts = line.split(" ");
            if (parts.length < 2){
                return;
            }
            uri = parts[1];

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUri(){
        return uri;
    }
}
