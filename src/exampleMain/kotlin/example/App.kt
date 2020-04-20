package example

import extlib.*
import kotlinx.cinterop.*
 
typealias SomeFun = CFunction<() -> CPointer<ByteVar>?>

fun main(args: Array<String>) {

	memScoped {
	   val lib = alloc<libmyext_ExportedSymbols>().apply {
          libmyext_symbols()
       }
       
       val s = lib.kotlin.root.example.someLibraryMethod?.reinterpret<SomeFun>()
	   println(s?.invoke())
	
    }
    
    
	
	//C stub from .def file - works fine
	println(getStringFromLib()!!.toKString());
	
}