package org.mengyun.tcctransaction;

import java.io.Serializable;

/**
 *  对调用的某个方法的封装，用于调用某个方法
 * Created by changmingxie on 11/9/15.
 */
public class InvocationContext implements Serializable {

    private static final long serialVersionUID = -7969140711432461165L;
    private Class targetClass;

    private String methodName;

    private Class[] parameterTypes;

    private Object[] args;

    public InvocationContext() {

    }

    public InvocationContext(Class targetClass, String methodName, Class[] parameterTypes, Object... args) {
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.targetClass = targetClass;
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }
}
