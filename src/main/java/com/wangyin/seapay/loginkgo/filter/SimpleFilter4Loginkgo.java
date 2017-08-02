package com.wangyin.seapay.loginkgo.filter;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.wangyin.seapay.loginkgo.constant.Markers;

/**
 * Created by hanxiaofei on 2017/7/4.
 */
public class SimpleFilter4Loginkgo extends Filter {
    @Override
    public FilterReply decide(Object o) {
        LoggingEvent event = (LoggingEvent)o;

        if (event.getMarker().contains(Markers._SJ)) {
            return FilterReply.ACCEPT;
        } else {
            return FilterReply.DENY;
        }
    }
}
