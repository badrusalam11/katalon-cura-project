package keyword.com.herokuapp.katalon_demo_cura

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class TestDataHelper {
	@Keyword
	List<Map <String, String>> convertDataFileToListOfMaps(String dataFileName){
		TestData data = TestDataFactory.findTestData(dataFileName)
		// define empty list of result
		List<Map<String, String>> result = []
		// define row count
		int rowCount = data.getRowNumbers()
		// define define column name
		List<String> columns = data.getColumnNames()
		for(int i=1; i <= rowCount; i++) {
			Map<String, String> rowMap = [:]
			for (column in columns) {
				rowMap[column] = data.getValue(column, i)
			}
			result.add(rowMap)
		}
		return result
	}
}
