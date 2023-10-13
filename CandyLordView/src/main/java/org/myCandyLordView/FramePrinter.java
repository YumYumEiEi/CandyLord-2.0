package org.myCandyLordView;

import org.myCandyLordView.records.CandyTransactionInfos;
import org.myCandyLordView.records.ChangeLocationInfos;
import org.myCandyLordView.records.StatusInfos;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FramePrinter {


    public void printMainScreen(StatusInfos infos){
        printFirstLine(infos);
        printCandyInfos(infos);
        printCashLine(infos);
        printDebtLine(infos);
        printBankLine(infos);
        printHealthLine(infos);
        printWeaponLine(infos);
        printFameLine(infos);
    }

    private void printFameLine(StatusInfos infos) {
        System.out.printf("Fame: %d%n%n%n", infos.fame());
    }

    private void printWeaponLine(StatusInfos infos) {
        System.out.printf("Weapon: %15s   Amount: %d%n", infos.weaponType(), infos.weaponAmount());
    }

    private void printHealthLine(StatusInfos infos) {
        System.out.printf("Health: %3d    Protection: %d%n", infos.health(), infos.protection());
    }

    private void printBankLine(StatusInfos infos) {
        System.out.printf("Bank: %12d%n%n", infos.bankMoney());
    }

    private void printDebtLine(StatusInfos infos) {
        System.out.printf("Debt: %12d   Timer: %2d   Anger: %d%n", infos.debt(), infos.timer(), infos.anger());
    }

    private void printCashLine(StatusInfos infos) {
        System.out.printf("Cash: %12d%n", infos.cash());
    }

    private void printCandyInfos(StatusInfos infos) {
        List<Map.Entry<String, Long>> candyPrices = infos.candyPrices().entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
        String tableBreak = "+-----------------------+------------+----------+--------+";
        System.out.println(tableBreak);
        System.out.println("|         Name          |   Price    |   Unit   | Amount |");
        System.out.println(tableBreak);
        candyPrices.forEach((entry) -> System.out.printf( "|   %-17s   |   %7d  |   cent   |   %2d   |%n" , entry.getKey(), entry.getValue(), infos.candyInventory().get(entry.getKey())));
        System.out.println(tableBreak);
    }

    private void printFirstLine(StatusInfos infos) {
        System.out.printf("Location: %-30s  Attention: %d%n", infos.locationName(), infos.attention());
    }

    private void fillLine(char[] line, int startPosition, String text) {
        char[] tmp = text.toCharArray();
        for (int i = 0; i < tmp.length; i++) {
            line[startPosition + i]  = tmp[i];
        }
    }

    public void printCandyBuyScreen(CandyTransactionInfos infos) {

    }

    public void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public void printLocationChangeScreen(ChangeLocationInfos infos) {
        clear();
        String tableBreak = "+---------------------------------------+---------+";
        System.out.printf("Where do you want to travel to? Cash: %d%n", infos.cash());
        System.out.println(tableBreak);
        System.out.println("|                 Name                  |  Price  |");
        System.out.println(tableBreak);
        int[] position = new int[]{1};
        infos.priceList().forEach((entry) -> System.out.printf("|   %d: %-30s   |   %3d   |%n", position[0]++, entry.getKey(), entry.getValue()));
        System.out.println(tableBreak);

    }
}
