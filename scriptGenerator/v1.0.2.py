import xlrd
import os
import re


class ScriptGenerator():
    def __init__(self, paraExcel='paralist.xls', scriptTemplete='template.txt'):
        self.paraExcel = paraExcel
        self.scriptTemplete = scriptTemplete

    # format input data due to xlrd can not read int type
    def ctypeCheck(self, inputData):
        if type(inputData) == str:
            return inputData
        else:
            num = str(inputData).split('.')
            if num[1] == '0':
                return num[0]
            else:
                return str(inputData)

    # get data from excel
    def getDataFromExcel(self):
        varlist = []
        excelLocation = os.path.join(os.getcwd(), self.paraExcel)
        workBook = xlrd.open_workbook(excelLocation)
        table = workBook.sheet_by_index(0)
        for j in range(1, table.ncols):
            varDict = {}
            for i in range(len(table.row_values(0))):
                varDict[self.ctypeCheck(table.row_values(0)[i])] = self.ctypeCheck(table.row_values(j)[i])
            varlist.append(varDict)
        return varlist, table.row_values(0)

    def generateScript(self, ):
        excelData, paralist = self.getDataFromExcel()
        with open(self.scriptTemplete) as f:
            tempText = f.read()
        # print(tempText)
        scriptPath = os.path.join(os.getcwd(), 'script')
        if not os.path.exists(scriptPath):
            os.mkdir(scriptPath)
        os.chdir(scriptPath)
        for i in range(len(excelData)):
            scriptText = tempText
            for eachKey in paralist:
                patternString = '##' + eachKey + '##'
                scriptText, number = re.subn(patternString, excelData[i][eachKey], scriptText)
            scriptName = excelData[i]['SiteName']
            with open(scriptName + '.mos', 'w') as f:
                f.write(scriptText)
        print('\nfinish and the script stored in %s' % os.getcwd())


script = ScriptGenerator()
script.generateScript()
