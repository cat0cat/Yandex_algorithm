package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class C {

    public static void main(String[] args) {
        try (FileReader reader = new FileReader("input.txt");
             FileWriter writer = new FileWriter("output.txt")) {
            Scanner scanner = new Scanner(reader);
            String jsonString = scanner.nextLine();
            scanner.close();

            Properties props = new Properties();
            props.load(new FileInputStream("input.txt"));

            String nameContains = props.getProperty("NAME_CONTAINS").toLowerCase();
            int priceGreaterThan = Integer.parseInt(props.getProperty("PRICE_GREATER_THAN"));
            int priceLessThan = Integer.parseInt(props.getProperty("PRICE_LESS_THAN"));

            String dateB = props.getProperty("DATE_BEFORE");
            DateFormat dateParser = new SimpleDateFormat("dd.MM.yyyy");
            Date dateBefore = dateParser.parse(dateB);

            String dateA = props.getProperty("DATE_AFTER");
            Date dateAfter = dateParser.parse(dateA);

            JSONParser parser = new JSONParser();
            try {
                JSONArray a = (JSONArray) parser.parse(jsonString);
                List<JSONObject> jsonValues = new ArrayList<JSONObject>();
                for (Object object : a) {
                    JSONObject purchase = (JSONObject) object;

                    long id = (long) purchase.get("id");
                    String name = (String) purchase.get("name");
                    String lowerName = name.toLowerCase();
                    long price = (long) purchase.get("price");

                    String dateStr = (String) purchase.get("date");
                    Date date =  dateParser.parse(dateStr);

                    if (lowerName.contains(nameContains) && (price >= priceGreaterThan) && (price <= priceLessThan)
                            && (date.before(dateBefore) || date.equals(dateBefore))
                            && (date.after(dateAfter) || date.equals(dateAfter))) {
                        jsonValues.add(purchase);
                    }
                }
                jsonValues.sort(Comparator.comparing(o -> o.get("id").toString()));
                writer.write(jsonValues.toString());

            } catch (ParseException | java.text.ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
