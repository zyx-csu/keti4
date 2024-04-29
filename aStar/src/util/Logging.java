/**
 * -*- coding: utf-8 -*-
 * @File    :   Logging.java
 * @Time    :   2024/4/26 22:30
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */

package util;

import java.io.IOException;
import java.util.logging.Formatter;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;

public class Logging {

    public static Logger getLogger(String logFilePath) {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger("logging");
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(logFilePath, true);
            fileHandler.setFormatter(new Formatter() {
                private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                public String format(LogRecord record) {
                    StringBuilder sb = new StringBuilder();
                    String dataFormat = this.sdf.format((record.getMillis()));
                    sb.append(dataFormat).append(" ");
                    sb.append("level:").append(record.getLevel()).append(" ");
                    sb.append(record.getMessage()).append("\n");

                    return sb.toString();
                }
            });

            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logger;
    }

}
