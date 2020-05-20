package DataTest;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test01{
    public static void main(String[] args) throws IOException {
        String filePath = "D:\\资料\\安装资料\\任务\\jobs2.csv";
        InputStream in = new FileInputStream(filePath);
        CsvReader cr = new CsvReader(in, Charset.forName("utf-8"));

        // 读表头
        cr.readHeaders();
        String filePath2 = "D:\\资料\\安装资料\\任务\\2.csv";
        // 创建CSV写对象
        CsvWriter cs = new CsvWriter(filePath2,',', Charset.forName("utf-8"));
        //CsvWriter csvWriter = new CsvWriter(filePath);

//        // 写表头
//        String[] headers = cr.getHeaders();
//        cs.writeRecord(headers);


        while(cr.readRecord()) {
            String RawRecord = cr.getRawRecord();
            if(RawRecord.startsWith("company_financing_stage")){
                continue;
            }
            int columnCount = cr.getColumnCount();
            String[] s = new String[cr.getColumnCount()];
            for (int i = 0; i <columnCount; i++) {
                String str = cr.get(i);
                Pattern p = Pattern.compile("\\s+|\t+|\n\r");
                Matcher m = p.matcher(str);
                s[i] = m.replaceAll("");


            }
            cs.writeRecord(s);
        }

        cs.close();
    }
}
