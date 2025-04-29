package com.herokuapp.katalon_demo_cura
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import keyword.com.herokuapp.katalon_demo_cura.TestDataHelper
import keyword.com.herokuapp.katalon_demo_cura.RadioHelper
import org.openqa.selenium.Keys
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.Alert


class MakeAppointmentSteps {
	// Instantiate the helper class
	TestDataHelper dataHelper = new TestDataHelper()
	RadioHelper radioHelper = new RadioHelper()

	@Given("I already logged in")
	public void i_already_logged_in() {
		WebUI.openBrowser('')
		WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')
		WebUI.maximizeWindow()
		WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment'))
		WebUI.setText(findTestObject('Object Repository/Page_CURA Healthcare Service/input_Username_username'), "John Doe")
		WebUI.setText(findTestObject('Object Repository/Page_CURA Healthcare Service/input_Password_password'), "ThisIsNotAPassword")
		WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/button_Login'))

	}

	@When("I make appointment and fill all the fields with: (.*)")
	public void i_make_appointment_and_fill_all_the_fields_with_datafile(String dataFile) {
		List<Map<String, String>> dataMap = dataHelper.convertDataFileToListOfMaps(dataFile)
		for (Map<String, String> data : dataMap) {
			WebUI.selectOptionByValue(findTestObject('Object Repository/Make_Appointment/select_facility'),data.get("facility"), false)
			if (data.get("check_list_apply")== ("true").toLowerCase()) {
				WebUI.click(findTestObject("Object Repository/Make_Appointment/input_Apply for hospital readmission_hospital_readmission"))
			}
			radioHelper.selectHealthcareProgram(data.get("healthcare_program"))
			WebUI.delay(1)
			println("visitdate "+ data.get("visit_date"))
//			WebUI.click(findTestObject("Object Repository/Make_Appointment/input_txt_VisitDate"))
//			WebUI.clearText(findTestObject("Object Repository/Make_Appointment/input_txt_VisitDate"))
			WebUI.setText(findTestObject("Object Repository/Make_Appointment/input_txt_VisitDate"), data.get("visit_date"))
//			WebUI.delay(1)
			WebUI.setText(findTestObject("Object Repository/Make_Appointment/textarea_Comment_comment"), data.get("comment"))
			WebUI.click(findTestObject("Object Repository/Make_Appointment/button_Book Appointment"))
		}
	}

	@Then("I verify the text: (.*) showed")
	public void i_verify_the_text_succesText_showed(text) {
		//WebUI.verifyElementVisible(findTestObject('Object Repository/Page_CURA Healthcare Service/h2_Make Appointment'))
		boolean isPresentSuccessText = WebUI.verifyTextPresent(text, false)
		if (!isPresentSuccessText) {
			assert false: "Error, Success Text is not showed"
		}
		WebUI.closeBrowser()
	}
}