package DataTest;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;


public class test01{
    public static void main(String[] args) throws IOException {
        String filePath = "D:\\资料\\安装资料\\任务\\jobs1.csv";
        InputStream in = new FileInputStream(filePath);
        CsvReader cr = new CsvReader(in, Charset.forName("utf-8"));

        // 读表头
        cr.readHeaders();
        String filePath2 = "D:\\资料\\安装资料\\任务\\1.csv";
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
                if(i==7){
                    s[j]= s[j].replace("学历：","");
                }
                if(i==11){
                    s[j]=s[j].replace("K","");
                    String[] s2 =s[j].split("-");
                    s[j]=s2[0];
                    j+=1;
                    s[j]=s2[1];
                }
                j++;

            }
            cs.writeRecord(s);
        }



        cs.close();
    }
}
