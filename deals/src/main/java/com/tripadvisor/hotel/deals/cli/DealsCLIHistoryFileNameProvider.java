package com.tripadvisor.hotel.deals.cli;

import org.springframework.shell.plugin.support.DefaultHistoryFileNameProvider;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DealsCLIHistoryFileNameProvider extends DefaultHistoryFileNameProvider {
	public String getHistoryFileName() {
        return "deals-history.log";
    }
 
    public String getProviderName() {
        return "Deals Search History";
    }
}
