/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package org.fixflow.expand.rulescript.update;

import org.fixflow.core.impl.ProcessEngineConfigurationImpl;
import org.fixflow.core.impl.db.SqlCommand;
import org.fixflow.core.impl.task.TaskInstanceEntity;
import org.fixflow.core.scriptlanguage.UpdateRulesScript;
import org.fixflow.expand.database.TableUtil;

public class UpdateTaskInstance implements UpdateRulesScript {

	public void execute(Object parameter, SqlCommand sqlCommand, ProcessEngineConfigurationImpl processEngineConfiguration) {
		TaskInstanceEntity taskInstance=(TaskInstanceEntity)parameter;
		Object[] objectParamWhere = new Object[1];
		objectParamWhere[0]=taskInstance.getId() ;
		sqlCommand.update(TableUtil.getTaskInstanceRunTableName(),  taskInstance.getPersistentDbMap(), " TASKINSTANCE_ID=?",objectParamWhere);
	
	}

}
