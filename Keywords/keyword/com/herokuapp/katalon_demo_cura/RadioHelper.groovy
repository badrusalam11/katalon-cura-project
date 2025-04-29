package keyword.com.herokuapp.katalon_demo_cura

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class RadioHelper {

    /**
     * Selects a healthcare program radio button using XPath with contains() based on the label.
     * Example: Medicare, Medicaid, or None
     */
    @Keyword
    def selectHealthcareProgram(String programName) {
        String xpath = "//input[@name='programs' and contains(@value, '${programName}')]"

        TestObject dynamicRadio = new TestObject("dynamicProgramRadio")
        dynamicRadio.addProperty("xpath", ConditionType.EQUALS, xpath)

        WebUI.waitForElementVisible(dynamicRadio, 5)
        WebUI.click(dynamicRadio)

        // Optional assertion to verify it's selected
        assert WebUI.getAttribute(dynamicRadio, 'checked') == 'true'
    }
}
