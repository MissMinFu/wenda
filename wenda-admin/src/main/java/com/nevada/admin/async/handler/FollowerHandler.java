package com.nevada.admin.async.handler;

import com.nevada.admin.async.EventHandler;
import com.nevada.admin.async.EventModel;
import com.nevada.admin.async.EventType;

import java.util.List;

/**
 * @program:wenda
 * @description:
 * @author: nevada
 * @create: 2020-04-14 09:57
 **/
public class FollowerHandler implements EventHandler {

    @Override
    public void doHandler(EventModel model) {



    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return null;
    }
}
