package com.visualization.epidemic.Task;


import cn.hutool.core.io.FileUtil;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvUtil;
import com.visualization.epidemic.Service.HistoryService;
import com.visualization.epidemic.pojo.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReadCsv {
    public static String fileName = "xxxx";

    @Autowired
    private HistoryService historyService;
    @Autowired
    private History history;

    public void test(int row, int col1, int col2, int col3, int col4) throws IOException {
        BufferedReader read = new BufferedReader(new FileReader("/data/temp/" + fileName));
        String line = null;
        int index = 0;
        while ((line = read.readLine()) != null) {
            ++index;
            if (index == row) {
                String[] item = line.split(",");
                if (item.length >= col4) {
                    String last1 = item[col1];//这就是你要的数据了
                    long last2 = Integer.parseInt(item[col2]);
                    long last3 = Integer.parseInt(item[col3]);
                    long last4 = Integer.parseInt(item[col4]);

                    history.setUpdate_time(last1);
                    history.setCumulative_cases(last2);
                    history.setCumulative_deaths(last3);
                    history.setCumulative_recovered(last4);

                    historyService.saveHistory(history);
                }
            }
            //int value = Integer.parseInt(last);//如果是数值，可以转化为数值

        }
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 24 * 60 * 60 * 1000)
    public void callme() throws IOException {//远程文件路径
        String filePath = "https://health-infobase.canada.ca/src/data/covidLive/covid19-download.csv";

//定义要保存的文件路径
        String savePath = "/data/temp/" + fileName;
        File file = new File(savePath);

//如果文件目录不出在则创建目录 *getParentFile()*
        if (!file.getParentFile().exists()) {
            //判断文件目录是否存在
            file.getParentFile().mkdirs();//创建目录
        }
        try {
            URL url = new URL(filePath);
            //字节输入流
            InputStream is = url.openStream();
            //字节流转字符流
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            //再转缓冲流  提高读取效率
            BufferedReader br = new BufferedReader(isr);
            //文件输出流
            OutputStream output = new FileOutputStream(file);
            //字符缓冲流输出                                                      转化流
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output));

            //使用char 数组传输    -----字节流byte数组
            char[] chs = new char[1024];
            //标记
            int len = 0;

            while ((len = br.read(chs)) != -1) {
                // read() 方法，读取输入流的下一个字节，返回一个0-255之间的int类型整数。如果到达流的末端，返回-1
                bw.write(chs, 0, len);//写入文件
                bw.flush();//清除缓存
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        CsvReader reader = CsvUtil.getReader();
        //从文件中读取CSV数据
        File file1 = FileUtil.file("/data/temp/" + fileName);
        CsvData data = reader.read(file1);
        int rowCount = data.getRowCount();
        test(rowCount - 1 + 1, 3, 5, 7, 11);
    }
}
