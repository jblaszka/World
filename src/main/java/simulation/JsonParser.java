package simulation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.lang.reflect.Type;

public class JsonParser {
    private static final Gson gson = new Gson();

    public static Map<String, Integer> readSimulationParams(String filename){
        Type type = new TypeToken<Map<String,Integer>>() {}.getType();
        try{
            String jsonString = Files.readString(Path.of(filename));
            return gson.fromJson(jsonString,type);
        } catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static void dumpStatisticToJsonFile(String filename, SimulationStatistics simulationStatistics){
        try(FileWriter writer = new FileWriter(filename)) {
            gson.toJson(simulationStatistics, writer);
            writer.append('\n');
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
