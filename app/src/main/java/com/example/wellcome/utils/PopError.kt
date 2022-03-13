package com.example.wellcome.utils

class PopError ()
{
    var intType = 0
    var stringType = ""

    fun intTypeTest(intValue:Int): Boolean {
        intType = intValue
        return ((intType == 0)||(intType == null))
    }
    fun intTypeTest(intValue:Int,inValideValue: Int): Boolean {
        intType = intValue
        return ((intType == 0)||(intType == null)||(intType == inValideValue))
    }
    fun intTypeTestBigger(intValue:Int,inValideValue: Int): Boolean {
        intType = intValue
        return ((intType == 0)||(intType == null)||(intType > inValideValue))
    }
    fun intTypeTestSmaller(intValue:Int,inValideValue: Int): Boolean {
        intType = intValue
        return ((intType == 0)||(intType == null)||(intType < inValideValue))
    }
    fun stringTypeTest(stringValue: String): Boolean {
       stringType = stringValue
        return ((stringType == "")||(stringType == null))
    }
    fun stringTypeTest(stringValue:String, inValideValue:String): Boolean {
        stringType = stringValue
        return ((stringType == "")||(stringType == null)||(stringType == inValideValue))
    }


}