package org.myCandyLordController;

import org.candyLordModel.api.CandyLordModelApi;
import org.myCandyLordView.records.*;
import org.myCandyLordView.CandyLordViewApi;

import java.util.*;

public class MyCandyLordController {

    private final CandyLordModelApi model;
    private final CandyLordViewApi view;

    public static void main(String[] args) {
        MyCandyLordController controller = new MyCandyLordController();
    }

    public MyCandyLordController() {
        this.view = CandyLordViewApi.getInstance();
        this.model = CandyLordModelApi.getInstance("Peter");
        startGame();
    }

    private void startGame() {
        boolean goOn = true;
        do {
            view.printStatus(createStatusInfos());
            goOn = switch (view.askForAction()) {
                case "Buy Candy" -> {
                    CandyTransactionInput input = createCandyTransactionInput();
                    try {
                        model.buyCandy(input.name(), input.amount());
                        model.nextDay();
                    } catch (Exception e) {
                        view.showMessage(e.getMessage());
                    }finally {
                        yield true;
                    }
                }
                case "Sell Candy" -> {
                    CandyTransactionInput input = createCandyTransactionInput();
                    try {
                        model.sellCandy(input.name(), input.amount());
                        model.nextDay();
                    } catch (Exception e) {
                        view.showMessage(e.getMessage());
                    }finally {
                        yield true;
                    }
                }
                case "Change Location" -> {
                    ChangeLocationInput input = view.askForLocationChangeInput(createChangeLocationInfos());
                    try {
                        model.changeLocation(input.locationName());
                        model.nextDay();
                    } catch (Exception e) {
                        view.showMessage(e.getMessage());
                    }finally {
                        yield true;
                    }
                }
                default -> false;
            };
        } while (goOn);
    }

    private ChangeLocationInfos createChangeLocationInfos() {
        List<Map.Entry<String, Long>> priceList = model.getLocationPriceList().entrySet().stream().toList();
        long cash = model.getCash();
        String currendLocation = model.getPlayerLocationName();
        return new ChangeLocationInfos(priceList, currendLocation, cash);
    }

    private CandyTransactionInput createCandyTransactionInput() {
        TreeMap<String, Long> priceList = model.getCandyPriceList();
        Map<String, Integer> candyInventory = model.getCandyInventory();
        return view.askBuyInformations(new CandyTransactionInfos(priceList, candyInventory));
    }

    private StatusInfos createStatusInfos() {
        return new StatusInfos(
                model.getCandyInventory(),
                model.getCandyPriceList(),
                model.getCharacterName(),
                model.getPlayerLocationName(),
                model.getWeaponType(),
                model.getJanitorAnger(),
                model.getAttention(),
                model.getStatusPoints(),
                model.getDebtTimer(),
                model.getHealth(),
                model.getProtection(),
                model.getWeaponAmount(),
                model.getCash(),
                model.getPiggyBankContent(),
                model.getDebt());
    }


}
