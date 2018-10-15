package org.mengyun.tcctransaction;

import org.mengyun.tcctransaction.api.TransactionXid;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 事务仓库，存放着所有TCC事务
 * Created by changmingxie on 11/12/15.
 */
public interface TransactionRepository {
    /* 创建事务 */
    int create(Transaction transaction);

    /* 更新事务 */
    int update(Transaction transaction);

    /* 删除某个事务 */
    int delete(Transaction transaction);

    /* 通过xid 获取所有事务 */
    Transaction findByXid(TransactionXid xid);

    /* 根据日期获取事务 */
    List<Transaction> findAllUnmodifiedSince(Date date);
}
