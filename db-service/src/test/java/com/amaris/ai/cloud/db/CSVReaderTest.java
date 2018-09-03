package com.amaris.ai.cloud.db;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import com.amaris.ai.cloud.db.util.DBServiceUtil;

public class CSVReaderTest {

  final static String INPUT_FILE = "src/test/resources/input/input2.csv";;
  final static String OUTPUT_DIR = "src/test/resources/output/output.csv";

  final static String HEADERS = "country_name,country_code,indicator_name,indicator_code,year,approximation";

  public static void main(String[] args) throws Exception {
    reformatCSV();
    //extractCSV();
  }

  public static void reformatCSV() throws Exception {
    List<String> lines = FileUtils.readLines(new File(INPUT_FILE), "UTF-8");
    if (new File(OUTPUT_DIR).exists()) {
      new File(OUTPUT_DIR).delete();
    }
    final StringBuilder csvdata = new StringBuilder("");
    for (String line : lines) {
      csvdata.append(line.replaceAll("\"", "")).append("\n");
    }
    writeDataToFile(new File(OUTPUT_DIR), csvdata.toString());
  }
  
  public static void extractCSV() throws Exception {

    List<String> lines = FileUtils.readLines(new File(INPUT_FILE), "UTF-8");
    Integer tracker = 0;
    Map<Integer, String> headerMap = new HashMap<>();

    if (new File(OUTPUT_DIR).exists()) {
      new File(OUTPUT_DIR).delete();
    }
    final StringBuilder csvdata = new StringBuilder(HEADERS);
    for (String line : lines) {
      if (tracker++ == 0) {
        headerMap.putAll(headerMap(line));
        System.out.println(DBServiceUtil.objectMapper().writeValueAsString(headerMap));
      } else {
        final Map<Integer, String> valuemap = headerMap(line);
        for (Integer j = 4; j < valuemap.size(); j++) {
          final StringBuilder builder = new StringBuilder();
          builder.append(valuemap.get(0)).append(",");
          builder.append(valuemap.get(1)).append(",");
          builder.append(valuemap.get(2)).append(",");
          builder.append(valuemap.get(3)).append(",");
          builder.append(headerMap.get(j)).append(",");
          builder.append(valuemap.get(j));
          csvdata.append("\n").append(builder.toString());
        }
      }
    }
    writeDataToFile(new File(OUTPUT_DIR), csvdata.toString());
    System.out.println("<== all records have been written to the file ==>");

  }

  public static Map<Integer, String> headerMap(final String line) {
    final Map<Integer, String> map = new HashMap<>();
    final String[] columnNames = line.replaceAll("\"", "").split(",");
    for (Integer i = 0; i < columnNames.length; i++) {
      map.put(i, columnNames[i]);
    }
    return map;
  }

  public static void writeDataToFile(final File file, final String data) throws Exception {
    FileUtils.writeStringToFile(file, data, Charset.defaultCharset(), true);
  }
}
