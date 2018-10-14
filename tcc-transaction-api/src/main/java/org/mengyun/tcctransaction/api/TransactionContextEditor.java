package org.mengyun.tcctransaction.api;

import java.lang.reflect.Method;

/**事务上下文,用于获取TransactionContext,在Compensable注解中有实现
 * Created by changming.xie on 1/18/17.
 */
public interface TransactionContextEditor {
    /*根据*/
    public TransactionContext get(Object target, Method method, Object[] args);

    public void set(TransactionContext transactionContext, Object target, Method method, Object[] args);

}
