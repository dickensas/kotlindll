package example

import extlib.*
import kotlinx.cinterop.*
 
typealias SomeFun = CFunction<() -> CPointer<ByteVar>?>

fun main(args: Array<String>) {

	memScoped {
       val lib:CPointer<libmyext_ExportedSymbols>? = libmyext_symbols()
       
       //Direct DLL method invoke using Kotlin COpaquePointer and reinterpret
       val s = lib!!.pointed.kotlin.root.example.someLibraryMethod!!.reinterpret<SomeFun>()
	   println(s.invoke()!!.toKString())
	
    }
	
	//C stub from .def file - works fine
	println(getStringFromLib()!!.toKString());
	
}