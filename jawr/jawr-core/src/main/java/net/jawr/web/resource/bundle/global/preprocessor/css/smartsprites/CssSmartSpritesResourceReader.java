/**
 * Copyright 2009-2016 Ibrahim Chaehoi
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 * 
 * 	http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package net.jawr.web.resource.bundle.global.preprocessor.css.smartsprites;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;

import net.jawr.web.JawrConstant;
import net.jawr.web.config.JawrConfig;
import net.jawr.web.resource.bundle.generator.GeneratorRegistry;
import net.jawr.web.resource.handler.reader.FileSystemResourceReader;
import net.jawr.web.resource.handler.reader.PathPrefixedServletContextResourceReader;
import net.jawr.web.resource.handler.reader.ResourceReader;
import net.jawr.web.resource.handler.reader.StreamResourceReader;
import net.jawr.web.resource.handler.reader.TextResourceReader;

/**
 * This class defines the resource reader for the resources generated by the smartsprites
 * global preprocessor : the generated sprites and the modified CSS files.
 *   
 * @author Ibrahim Chaehoi
 *
 */
public class CssSmartSpritesResourceReader implements TextResourceReader, StreamResourceReader {

	/** The smartsprites resource reader */
	private ResourceReader resourceReader;
	
	/** The jawr config */
	private JawrConfig jawrConfig;
	
	/** the smartsprites temporary directory */
	private String tempDir;
	
	/**
	 * Constructor
	 * @param tempDir the smartsprites temporary directory
	 * @param jawrConfig the jawr config
	 */
	public CssSmartSpritesResourceReader(String tempDir, JawrConfig jawrConfig) {
		
		this.tempDir = tempDir;
		this.jawrConfig = jawrConfig;
		if(jawrConfig.isWorkingDirectoryInWebApp()){
			resourceReader = new PathPrefixedServletContextResourceReader(jawrConfig.getContext(), jawrConfig, tempDir+JawrConstant.CSS_SMARTSPRITES_TMP_DIR);

		}else{
			resourceReader = new FileSystemResourceReader(tempDir+JawrConstant.CSS_SMARTSPRITES_TMP_DIR, jawrConfig);
		}
	}
	
	/* (non-Javadoc)
	 * @see net.jawr.web.resource.handler.ResourceReader#getResource(java.lang.String)
	 */
	public Reader getResource(String resourceName) {
		
		Reader rd = null;
		if(!jawrConfig.isDebugModeOn()){
			((TextResourceReader) resourceReader).getResource(resourceName);
		}
		
		return rd;
	}

	/* (non-Javadoc)
	 * @see net.jawr.web.resource.handler.ResourceReader#getResource(java.lang.String, boolean)
	 */
	public Reader getResource(String resourceName, boolean processingBundle) {
		
		Reader rd = null;
		if(processingBundle){
			String path = getCssPath(resourceName);
			rd = ((TextResourceReader) resourceReader).getResource(path, processingBundle);
		}
		
		return rd;
	}

	/**
	 * Returns the Css path from the resource name
	 * @param resourceName the resource name
	 * @return the Css path
	 */
	protected String getCssPath(String resourceName) {
		String path = resourceName;
		if(jawrConfig.getGeneratorRegistry().isPathGenerated(path)){
			path = path.replace(':', '/');
			path = JawrConstant.SPRITE_GENERATED_CSS_DIR+path;
		}
		return path;
	}
	
	/* (non-Javadoc)
	 * @see net.jawr.web.resource.handler.stream.StreamResourceReader#getResourceAsStream(java.lang.String)
	 */
	public InputStream getResourceAsStream(String resourceName) {
		
		return getResourceAsStream(resourceName, false);
	}

	/* (non-Javadoc)
	 * @see net.jawr.web.resource.handler.stream.StreamResourceReader#getResourceAsStream(java.lang.String, boolean)
	 */
	public InputStream getResourceAsStream(String resourceName,
			boolean processingBundle) {
		
		String path = resourceName;
		GeneratorRegistry generatorRegistry = jawrConfig.getGeneratorRegistry();
		if(generatorRegistry.isGeneratedBinaryResource(path)){
			path = path.replace(':', '/');
			path = JawrConstant.SPRITE_GENERATED_IMG_DIR+path;
		}
				
		return ((StreamResourceReader) resourceReader).getResourceAsStream(path, processingBundle);
	}

	/**
	 * Returns the file of the generated CSS
	 * @param path the path of the CSS
	 * @return the file of the generated CSS
	 */
	public File getGeneratedCssFile(String path) {
		
		String rootDir = tempDir+JawrConstant.CSS_SMARTSPRITES_TMP_DIR;
		String fPath = null;
		if(jawrConfig.isWorkingDirectoryInWebApp()){
			fPath = jawrConfig.getContext().getRealPath(rootDir+getCssPath(path));

		}else{
			fPath = rootDir+getCssPath(path);
		}
		
		return new File(fPath);
	}

	/**
	 * Returns the file of the generated CSS
	 * @param path the path of the CSS
	 * @return the file of the generated CSS
	 */
	public File getBackupFile(String path) {
		
		String rootDir = tempDir+JawrConstant.SPRITE_BACKUP_GENERATED_CSS_DIR;
		String fPath = null;
		if(jawrConfig.isWorkingDirectoryInWebApp()){
			fPath = jawrConfig.getContext().getRealPath(rootDir+getCssPath(path));
		}else{
			fPath = rootDir+getCssPath(path);
		}
		
		return new File(fPath);
	}

}
