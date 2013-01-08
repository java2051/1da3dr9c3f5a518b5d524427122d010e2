package com.honey.core.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationSession{
	private static Map<String, Object>  session = new HashMap<String, Object>();

	private static List<AddListener> addListeners = new ArrayList<AddListener>();
	
	private static List<RemoveListener> removeListeners = new ArrayList<RemoveListener>();
	
	public void addSession(String key, Object obj){
		for(AddListener  addListener : addListeners){
			addListener.doListener(key, obj);
		}
		session.put(key, obj);	
	}

	public void removeSession(String key){
		Object obj = session.remove(key);
		for(RemoveListener  removeListener : removeListeners){
			removeListener.doListener(key, obj);
		}
	}

	/**
	 * session监听器
	 * @param listener
	 */
	public static void addListener(Listener listener){
		if(listener instanceof AddListener ){
			addListeners.add((AddListener)listener);
		}else if(listener instanceof RemoveListener ){
			removeListeners.add((RemoveListener)listener);
		}
	}

	
	
}
