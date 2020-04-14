package com.nevada.admin.async;

import java.util.List;

/**
 * @program:wenda
 * @description:
 * @author: nevada
 * @create: 2020-04-13 11:36
 **/
public interface EventHandler {

    void doHandler(EventModel model);
    List<EventType> getSupportEventTypes();
}
