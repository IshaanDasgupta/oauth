package com.Bank.resourceServer.DTO;

public class RemoveBalance {
    private int balanceToWithdraw;

    public int getBalanceToRemove() {
        return balanceToWithdraw;
    }

    public void setBalanceToRemove(int balanceToRemove) {
        this.balanceToWithdraw = balanceToRemove;
    }
}
