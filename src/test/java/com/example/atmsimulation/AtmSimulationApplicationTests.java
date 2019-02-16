package com.example.atmsimulation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtmSimulationApplicationTests {

    @Autowired
    ATMService atmService;

    @Test
    public void withdrawn_fiveHundred_thenOK() throws ATMException {
        BigDecimal withdrawnAmount = new BigDecimal(500);
        ATM atm = new ATM();
        this.atmService.withdraw(atm, withdrawnAmount);
        int fiveHundred = atm.getNoteOfWithdraw().get(1);
        Assert.assertEquals(1,(int) atm.getNoteOfWithdraw().get(1));
        Assert.assertEquals(11,(int) atm.getNoteOfCurrency().get(1));
    }

    @Test(expected = ATMException.class)
    public void withdrawn_exceedAmount_thenNotOK() throws ATMException {
        BigDecimal withdrawnAmount = new BigDecimal(1000000);
        ATM atm = new ATM();
        this.atmService.withdraw(atm, withdrawnAmount);
    }

    @Test(expected = ATMException.class)
    public void withdrawn_lastdigitIsNotZero_thenNotOk()throws ATMException{
        BigDecimal withdrawnAmount = new BigDecimal(731);
        ATM atm = new ATM();
        this.atmService.withdraw(atm, withdrawnAmount);
    }

}

