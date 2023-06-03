import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject


RequestObject getSingleUser = findTestObject('QoinDigital/Get Single User'
	, [('userId') : userId, ('basedUrl') : GlobalVariable.basedUrl])

ResponseObject respSingleUser = WS.sendRequest(getSingleUser, FailureHandling.OPTIONAL)

JsonSlurper jsonSlurper = new JsonSlurper()

def jsonRespSingleUser = jsonSlurper.parseText(respSingleUser.getResponseText())
def respCode = respSingleUser.getStatusCode().toString()


if (respCode != '200') {
	
	KeywordUtil.markFailed('User Not Found')
	
	return respCode
	
} else {
	
	KeywordUtil.logInfo(
		'\n' + ('=' * 40)
		+'\nId		: '+ jsonRespSingleUser.data.id
		+'\nEmail		: '+ jsonRespSingleUser.data.email
		+'\nFirst Name	: '+ jsonRespSingleUser.data.first_name
		+'\nLast Name	: '+ jsonRespSingleUser.data.last_name
		+'\nAvatar		: '+ jsonRespSingleUser.data.avatar
		+ '\n' + ('=' * 40))
	
	return jsonRespSingleUser.data.email
	
}



