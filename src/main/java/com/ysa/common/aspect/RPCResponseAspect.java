package com.ysa.common.aspect;

import com.ysa.common.error.YsaParamException;
import com.ysa.common.rpc.base.constant.RPCBaseConstant;
import com.ysa.common.rpc.base.model.RPCBaseResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ysa
 * @date 2020/7/14 18:41
 * @description
 */
@Aspect
@Slf4j
public class RPCResponseAspect {

    @Pointcut("execution(public com.ysa.common.rpc.base.model.RPCBaseResponseVO com.ysa.*.rpc..*(..) )")
    private void andRPCResponseMethod() {
    }

    @Around("andRPCResponseMethod()")
    public Object handleRPCResponseMethod(ProceedingJoinPoint pjp) {
        try {
            return pjp.proceed();
        } catch (YsaParamException e) {
            log.debug("ysa message {}", e.getMessage());
            return new RPCBaseResponseVO(RPCBaseConstant.PARAM_ERROR, e.getMessage());
        } catch (Throwable ex) {
            log.error("error", ex);
            return new RPCBaseResponseVO(RPCBaseConstant.SYSTEM_ERROR, "RPC服务器繁忙");
        }
    }
}
