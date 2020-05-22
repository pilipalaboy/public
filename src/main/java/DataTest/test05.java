package DataTest;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class test05 {
    public static void main(String[] args) throws IOException {
        String filePath = "D:\\资料\\安装资料\\任务\\jobs5.csv";
        InputStream in = new FileInputStream(filePath);
        CsvReader cr = new CsvReader(in, Charset.forName("utf-8"));

        // 读表头
        cr.readHeaders();
        String filePath2 = "D:\\资料\\安装资料\\任务\\5.csv";
        // 创建CSV写对象
        CsvWriter cs = new CsvWriter(filePath2,',', Charset.forName("utf-8"));


        while(cr.readRecord()) {
            int j =0;
            String RawRecord = cr.getRawRecord();
            if(RawRecord.startsWith("company_financing_stage")){
                continue;
            }
            int columnCount = cr.getColumnCount();
            String[] s = new String[cr.getColumnCount()];
            for (int i = 0; i <columnCount; i++) {
                if(i==0 || i==1 || i==2 || i==4 || i==5 || i==6 || i==8 || i==9 || i==10 || i==13){
                    continue;
                }
                s[j]=cr.get(i);
                if(i==11){
                    if(s[j].contains("以下")){ continue; }
                    if(s[j].contains("面议")){continue;}
                    if(s[j].contains("以上")){continue;}
                    s[j]=s[j].replace("元/月","");
                    String[] s2 =s[j].split("-");
                    s[j]= String.valueOf(Double.valueOf(s2[0])/1000);
                    j+=1;
                    s[j]=String.valueOf(Double.valueOf(s2[1])/1000);;
                }
                j++;

            }
            cs.writeRecord(s);
        }



        cs.close();
    }
}
