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



class LoginSteps {

	@Given("I want to open the cura web application")
	public void i_want_to_open_the_cura_web_application() {
		WebUI.openBrowser('')

		WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')

		WebUI.maximizeWindow()
	}

	@When("I click Button Make Appointment")
	public void i_click_Button_Make_Appointment() {
		WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment'))
	}

	@When("I fill the username: (.*) and password (.*)")
	public void i_fill_the_username_John_Doe_and_password_ThisIsNotAPassword(username, password) {
		WebUI.setText(findTestObject('Object Repository/Page_CURA Healthcare Service/input_Username_username'), username)

		WebUI.doubleClick(findTestObject('Object Repository/Page_CURA Healthcare Service/input_Demo account_form-control'))

		WebUI.setText(findTestObject('Object Repository/Page_CURA Healthcare Service/input_Password_password'), password)

		WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/button_Login'))
	}

	@Then("I verify the text (.*) showed")
	public void i_verify_the_text_Make_Appointment_showed(text) {

		//WebUI.verifyElementVisible(findTestObject('Object Repository/Page_CURA Healthcare Service/h2_Make Appointment'))
		boolean isPresentSuccessText = WebUI.verifyTextPresent(text, false)
		if (!isPresentSuccessText) {
			assert false: "Error, Success Text is not showed"
		}
		WebUI.closeBrowser()
	}


}