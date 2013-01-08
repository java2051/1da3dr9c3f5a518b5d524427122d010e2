package com.honey.core;

import java.util.List;
import java.util.Map;

import org.java.plugin.PluginLifecycleException;

import com.honey.core.notify.Notify;
import com.honey.core.notify.Signal;
import com.honey.core.utils.EmptyUtility;


class NotifyToPlugin {
	Map<Class<Signal>, List<Notify>> notifies = null;
	
	public NotifyToPlugin(Map<Class<Signal>, List<Notify>>  notifies  ){
		this.notifies = notifies; 
	}
	
	/**
	 * 执行通知
	 * @param signal 通知信号
	 * @return 
	 * @throws PluginLifecycleException
	 */
	public void runNotify(Signal signal ) throws PluginLifecycleException{
		List<Notify> list = notifies.get(signal.getClass());
		if( ! EmptyUtility.isListEmpty(list) ){
			for(Notify notify : list  ){
				notify.execute(signal);
			}
		}
		
//		Object answer = null;
//		if( notifies != null ){
//			String to = signal.getToPlugin();
//			if(to == null ){
//				Collection<Notify> list = notifies.values();
//				for(Notify notify : list ){
//					answer = notify.execute(signal);
//				}
//			}else{
//				Notify notify = notifies.get(to);
//				if(notify != null){
//					answer = notify.execute(signal);
//				}
//			}
//		}
//		return answer;
	}
}
