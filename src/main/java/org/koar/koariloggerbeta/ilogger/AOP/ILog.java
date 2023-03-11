package org.koar.koariloggerbeta.ilogger.AOP;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
/**
 * Annotation for logging methods by only providing level of severity.
 * <br>
 * use {@link org.koar.koariloggerbeta.ilogger.LoggingContext LoggingContext.put("key","response")} method to log http responses or messages in general.

 * <ul>
 * <li> level : for choosing the level of severity provided by {@link Levels}
 * </li>
 * <li> keys : it is not necessary, but can help  remove the elements from the context. Provide the list of the keys you have used with
 * {@link org.koar.koariloggerbeta.ilogger.LoggingContext LoggingContext.put("key","response")}
 * </li>
 * </ul>
 * @author <a href="https://github.com/abderox">abderox</a>
 */
@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ILog {

    Levels  level() default Levels.INFO;
    String[] keys() default {};

}
