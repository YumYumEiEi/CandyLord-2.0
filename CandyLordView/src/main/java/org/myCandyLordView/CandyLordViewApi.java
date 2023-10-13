package org.myCandyLordView;

import org.myCandyLordView.records.*;

public interface CandyLordViewApi {
    void printStatus(StatusInfos info);

    String askForAction();

    CandyTransactionInput askBuyInformations(CandyTransactionInfos infos);

    void showMessage(String message);

    ChangeLocationInput askForLocationChangeInput(ChangeLocationInfos infos);

    static CandyLordViewApi getInstance() {
        return MyCandyLordView.getInstance();
    }
}
