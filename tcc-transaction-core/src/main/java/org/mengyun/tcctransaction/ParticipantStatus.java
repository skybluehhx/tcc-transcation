package org.mengyun.tcctransaction;

/**
 * 表明参与者的状态，用于回滚因为在事务参与过程中，需要回滚时，
 * 通常是部分参与者成功，部分参与者失败，因此需要给参与者一个
 * 状态标志，来表明参与者所在的状态以此表明是否应该执行补偿方法
 * <p>
 * 在改参与者相关的事务提交完成后，参与者状态应该变成JOIN,如果在
 * 后续过程中，抛出异常，所有处于JOIN状态的参与者都应该对自己执行
 * “回滚操作”，在TCC事务完成后，参与者状态变成FINISH状态，只有
 * 所有参与者状态处于完成状态，TCC事务才能够算完成
 * <p>
 * <p>
 * Created by lin on 2018/10/15.
 */
public enum ParticipantStatus {

    PREPARE(1),//表明参与者准已经备完毕，可以参与进事务中
    JOIN(2),//表明参与者已经加入到事务当中 执行操作后，commit之前
    FINISH(3),//表明参与者已近完成了参与；commit之后置为该状态
    ROLLBOCK(0),//回滚状态，表明该参与者已经回滚 ；回滚之后置位该状态，FINISH和ROLLBOCK只能出现一个
    UNKNOW(-1);//未知状态，正常中不应该出现
    private Integer status;

    private ParticipantStatus(Integer status) {
        this.status = status;
    }

    public static ParticipantStatus valueOf(Integer status) {
        switch (status) {
            case 0:
                return ROLLBOCK;
            case 1:
                return PREPARE;
            case 2:
                return JOIN;
            case 3:
                return FINISH;
            default:
                return UNKNOW;

        }


    }

    public Integer getValue() {
        return status;
    }


}
