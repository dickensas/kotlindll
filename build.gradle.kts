plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.3.72"
}

repositories {
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-dev" )
}

val mingw64Path = File(System.getenv("MINGW64_DIR") ?: "C:/msys64/mingw64")

kotlin {
	mingwX64("libmyext") {
       binaries {
		 sharedLib {
			baseName= "libmyext"
		  }
	   }
    }
	
	mingwX64("example") {
		binaries {
		  executable {
             entryPoint = "example.main"
             
             val taskName = linkTaskName.replaceFirst("link", "dlltool")
             val defFile = File("${project.rootDir}/build/bin/libmyext/debugShared/libmyext.def")
         	 val aFile = File("${project.rootDir}/build/bin/libmyext/debugShared/libmyext.dll.a")
         	 val dllFile = File("${project.rootDir}/build/bin/libmyext/debugShared/libmyext.dll")
             	
             val dlltoolTask = tasks.register<Exec>(taskName) {
             	commandLine("$mingw64Path/bin/dlltool", "-d", defFile, "-l", aFile, "-D", dllFile)
             }
             linkTask.dependsOn(dlltoolTask, "copyDlls")
             
			 linkerOpts("-L${project.rootDir}/build/bin/libmyext/debugShared")
          }
	    }
		compilations["main"].cinterops {
			val extlib by creating {
				includeDirs {
                    allHeaders("${project.rootDir}/build/bin/libmyext/debugShared")
                }
		    }
		}
	}
}

tasks {
    register("copyDlls", Copy::class) {
        from("${project.rootDir}/build/bin/libmyext/debugShared") {
            include("libmyext.dll")
            include("libmyext.dll.a")
        }
        into("${project.rootDir}")
    }
}
