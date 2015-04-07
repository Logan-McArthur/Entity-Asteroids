package com.PromethiaRP.Draeke.Asteroids;

import java.io.File;

public class ResourceLoader {

	private File resourceDirectory;
	
	public ResourceLoader(File resourceDirectory) {
		this.resourceDirectory = resourceDirectory;
		
	}
	
	public void loadResources() {
		File[] files = resourceDirectory.listFiles();
		
	}
}
