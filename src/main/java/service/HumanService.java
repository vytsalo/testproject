package service;

import java.io.IOException;
import java.text.ParseException;

public interface HumanService {

    void exportToExcel() throws IOException;
    void initDB() throws ParseException, IOException;
}
