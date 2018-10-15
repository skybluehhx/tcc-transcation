package org.mengyun.tcctransaction.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * Created by changmingxie on 10/25/15.
 *///可补偿事务TCC注解，标有该注解的方法，即代表使用tcc事务
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Compensable {
   /*事务的传播行为*/
    public Propagation propagation() default Propagation.REQUIRED;
  /*TCC阶段的confirm阶段执行的方法*/
    public String confirmMethod() default "";
 /*TCC阶段cancel阶段执行的方法*/
    public String cancelMethod() default "";

    public Class<? extends TransactionContextEditor> transactionContextEditor() default DefaultTransactionContextEditor.class;
   /*是否开启异步验证*/
    public boolean asyncConfirm() default false;
/*是否开启异步取消*/
    public boolean asyncCancel() default false;

    class NullableTransactionContextEditor implements TransactionContextEditor {


        public TransactionContext get(Object target, Method method, Object[] args) {
            return null;
        }


        public void set(TransactionContext transactionContext, Object target, Method method, Object[] args) {

        }
    }

    class DefaultTransactionContextEditor implements TransactionContextEditor {


        public TransactionContext get(Object target, Method method, Object[] args) {
            int position = getTransactionContextParamPosition(method.getParameterTypes());

            if (position >= 0) {
                return (TransactionContext) args[position];
            }

            return null;
        }


        public void set(TransactionContext transactionContext, Object target, Method method, Object[] args) {

            int position = getTransactionContextParamPosition(method.getParameterTypes());
            if (position >= 0) {
                args[position] = transactionContext;
            }
        }

        public static int getTransactionContextParamPosition(Class<?>[] parameterTypes) {

            int position = -1;

            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i].equals(org.mengyun.tcctransaction.api.TransactionContext.class)) {
                    position = i;
                    break;
                }
            }
            return position;
        }

        public static TransactionContext getTransactionContextFromArgs(Object[] args) {

            TransactionContext transactionContext = null;

            for (Object arg : args) {
                if (arg != null && org.mengyun.tcctransaction.api.TransactionContext.class.isAssignableFrom(arg.getClass())) {

                    transactionContext = (org.mengyun.tcctransaction.api.TransactionContext) arg;
                }
            }

            return transactionContext;
        }
    }
}