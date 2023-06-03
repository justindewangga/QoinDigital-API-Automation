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


RequestObject getListUser = findTestObject('QoinDigital/Get List User'
	, [('page') : page, ('basedUrl') : GlobalVariable.basedUrl])

ResponseObject respListUser = WS.sendRequest(getListUser, FailureHandling.OPTIONAL)

JsonSlurper jsonSlurper = new JsonSlurper()

def jsonRespListUser = jsonSlurper.parseText(respListUser.getResponseText())
def respCode = respListUser.getStatusCode().toString()

def log_info = '\n' +'=' * 40 + '\n'
log_info += 'List Of User \n'
log_info += '=' * 40 + '\n'
log_info += 'Page		: ' + jsonRespListUser.page + '\n'
log_info += 'Per page	: ' +  jsonRespListUser.per_page + '\n'
log_info += 'Total		: ' + jsonRespListUser.total + '\n'
log_info += 'Total Page	: ' + jsonRespListUser.total_pages + '\n'
log_info += 'Data:\n'
jsonRespListUser.data.each { item ->
	log_info += "id: ${item.id}, email: ${item.email}, first_name: ${item.first_name}, last_name: ${item.last_name}, avatar: ${item.avatar}\n"
}
log_info += '=' * 40


if (respCode != '200') {
	
	KeywordUtil.markFailed('Result Not Suitable')
	
} else {
	
	KeywordUtil.logInfo(log_info)
	
}




