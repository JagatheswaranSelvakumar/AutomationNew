package com.automation.framework.browserProfile;

import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxBrowserProfile extends BrowserProfile {
	protected FirefoxProfile profile;

	public FirefoxBrowserProfile() {
		profile = new FirefoxProfile();
	}

	@Override
	public Object createProfile() {
		return profile;
	}

}
