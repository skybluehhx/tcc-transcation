package org.mengyun.tcctransaction.api;

/**
 * Created by changmingxie on 10/28/15.
 * 因为使用TCC框架，事务主要分3个部分，try阶段，commit阶段，cancel阶段
 * 因此事务状态也分为3个部分
 */
public enum TransactionStatus {

    TRYING(1), CONFIRMING(2), CANCELLING(3);

    private int id;

     TransactionStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TransactionStatus valueOf(int id) {

        switch (id) {
            case 1:
                return TRYING;
            case 2:
                return CONFIRMING;
            default:
                return CANCELLING;
        }
    }

}
