package com.tripadvisor.hotel.deals.cli;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultPromptProvider;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DealsCLIPromptProvider extends DefaultPromptProvider{
	public String getPrompt() {
        return "hotel-deals-shell>";
    }
 
    public String getProviderName() {
        return "Deals Search Prompt";
    }
}
