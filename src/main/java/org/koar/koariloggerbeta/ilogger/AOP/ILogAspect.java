package org.koar.koariloggerbeta.ilogger.AOP;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.koar.koariloggerbeta.ilogger.CustomFormatter;
import org.koar.koariloggerbeta.ilogger.CustomHandler;
import org.koar.koariloggerbeta.ilogger.LoggingContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Aspect
@EnableAspectJAutoProxy
@ConditionalOnProperty(name = "ilogger.annotation.enabled", havingValue = "true" ,matchIfMissing = true)
public class ILogAspect {


    @Around(value = "@annotation(iLog)", argNames = "joinPoint,iLog")
    public Object logAround(ProceedingJoinPoint joinPoint, ILog iLog) throws Throwable {
//        System.out.println(count++);
        Logger logger = Logger.getLogger(String.valueOf(joinPoint.getTarget().getClass()));
        String level = iLog.level().name().equals(Levels.WTF.name()) ? Levels.SEVERE.name() : iLog.level().name();
        logger.setUseParentHandlers(false);
        CustomHandler handler = new CustomHandler();
        handler.setFormatter(new CustomFormatter());
        logger.addHandler(handler);

        try {
            Object o = joinPoint.proceed();
            logger.log(Level.parse(level), String.format("Method %s() -> ", joinPoint.getSignature().getName()));
            logger.removeHandler(handler);

            Map<String, String> context = LoggingContext.get();
            if (context != null) {
                for (String key : iLog.keys()) {
                    if (LoggingContext.get().containsKey(key)) {
                        LoggingContext.remove(key);
                    }
                }
            }

            return o;

        } catch (Throwable e) {
            logger.log(Level.parse(level), String.format("Method %s is called with args %s", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs())));
            throw e;
        }
    }


}
