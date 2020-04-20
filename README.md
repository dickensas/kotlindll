# kotlindll
Kotlin DLL Example

This directory contains an example code, which creates DLL from one kotlin code and directly access that DLL from another kotlin code without using klib

## Executing

`gradlew clean linkDebugSharedLibmyext runDebugExecutableExample`

## Python module

Use MSYS2 in windows with below sequence of commands, this is should done after running above gradle which will copy the DLLs and heade to current directory 

`python3 setup.py build` <br/>
`python3 setup.py install` <br/>
`python3 test.py` <br/>