package org.myCandyLordView.records;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record ChangeLocationInfos(
        List<Map.Entry<String, Long>> priceList,
        String currendLocation,
        long cash
) {
}
