package com.PromethiaRP.Draeke.Asteroids;

import java.util.HashMap;
import java.util.Map;

import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaString;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

// Scripts not necessarily being unique to an entity

public class Script {
	
	public static final String INITIALIZATION_FUNCTION = "getDeclaredFunctions";
	
	private String fileName;
	private Map<String, LuaFunction> functionMap;
	private LuaValue scriptHandle;
	
//	public Script(String fileName) {
//		this.fileName = fileName;	// Always have this first
//		functionMap = new HashMap<String, LuaFunction>();
//		loadFunctionMap(fileName, functionMap, functionNames);
//		
//	}
	
	public Script(String fileName) {
		this.fileName = fileName;
		functionMap = new HashMap<String, LuaFunction>();
		scriptHandle = JsePlatform.standardGlobals();
		scriptHandle.get("dofile").call( LuaValue.valueOf(fileName) );
		
		addFunctionHandle(Script.INITIALIZATION_FUNCTION);
		Varargs rtvals = functionMap.get(Script.INITIALIZATION_FUNCTION).invoke();
		
		int i = 1;
		while (rtvals.isstring(i)) {
			addFunctionHandle(rtvals.tojstring(i));
			i++;
		}
	}
	
	// Invoke on Entities
	public void invokeFunction(Entity source, String functionName) {
		if (functionMap.containsKey(functionName)) {
			functionMap.get(functionName).invoke(CoerceJavaToLua.coerce(source));
		} else {
			throw new IllegalArgumentException("There is no function named " + functionName + " within script " + fileName);
		}
	}
	
	
//	private void loadFunctionMap(String fileName, Map<String, LuaFunction> functionMap, String[] functionNames) {
//		LuaValue _G = JsePlatform.standardGlobals();
//		_G.get("dofile").call( LuaValue.valueOf(fileName));
//
//		scriptHandle = _G;
//		
//		for (String function : functionNames) {
//			addFunctionHandle(function);
//		}
//		
//	}
	
	
	private LuaFunction retrieveFunctionHandle(LuaValue _G, String functionName) {
		LuaValue uncertain = _G.get(functionName);
		if (uncertain instanceof LuaFunction) {
			return (LuaFunction) uncertain;
		}
		throw new RuntimeException("Could not find function " + functionName + " in file " + fileName);
	}
	
	public void addFunctionHandle(String functionName) {
		functionMap.put(functionName, retrieveFunctionHandle(scriptHandle, functionName));
	}
}

/*
 * 
function receive_aif( aifObj )
    --This is how you can invoke public function associated with aifObj     
    aifObj:someAifFunction()
end
 */
