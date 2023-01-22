package gui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSONBoard {

	@SuppressWarnings("unchecked")
    public static void main(String[] args) 
    {
        /
        JSONParser jsonParser = new JSONParser();
        @SuppressWarnings("unused")
		ArrayList<Square> allSquares = new ArrayList<Square>();
        String[] str= {""};
        		int x = 0;
        
        try (FileReader reader = new FileReader("board.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray propertyList = (JSONArray) obj;
            System.out.println(propertyList);
           
           
            propertyList.forEach( boardArray -> parsepropertyObject( (JSONObject) boardArray ) );
      
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

	private static void parsepropertyObject(JSONObject boardObject) {

		String name = (String) boardObject.get("name");
		System.out.println(name);

		int price = (boardObject.get("price") != null) ? Integer.parseInt(boardObject.get("price").toString()) : 0;
		System.out.println(price);
		

		String colour = (boardObject.get("colour") != null) ? (String) boardObject.get("colour") : "";
		System.out.println(colour);

		String type = (String) boardObject.get("type");
		System.out.println(type);

	}

}
