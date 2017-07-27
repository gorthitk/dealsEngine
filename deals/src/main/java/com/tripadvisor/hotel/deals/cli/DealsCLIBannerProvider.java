package com.tripadvisor.hotel.deals.cli;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DealsCLIBannerProvider extends DefaultBannerProvider {
	public String getBanner() {
		StringBuffer buf = new StringBuffer();
		buf.append("=======================================").append(OsUtils.LINE_SEPARATOR);
		buf.append("*      Hotel Deals Provider Shell      *").append(OsUtils.LINE_SEPARATOR);
		buf.append("=======================================").append(OsUtils.LINE_SEPARATOR);
		buf.append("Version:").append(this.getVersion());
		return buf.toString();
	}

	public String getVersion() {
		return "1.0";
	}

	public String getWelcomeMessage() {
		return "This Shell provides a CLI to search for Best hotel deals";
	}

	public String getProviderName() {
		return "Deals Search Banner";
	}
}
