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
package net.jawr.web.resource.bundle.iterator;

/**
 * This class defines the resource bundle path which will be rendered by the BundleRenderer
 * 
 * @author ibrahim Chaehoi
 *
 */
public class BundlePath {

	/** The bundle path */
	private String path;
	
	/** The flag indicating if it's a production URL or not */
	private boolean productionURL;

	/**
	 * Constructor
	 * @param path the bundle path, which is not a production URL 
	 */
	public BundlePath(String path) {
		this( path, false);
	}
	
	/**
	 * Constructor
	 * @param path the bundle path 
	 * @param isProductionURL flag indicating if it's a production URL or not
	 */
	public BundlePath(String path, boolean isProductionURL) {
		super();
		this.path = path;
		this.productionURL = isProductionURL;
	}

	/**
	 * Returns the bundle path
	 * @return the bundle path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the bundle path
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Returns true if the path is the path for a production URL
	 * @return true if the path is the path for a production URL
	 */
	public boolean isProductionURL() {
		return productionURL;
	}

	public void setProductionURL(boolean productionURL) {
		this.productionURL = productionURL;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + (productionURL ? 1231 : 1237);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BundlePath other = (BundlePath) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (productionURL != other.productionURL)
			return false;
		return true;
	}
	
	
}
