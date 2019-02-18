package tech.verenti.webdriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WebDriverInvoker implements InvocationHandler{

    private static final String QUIT_METHOD = "quit";

    private static final String CLOSE_METHOD = "close";

    private static final String GET_SESSIONID_METHOD = "getSessionId";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ThreadLocal<WebDriver> threadDriver;

    public WebDriverInvoker(ThreadLocal<WebDriver> threadDriver) {
        this.threadDriver = threadDriver;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Object underlyingDriver = threadDriver.get();
        if (underlyingDriver == null) {
            throw new IllegalStateException("WebDriver has not been initialised (in this thread)");
        } else {
            return invokeNormally(method, args, underlyingDriver);
        }
    }

    private Object invokeNormally(Method method, Object[] args, Object underlyingDriver) throws Throwable {
        String methodName = method.getName();
        try {
            log.debug("Invoking webDriver#" + methodName);

            return method.invoke(underlyingDriver, args);
        } finally {
            switch (methodName) {
                case QUIT_METHOD:
                    log.debug("WebDriver quit, dropping instance for this thread");
                    threadDriver.remove();
                    break;
                case CLOSE_METHOD:
                    log.error("Window close, I don't know whether to drop the browser or not! So I didn't. Please stop doing that.");
                    break;
                default:
                    break;
            }
        }
    }
}
