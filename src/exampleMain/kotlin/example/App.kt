package example

import extlib.*
import kotlinx.cinterop.*

fun main(args: Array<String>) {
	
	println(getStringFromLib()!!.toKString());
	
}