package com;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


    public class FileHelper<T> {

        private File file;
        private final ObjectMapper mapper = new ObjectMapper();
        private final Class<T[]> type;

        // Proper constructor
        public FileHelper(String filePath, Class<T[]> type) {
            this.file = new File(filePath);
            this.type = type;

            try {
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                    mapper.writeValue(file, new ArrayList<T>());
                }
            } catch (IOException e) {
                throw new RuntimeException("Unable to initialize JSON file", e);
            }
        }

        // Read all items
        public ArrayList<T> readAll() {
            try {
                if (file.length() == 0) return new ArrayList<>();

                T[] data = mapper.readValue(file, type);
                ArrayList<T> list = new ArrayList<>();
                for (T item : data) list.add(item);
                return list;

            } catch (IOException e) {
                throw new RuntimeException("Unable to read JSON file", e);
            }
        }

        // Save all items
        public void saveAll(ArrayList<T> list) {
            try {
                mapper.writeValue(file, list);
            } catch (IOException e) {
                throw new RuntimeException("Unable to write to JSON file", e);
            }
        }
    }


