package org.mengyun.tcctransaction.recover;

import java.util.Set;

/**
 * 恢复配置，用于配置TCC事务的cancel阶段
 * Created by changming.xie on 6/1/16.
 */
public interface RecoverConfig {
    /*最大重试次数*/
    public int getMaxRetryCount();

    /*恢复周期，重试间隔？*/
    public int getRecoverDuration();

    /*Cron表达式，跟quartz相似，用于定时执行*/
    public String getCronExpression();

    public Set<Class<? extends Exception>> getDelayCancelExceptions();

    public void setDelayCancelExceptions(Set<Class<? extends Exception>> delayRecoverExceptions);

    public int getAsyncTerminateThreadPoolSize();
}
