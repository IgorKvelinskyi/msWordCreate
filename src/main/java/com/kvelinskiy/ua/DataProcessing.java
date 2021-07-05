package com.kvelinskiy.ua;

import java.util.Arrays;
import java.util.List;

/**
 * @author Igor Kvelinskyi (igorkvjava@gmail.com)
 */
public class DataProcessing {
    public String changeData(List<List<String>> dataTable, String dataBody) {
        String[] initialData = {"111", "222", "333", "444", "555", "666", "777", "888"};
        String resultAll = "\n";
        for (List<String> lineTable : dataTable) {
            int count = 0;
            String result = dataBody;
            if (lineTable.size() == initialData.length) {
                for (String value : lineTable) {
                    result = result.replace(initialData[count], value);
                    count++;
                }
                resultAll += result;
            }
        }
        return resultAll;
    }
}
