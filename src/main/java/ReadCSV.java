import java.io.IOException;
import java.util.List;
import java.util.Scanner;

class ReadCSV {

    public static void main(String[] args) throws IOException {
        Scanner stdin = new Scanner(System.in);

        // 路線データ・駅データ全体のオブジェクト生成
        LineCSV lineCSV = new LineCSV();
        StationCSV stationCSV = new StationCSV();

        System.out.print("駅一覧検索：路線名＞　");
        String targetLineName = stdin.next();

        // 今調べたい路線コードをtargetLineCdに格納
        String targetLineCd = lineCSV.getLineCdFromLineName(targetLineName);

        // 調べて得た駅をリストに格納
        List<String> targetStations = stationCSV.getStationListFromLineCd(targetLineCd);

        // 囲みたいタグ名を指定（駅リストだけでいい場合は空文字指定 ex) tag = "" ）
        String tag = "";

        System.out.println();
        printStationsList(targetStations, tag);
    }

    public static void printStationsList(List<String> stationsList, String tag) {
        if (tag.equals("")) {
            for (String str : stationsList) System.out.println(str);
        } else {
            for (String str : stationsList) {
                System.out.println("<" + tag + ">" + str + "</" + tag + ">");
            }
        }
    }
}
