import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class LineCSV{
    static final int LINE_CD = 0;
    static final int LINE_NAME = 2;
    final private Path linePath = Paths.get("./src/main/resources/line.csv");
    public List<String> lineStringList;

    LineCSV() throws IOException {
        lineStringList = Files.readAllLines(linePath);
    }

    // 入力したい数値の最大値を指定して、Int整数を得るメソッド
    public static int inputNum(int max){
        while (true){
            try{
                int select = new Scanner(System.in).nextInt();
                if(0 <= select && select <= max){
                    return select;
                }else{
                    System.out.println("正しい範囲の数値を入力してください");
                }
            }catch (InputMismatchException ime){
                System.out.println("正しい形式で入力してください");
            }
        }
    }

    public List<String> getLineStringList() {
        return lineStringList;
    }

    public Path getLinePath() {
        return linePath;
    }

    // 路線名から路線コードを得るメソッド
    public String getLineCdFromLineName(String lineName){
        ArrayList<String> optionsLineName = new ArrayList<>();
        ArrayList<String> optionsLineCd = new ArrayList<>();

        for(String str: this.lineStringList){

            // 路線名が一致したら重複の無いように選択肢に追加
            if(str.contains(lineName)){
                boolean flag = false; // 路線コードの重複確認用フラグ
                String[] separateStr = str.split(",");

                // すでに追加された路線コードかどうか確認
                for(String words: optionsLineCd){
                    if (separateStr[LINE_CD].equals(words)) {
                        flag = true;
                        break;
                    }
                }
                if(!flag && !separateStr[LINE_NAME].contains("新幹線")){
                    optionsLineCd.add(separateStr[LINE_CD]);
                    optionsLineName.add(separateStr[LINE_NAME]);
                }
            }
        }
        // ここで路線名が一致したものが全てoptionsに入っている　OR　一致なし　の状態
        if(optionsLineCd.isEmpty()){
            System.out.println("該当路線なし");
            System.exit(-1);
            return "-1";
        }else{
            for(int i = 0; i < optionsLineCd.size(); i++){
                System.out.println(" ["+i+"]:" + optionsLineName.get(i));
            }
            System.out.println();
            System.out.print("検索したい路線番号 > ");
            int num = inputNum(optionsLineCd.size() - 1);
            return optionsLineCd.get(num);
        }
    }
}
