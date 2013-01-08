/*****************************************************************************
 * Java Plug-in Framework (JPF)
 * Copyright (C) 2004-2007 Dmitry Olshansky
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *****************************************************************************/
package org.java.plugin.boot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;

import org.java.plugin.ObjectFactory;
import org.java.plugin.Plugin;
import org.java.plugin.PluginManager;
import org.java.plugin.PluginManager.PluginLocation;
import org.java.plugin.registry.IntegrityCheckReport;
import org.java.plugin.registry.ManifestProcessingException;
import org.java.plugin.registry.PluginRegistry;
import org.java.plugin.util.ResourceManager;

public class AdvanceApplicationInitializer extends
		DefaultApplicationInitializer {

	protected static final String PARAM_APPLICATION_DISABLE = "org.java.plugin.boot.pluginDisable"; //$NON-NLS-1$

	protected static final String PARAM_APPLICATION_BLACKLIST_ = "org.java.plugin.boot.pluginDisable"; //$NON-NLS-1$

	public Application initApplication(final BootErrorHandler errorHandler,
			final String[] args) throws Exception {

		// Prepare parameters to start plug-in manager.
		log.debug("collecting plug-in locations"); //$NON-NLS-1$
		Collection<PluginLocation> pluginLocations = collector
				.collectPluginLocations();
		log.debug("collected " + pluginLocations.size() //$NON-NLS-1$
				+ " plug-in locations, instantiating plug-in manager"); //$NON-NLS-1$
		// Create instance of plug-in manager.
		PluginManager pluginManager = ObjectFactory.newInstance(config)
				.createManager();
		pluginLocations = filterPluginLocations(pluginManager.getRegistry(),
				pluginLocations);
		log.debug(pluginLocations.size() + " plug-in locations remain after " //$NON-NLS-1$
				+ "applying filters, publishing plug-ins"); //$NON-NLS-1$
		// Publish discovered plug-in manifests and corresponding plug-in
		// folders.
		pluginManager.publishPlugins(pluginLocations
				.toArray(new PluginLocation[pluginLocations.size()]));
		if (!"off".equalsIgnoreCase(integrityCheckMode)) { //$NON-NLS-1$
			// Check plug-in's integrity.
			log.debug("checking plug-ins set integrity"); //$NON-NLS-1$
			IntegrityCheckReport integrityCheckReport = pluginManager
					.getRegistry().checkIntegrity(
							"light".equalsIgnoreCase(integrityCheckMode) ? null //$NON-NLS-1$
									: pluginManager.getPathResolver());
			log.info("integrity check done: errors - " //$NON-NLS-1$
					+ integrityCheckReport.countErrors() + ", warnings - " //$NON-NLS-1$
					+ integrityCheckReport.countWarnings());
			if (integrityCheckReport.countErrors() != 0) {
				// Something wrong in plug-ins set.
				log.info(integrityCheckReport2str(integrityCheckReport));
				if (!errorHandler.handleError(ResourceManager.getMessage(
						Boot.PACKAGE_NAME, "integrityCheckFailed"), //$NON-NLS-1$
						integrityCheckReport)) {
					return null;
				}
			} else if (log.isDebugEnabled()
					&& ((integrityCheckReport.countErrors() > 0) || (integrityCheckReport
							.countWarnings() > 0))) {
				log.debug(integrityCheckReport2str(integrityCheckReport));
			}
		}
		// application plug-in ID
		String sAppPluginId = config.getProperty(PARAM_APPLICATION_PLUGIN);
		String appPluginIds[] = sAppPluginId.split(";|\\||\\s");

		ApplicationPlugin applicationPlugin = null;
		for (String appPluginId : appPluginIds) {
			if (appPluginId.length() == 0)
				continue;

			log.info("application plug-in is " + appPluginId); //$NON-NLS-1$
			// get the start-up application plug-in
			Plugin appPlugin = pluginManager.getPlugin(appPluginId);
			log.debug("got application plug-in " + appPlugin //$NON-NLS-1$
					+ ", initializing application"); //$NON-NLS-1$
			if (appPlugin instanceof ApplicationPlugin) {
				if (applicationPlugin == null) {
					applicationPlugin = (ApplicationPlugin) appPlugin;
				} else {
					throw new Exception(appPlugin.getClass().getName()
							+ "已经存在一个ApplicationPlugin.");
				}
			}
		}
		if (applicationPlugin == null) {
			throw new Exception("没有找到ApplicationPlugin.");
		}
		return applicationPlugin.initApplication(config, args); //$NON-NLS-1$
	}

	protected Collection<PluginLocation> filterPluginLocations(
			final PluginRegistry registry,
			final Collection<PluginLocation> pluginLocations)
			throws ManifestProcessingException {
		String disables = config.getProperty(PARAM_APPLICATION_DISABLE);
		String blFile = config.getProperty(Boot.BLACKLIST_PATH);
		if (blFile != null) {
			disables += loadBlackList(blFile, "utf-8");
		}

		if (disables != null && disables.length() > 0) {
			String disablePlugins[] = disables.split(";|\\||\\s");
			if (disablePlugins.length > 0) {
				if (blackList == null)
					blackList = new HashSet<String>();
				for (String p : disablePlugins) {
					if (p != null && p.length() > 0) {
						if(p.charAt(0)=='#') continue;
						blackList.add(p);
					}
				}
			}
		}
		
		return super.filterPluginLocations(registry, pluginLocations);
	}

	private String loadBlackList(String blackList, String fileCharacter) {
		StringBuilder answer = new StringBuilder();
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		try {
			inputStreamReader = new InputStreamReader(new FileInputStream(
					blackList), fileCharacter);
			reader = new BufferedReader(inputStreamReader);
			String line = null;
			while ((line = reader.readLine()) != null) {
				answer.append(line).append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return answer.toString();
	}
}
