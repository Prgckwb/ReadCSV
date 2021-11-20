import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ReadCSV {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        // 路線データ・駅データ全体のオブジェクト生成
        LineCSV lineCSV = null;
        StationCSV stationCSV = null;

        // ファイルの読み込み例外が発生する可能性あり
        try {
            lineCSV = new LineCSV();
            stationCSV = new StationCSV();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.print("駅一覧検索：路線名＞　");
        String targetLineName = stdin.next();

        List<String> targetStations;

        try{
            // 今調べたい路線コードをtargetLineCdに格納
            String targetLineCd = lineCSV.getLineCdFromLineName(targetLineName);

            // 調べて得た駅をリストに格納
            targetStations = stationCSV.getStationListFromLineCd(targetLineCd);
        }catch(NullPointerException ne){
            targetStations = new ArrayList<>();
            targetStations.add("NOT FOUND");
            ne.printStackTrace();
            System.exit(1);
        }


        // 囲みたいタグ名を指定（駅リストだけでいい場合は空文字指定 ex) tag = "" ）
        String tag = "";

        System.out.println();
//        printStationsList(targetStations, tag);
        printStationQuiz(targetStations);
    }

    // 駅ごとに開業して任意のタグで囲んで出力するメソッド
    public static void printStationsList(List<String> stationsList, String tag) {
        if (tag.equals("")) {
            for (String str : stationsList) System.out.println(str);
        } else {
            for (String str : stationsList) {
                System.out.println("<" + tag + ">" + str + "</" + tag + ">");
            }
        }
    }

    // 配列として使いたい時に使うメソッド
    public static void printStationsAsArray(List<String> stationsList){
        System.out.print("[");
        for (String str : stationsList){
            System.out.print("\"" + str + "\", ");
        }
        System.out.println("]");
    }

    public static void printStationQuiz(List<String> stationsList) {
        for (String str : stationsList) {
            System.out.println("Station(\"" + str + "\"),");
        }
    }
}
