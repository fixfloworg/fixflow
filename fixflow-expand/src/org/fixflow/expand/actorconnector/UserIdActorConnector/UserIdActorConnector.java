package org.fixflow.expand.actorconnector.UserIdActorConnector;


import java.util.*;

import org.fixflow.core.connector.ActorConnectorHandler;
import org.fixflow.core.impl.identity.Authentication;
import org.fixflow.core.impl.identity.GroupTo;
import org.fixflow.core.impl.identity.UserTo;
import org.fixflow.core.runtime.ExecutionContext;

public class UserIdActorConnector implements ActorConnectorHandler {

	/**
	* 获取用户类型处理者
	* @param executionContext 流程上下文
	* @return
	*/
	public List<UserTo> UserExecute(ExecutionContext executionContext){
		List<UserTo> userTos = new ArrayList<UserTo>();
		String userId=Authentication.getAuthenticatedUserId();
		UserTo userTo = new UserTo(userId);
		userTos.add(userTo);
		return userTos;
	}

	/**
	* 获取组类型处理者
	* @param executionContext 流程上下文
	* @return
	*/
	public List<GroupTo> GroupExecute(ExecutionContext executionContext){
		List<GroupTo> groupTos = new ArrayList<GroupTo>();
		//加入Group 	GroupTo groupTo = new GroupTo("组编号", "组类型");
		return groupTos;
	}

}