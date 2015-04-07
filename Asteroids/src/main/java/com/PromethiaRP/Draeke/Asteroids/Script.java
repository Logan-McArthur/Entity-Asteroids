package com.PromethiaRP.Draeke.Asteroids;

import java.util.HashMap;
import java.util.Map;

import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

// Scripts not necessarily being unique to an entity

// Am I just replacing Components with Scripts?
// I was thinking about Scripts when I was looking at bullet lifespan, how it seemed weird to
// create a component just to say that the bullet dies when a Countdown ends
// So I want scripts to augment the entity? To be how the entity behaves beyond the scope of a single component

// So then scripts are what happens between the components, the components have a behavior and the scripts turn those behaviors on and off

// Maybe the components shouldn't be branching? The scripts are? What do I mean by that?
// Maybe the components just store data and have methods that are called from scripts?

// Thinking through an example, the input system. KeyboardInput shouldn't be tied to moving the ship
// So KeyboardInput is tied to a script instead? And the specific script chooses where to direct the input?
// How is that any different than what I have now?

// I think I'm getting close, with physics the position is updated every tick and I don't want a script to do that
// So Scripts are the things that orchestrate the components behaviors?

// Like a conductor then, each musician knows how to play the song, but the conductor is there to lead them
// ie. Bullets die when their timer runs out, Asteroids spawn smaller Asteroids when they die
public class Script {
	
	private String fileName;
	private Map<String, LuaFunction> functionMap;
	
	public Script(String fileName, String[] functionNames) {
		functionMap = Script.loadFunctionMap(fileName, functionNames);
		this.fileName = fileName;
	}
	
	// Invoke on Entities
	public void invokeFunction(Entity source, String functionName) {
		if (functionMap.containsKey(functionName)) {
			functionMap.get(functionName).invoke();
		} else {
			throw new IllegalArgumentException("There is no function named" + functionName + " within script " + fileName);
		}
	}
	
	private static Map<String, LuaFunction> loadFunctionMap(String fileName, String[] functionNames) {
		Map<String, LuaFunction> functionMap = new HashMap<String, LuaFunction>();
		LuaValue _G = JsePlatform.standardGlobals();
		
		_G.get("dofile").call( LuaValue.valueOf(fileName));
		
		for (String function : functionNames) {
			functionMap.put(function, retrieveFunctionHandle(_G, function));
		}
		return functionMap;
	}
	
	private static LuaFunction retrieveFunctionHandle(LuaValue _G, String functionName) {
		LuaValue uncertain = _G.get(functionName);
		
		if (uncertain instanceof LuaFunction) {
			return (LuaFunction) uncertain;
		}
		throw new RuntimeException();
	}
}

/*
 * 
function receive_aif( aifObj )
    --This is how you can invoke public function associated with aifObj     
    aifObj:someAifFunction()
end
 */
