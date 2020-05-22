package DataTest;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.*;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class test03 {
    public static void main(String[] args) throws IOException {
        String filePath = "D:\\资料\\安装资料\\任务\\jobs3.csv";
        InputStream in = new FileInputStream(filePath);
        CsvReader cr = new CsvReader(in, Charset.forName("utf-8"));

        // 读表头
        cr.readHeaders();
        String filePath2 = "D:\\资料\\安装资料\\任务\\3.csv";
        // 创建CSV写对象
        CsvWriter cs = new CsvWriter(filePath2,',', Charset.forName("utf-8"));


        while(cr.readRecord()) {
            int j =0;
            String RawRecord = cr.getRawRecord();
            if(RawRecord.startsWith("company_financing_stage")){
                continue;
            }
            int columnCount = cr.getColumnCount();
            String[] s = new String[cr.getColumnCount()-10+1];
            for (int i = 0; i <columnCount; i++) {
                if(i==0 || i==1 || i==2 || i==4 || i==5 || i==6 || i==8 || i==9 || i==10 || i==13){
                    continue;
                }
                s[j] = cr.get(i);
                if(i==7){
                    s[j]=s[j].replace("及以上","").replace("统招","").replace("学历","");
                }
                if(i==11){
                    if(s[j].contains("面议")){continue;}
                    s[j]=s[j].replace("万","0");
                    String[] s2 =s[j].split("-");
                    s[j]= String.valueOf((Double.valueOf(s2[0])*10)/12);
                    j+=1;
                    s[j]=String.valueOf(Double.valueOf(s2[1])/12);;
                }
                Pattern p = Pattern.compile("\\s+|\t+|\n\r");
                Matcher m = p.matcher(s[j]);
                s[j] = m.replaceAll("");
                j++;

            }
            cs.writeRecord(s);
        }

        cs.close();
    }
}