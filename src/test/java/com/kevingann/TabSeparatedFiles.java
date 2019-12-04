package com.kevingann;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TabSeparatedFiles {

  private static final Logger LOG = LoggerFactory.getLogger(TabSeparatedFiles.class);

  private static final String INPUT_EXTENSION = "csv";
  private static final String OUTPUT_EXTENSION = "txt";
  private static final String INPUT_FILENAME = "Rough Draft";
  private static final String INPUT_PATH = "./resources/" + INPUT_FILENAME + "." + INPUT_EXTENSION;
  private static final String OUTPUT = "./target/" + INPUT_FILENAME + "." + OUTPUT_EXTENSION;

  @Test
  public void canReadHeaders() throws IOException, CsvException {
    CSVReader reader = new CSVReader(new FileReader(INPUT_PATH));
    List<String[]> myEntries = reader.readAll();

    if (myEntries.isEmpty()) {
      throw new RuntimeException("No lines found!");
    }

    if (myEntries.get(0).length < 1) {
      throw new RuntimeException("No columns found!");
    }

    List<String> lines = new ArrayList<>();

    String[] headers = myEntries.get(0);

    for (int i = 1; i < myEntries.size(); i++) {
      lines.add("Given I am a software development engineer in test");
      lines.add("When I am implementing the checkout flow for " + INPUT_FILENAME);
      matchHeaderWithValue(lines, headers, myEntries.get(i));
      lines.add("Then, the order is placed.");
      LOG.info("Test case [" + i + "].");
      writeLines(lines);
      lines.clear();
    }
  }

  private void matchHeaderWithValue(List<String> lines, String[] headers, String[] values) {
    for (int i = 0; i < headers.length; i++) {
      lines.add("And " + headers[i].trim() + " is " + values[i].trim());
    }
  }

  public void writeLines(List<String> lines) {
    for (String line : lines) {
      LOG.info(line);
    }
  }

  public void writeLinesToFile(String filename, List<String> lines) {
    try {
      FileWriter fileWriter = new FileWriter(filename);
      for (String line : lines) {
        fileWriter.append(line).append("\n");
      }
      fileWriter.flush();
      fileWriter.close();
    } catch (IOException ioe) {
      throw new RuntimeException("Problem reading/writing file [" + ioe.getMessage() + "].");
    }
  }
}
