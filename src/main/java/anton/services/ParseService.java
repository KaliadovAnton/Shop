package anton.services;

public class ParseService {
    public static String parseGood(String toParse){
        String parsedString = toParse.split(" ")[0];
        return parsedString;
    }
}
