package utils

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling
import org.openqa.selenium.StaleElementReferenceException

class ObjectUtils {

	/**
	 * Automatically creates and returns a TestObject without explicit initiation.
	 * @param selectorType e.g., 'id', 'xpath', 'name', etc.
	 * @param value the locator value (e.g., '//*[@id="password"]')
	 * @return TestObject
	 */
	@Keyword
	static TestObject findCustomObject(String selectorType, String value) {
		return new TestObject().addProperty(selectorType, ConditionType.EQUALS, value)
	}

	/**
	 * Safely clicks a TestObject by waiting for it to be visible and clickable.
	 * Retries once on StaleElementReferenceException.
	 */
	@Keyword
	static void safeClick(TestObject to, int timeout = 10) {
		try {
			WebUI.waitForElementVisible(to, timeout)
			WebUI.waitForElementClickable(to, timeout)
			WebUI.click(to)
		} catch (StaleElementReferenceException | Exception e) {
			WebUI.comment("Retrying click due to: " + e.getMessage())
			WebUI.waitForElementVisible(to, timeout)
			WebUI.waitForElementClickable(to, timeout)
			WebUI.click(to, FailureHandling.STOP_ON_FAILURE)
		}
	}

	/**
	 * Safely sets text in an input by waiting for visibility and enabled state.
	 * Retries once on failure or stale reference.
	 */
	@Keyword
	static void safeSetText(TestObject to, String text, int timeout = 10) {
		try {
			WebUI.waitForElementVisible(to, timeout)
			WebUI.waitForElementClickable(to, timeout)
			WebUI.setText(to, text)
		} catch (StaleElementReferenceException | Exception e) {
			WebUI.comment("Retrying setText due to: " + e.getMessage())
			WebUI.waitForElementVisible(to, timeout)
			WebUI.waitForElementClickable(to, timeout)
			WebUI.setText(to, text, FailureHandling.STOP_ON_FAILURE)
		}
	}
}
