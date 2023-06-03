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



def email = WS.callTestCase(findTestCase('Get Single User'),
	[('userId') : userId], FailureHandling.STOP_ON_FAILURE)


RequestObject registerUser = findTestObject('QoinDigital/Post Register Successful'
	, [('email') : email, ('password') : password, ('basedUrl') : GlobalVariable.basedUrl])

ResponseObject respRegisterUser = WS.sendRequest(registerUser, FailureHandling.OPTIONAL)

JsonSlurper jsonSlurper = new JsonSlurper()

def jsonRespRegisterUser = jsonSlurper.parseText(respRegisterUser.getResponseText())
def respCode = respRegisterUser.getStatusCode().toString()


if (respCode != '200') {
	
	KeywordUtil.markFailed(
		'\n' + ('=' * 40)
		+'\nError		: '+ jsonRespRegisterUser.error
		+ '\n' + ('=' * 40))
		
} else {
	
	KeywordUtil.logInfo(
		'\n' + ('=' * 40)
		+'\nRegister of Email ' + email + ' was Success'
		+ '\n' + ('=' * 40)
		+'\nId		: '+ jsonRespRegisterUser.id
		+'\nToken		: '+ jsonRespRegisterUser.token
		+ '\n' + ('=' * 40))

	
}
