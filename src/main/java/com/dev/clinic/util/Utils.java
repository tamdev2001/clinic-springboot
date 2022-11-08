package com.dev.clinic.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Utils {
    public List<Object[]> customListStatsMonth(List<Object[]> list) {
        boolean flag = false;
        if (list != null) {
            for (int i = 1; i <= 12; i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (i == (int) list.get(j)[0]) {
                        flag = true;
                        break;
                    }
                }
                if (flag == false) {
                    Object[] term = {i, 0};
                    list.add(term);
                }
                flag = false;
            }
            Collections.sort(list, (Object[] a1, Object[] a2) -> (int) a1[0] - (int) a2[0]);
        }
        return list;
    }
}
