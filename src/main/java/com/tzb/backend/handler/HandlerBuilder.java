package com.tzb.backend.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用于构建处理器链的建造者类。
 * 该类提供了一种方便的方式来构建处理器链，并依次对目标对象进行处理。
 * <p>
 * 示例使用：
 * <pre>
 *     {@code
 *     HandlerBuilder builder = new HandlerBuilder<>();
 *     builder.addHandler(new ValidateOrderHandler(order))
 *            .addHandler(new ProcessOrderHandler(order))
 *            .addHandler(new NotifyOrderHandler(order))
 *            .build();
 *     }
 * </pre>
 *
 * @see com.tzb.backend.handler.Handler 处理器接口，用于定义处理目标对象的方法。
 */
public class HandlerBuilder {
    private final List<Handler> handlers = new ArrayList<>();

    /**
     * 添加一个处理器到处理器链中，并依次对目标对象进行处理。
     *
     * @param handler 要添加的处理器
     * @return 处理器链的建造者
     */
    public HandlerBuilder addHandler(Handler handler) {
        handlers.add(handler);
        return this;
    }

    /**
     * 构建处理器链，并依次对目标对象进行处理。
     */
    public void build() {
        for (Handler handler : handlers) {
            handler.process();
        }
    }
}
