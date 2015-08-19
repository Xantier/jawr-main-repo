/**
 * Copyright 2014 Ibrahim Chaehoi
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
package net.jawr.web.resource.bundle.generator.classpath.webjars;

import net.jawr.web.resource.bundle.generator.resolver.ResourceGeneratorResolver;
import net.jawr.web.resource.bundle.generator.resolver.WebJarsLocatorPathResolver;

/**
 * This class defines the generator for webjars CSS resources which use the
 * WebJarsLocator. So to reference a ressource you only need the short path
 * reference instead of the full path one. <br/>
 * For example : webjars:/css/bootstrap.css instead of
 * webjars:/bootstrap/3.2.0/css/bootstrap.css
 * To avoid resource reference collision if there multiple resource with the same name in different webjars,
 * you can specify the webjars in parameter between bracket.
 * like below :</br>
 * webjars:/css/bootstrap.css[bootstrap]
 *
 * @author (Original) Ted Liang (https://github.com/tedliang)
 * @author Ibrahim Chaehoi
 */
public class WebJarsLocatorCssGenerator extends WebJarsCssGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.jawr.web.resource.bundle.generator.classpath.ClassPathCSSGenerator
	 * #createResolver(java.lang.String)
	 */
	@Override
	protected ResourceGeneratorResolver createResolver(String generatorPrefix) {
		return new WebJarsLocatorPathResolver(generatorPrefix, true, true);
	}
}
