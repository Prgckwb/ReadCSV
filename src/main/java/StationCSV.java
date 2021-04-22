import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class StationCSV{
    static final int STATION_NAME = 2;
    static final int LINE_CD = 5;
    final private Path stationPath = Paths.get("./src/main/resources/station.csv");
    private final List<String> stationStringList;

    StationCSV() throws IOException {
        stationStringList = Files.readAllLines(stationPath);
    }

    public ArrayList<String> getStationListFromLineCd(String lineCd){
        ArrayList<String> stations = new ArrayList<>();
        for(String str : this.stationStringList){
            String[] splitStringList = str.split(",");
            if(splitStringList[StationCSV.LINE_CD].equals(lineCd)) {
                stations.add(splitStringList[STATION_NAME]);
            }
        }
        return stations;
    }

    public List<String> getStationStringList() {
        return stationStringList;
    }

    public Path getStationPath() {
        return stationPath;
    }
}