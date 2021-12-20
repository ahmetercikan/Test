package UI.helpers;
import com.opencsv.CSVWriter;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static API.Services.response;

public class Excel {

    public static void excelGenerator(List<Integer>responsCode) throws IOException {

        List<String[]> csvData = createCsvData();
        try (CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir") + "/test-output/Odev.csv"))) {
            writer.writeAll(csvData);
        }
    }
    private static List<String[]> createCsvData() {
        String[] header = {"Response Code"};
        String[] record1 = {response.asString()};
        List<String[]> list = new ArrayList<>();
        list.add(header);
        list.add(record1);
        return list;
    }
}