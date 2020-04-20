#include <Python.h>
#include "libmyext_api.h"

static PyObject* helloworld(PyObject* self, PyObject* args) {
	libmyext_ExportedSymbols* lib = libmyext_symbols();
	printf("%s",lib->kotlin.root.example.someLibraryMethod());
	return Py_None;
}

static PyMethodDef myMethods[] = { { "helloworld", helloworld, METH_NOARGS,
		"Prints Hello World" }, { NULL, NULL, 0, NULL } };

static struct PyModuleDef myModule = { PyModuleDef_HEAD_INIT, "myModule",
		"Test Module", -1, myMethods };

PyMODINIT_FUNC PyInit_myModule(void) {
	return PyModule_Create(&myModule);
}
