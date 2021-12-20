package UI.helpers;
import com.opencsv.CSVWriter;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static API.Services.response;

public class Excel {

    public static void excelGenerator(List<String> responCodes) throws IOException {

        FileWriter writer = new FileWriter(System.getProperty("user.dir") + "/test-output/Odev.csv");

        String collect = responCodes.stream().collect(Collectors.joining(","));
        System.out.println(collect);

        writer.write(collect);
        writer.close();
    }
}