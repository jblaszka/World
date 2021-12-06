package simulation;

import java.util.Map;

public class SimulationParams {
    private static final String CONFIG_PATH = "config.json";
    private static final Map<String, Integer> paramsMap;

    static {
        paramsMap = JsonParser.readSimulationParams(CONFIG_PATH);
    }

    public static Integer getField(String fieldName) {
        return paramsMap.computeIfAbsent(fieldName, k -> {
            throw new IllegalArgumentException("There is no config field called " + k);
        });
    }
}
