package com.example.atmsimulation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ATMService {

    @Value("${error.msg.code.001}")
    private String errMsg001;

    @Value("${error.msg.code.002}")
    private String errMsg002;

    @Value("${error.msg.code.003}")
    private String errMsg003;

    private void calTotalAmount(ATM atm){
        BigDecimal total = new BigDecimal(0);
        for(int a=0; a < atm.getCurrency().size(); a++){
            int currency = atm.getCurrency().get(a);
            int num = atm.getNoteOfCurrency().get(a);
            BigDecimal tmp = new BigDecimal(currency * num);
            total = total.add(tmp);

        }
        atm.setTotalAmount(total);
    }

    public void withdraw(ATM atm, BigDecimal amount) throws ATMException {

        List<Integer> noteOfWithdrawn = new ArrayList<>();

        if(amount.remainder(new BigDecimal(10)).compareTo(BigDecimal.ZERO) != 0 ){
            //the last digit of withdrawn amount is not zero
            throw new ATMException(errMsg001);
        }
        this.calTotalAmount(atm);

        if(amount.compareTo(atm.getTotalAmount()) > 0){
            //withdrawn amount > amount in atm
            throw new ATMException(errMsg002);
        }

        for(int a=0; a < atm.getCurrency().size(); a++){

            if(amount.compareTo(new BigDecimal(0)) > 0) {

                int note = amount.divide(new BigDecimal(atm.getCurrency().get(a))).intValue();
                if (note > 0) {
                    int numOfCurr = atm.getNoteOfCurrency().get(a);
                    if (note >= numOfCurr) {
                        noteOfWithdrawn.add(numOfCurr);
                        atm.getNoteOfCurrency().set(a, 0);
                    } else {
                        noteOfWithdrawn.add(note);
                        atm.getNoteOfCurrency().set(a, numOfCurr - note);
                    }

                    amount = amount.subtract(new BigDecimal(noteOfWithdrawn.get(a) * atm.getCurrency().get(a)));

                }else{
                    noteOfWithdrawn.add(0);
                }
            }else{
                noteOfWithdrawn.add(0);
            }

        }

        if(amount.compareTo(new BigDecimal(0)) > 0){
            // note not enough;
            throw new ATMException(errMsg003);
        }

        atm.setNoteOfWithdraw(noteOfWithdrawn);
        this.calTotalAmount(atm);

    }
}
