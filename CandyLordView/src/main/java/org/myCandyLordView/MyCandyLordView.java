package org.myCandyLordView;

import org.myCandyLordView.records.*;

import java.io.IOException;
import java.util.*;

class MyCandyLordView implements CandyLordViewApi{

    private MyCandyLordView(){
        framePrinter = new FramePrinter();
    }

    private static MyCandyLordView instance;
    private final FramePrinter framePrinter;

    @Override
    public void printStatus(StatusInfos infos) {
        framePrinter.clear();
        framePrinter.printMainScreen(infos);
    }

    @Override
    public String askForAction() {

        System.out.println("What do you want to do?");
        System.out.println("""
                1: Buy candy           4: Go to piggy bank
                2: Sell candy          5: Visit janitor
                3: Change location     6: Go to hospital
                """);
        return switch (new Scanner(System.in).nextInt()){
            case 1 -> "Buy Candy";
            case 2 -> "Sell Candy";
            case 3 -> "Change Location";
            default -> "";
        };
    }

    @Override
    public CandyTransactionInput askBuyInformations(CandyTransactionInfos infos) {
        framePrinter.clear();
        framePrinter.printCandyBuyScreen(infos);
        System.out.println("Choose a candy!");
        int[] number = new int[]{1};
        List<Map.Entry<String, Long>> candyPrices = infos.candyPriceList().entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
        String tableBreak = "+---------------------------+------------+----------+--------+";
        System.out.println(tableBreak);
        System.out.println("|           Name            |    Price   |   Unit   | Amount |");
        System.out.println(tableBreak);
        candyPrices.forEach((entry) -> System.out.printf( "|   %2d: %-17s   |   %-7d  |   cent   |   %2d   |%n" , number[0]++, entry.getKey(), entry.getValue(), infos.candyInventory().get(entry.getKey())));
        System.out.println(tableBreak);
        String candy = switch (new Scanner(System.in).nextInt()){
            case 1 -> candyPrices.get(0).getKey();
            case 2 -> candyPrices.get(1).getKey();
            case 3 -> candyPrices.get(2).getKey();
            case 4 -> candyPrices.get(3).getKey();
            case 5 -> candyPrices.get(4).getKey();
            case 6 -> candyPrices.get(5).getKey();
            case 7 -> candyPrices.get(6).getKey();
            case 8 -> candyPrices.get(7).getKey();
            default -> "";
        };

        if(candy.equals("")){
            return new CandyTransactionInput("", 0);
        }
        System.out.println("How much?");
        int amount = new Scanner(System.in).nextInt();
        return new CandyTransactionInput(candy, amount);
    }

    @Override
    public void showMessage(String message) {
        System.err.println(message);
        System.out.println("-- Press Enter to continue --");
        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ChangeLocationInput askForLocationChangeInput(ChangeLocationInfos infos) {
        framePrinter.printLocationChangeScreen(infos);
        String newLocationName = switch (new Scanner(System.in).nextInt()){
            case 1 -> infos.priceList().get(0).getKey();
            case 2 -> infos.priceList().get(1).getKey();
            case 3 -> infos.priceList().get(2).getKey();
            case 4 -> infos.priceList().get(3).getKey();
            case 5 -> infos.priceList().get(4).getKey();
            case 6 -> infos.priceList().get(5).getKey();
            default -> throw new IllegalStateException("Unexpected value");
        };
        return new ChangeLocationInput(newLocationName);
    }

    public static MyCandyLordView getInstance(){
        if(instance == null){
            instance = new MyCandyLordView();
        }
        return instance;
    }
}
