package org.myCandyLordView.records;

import java.util.Map;
import java.util.TreeMap;

public record CandyTransactionInfos(TreeMap<String, Long> candyPriceList, Map<String, Integer> candyInventory) {
}
