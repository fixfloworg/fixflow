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
package org.fixflow.core.impl.cmd;

import org.fixflow.core.impl.interceptor.Command;
import org.fixflow.core.impl.interceptor.CommandContext;
import org.fixflow.core.impl.job.JobEntity;
import org.fixflow.core.impl.persistence.JobManager;
import org.fixflow.core.job.Job;

public class SaveJobCmd implements Command<Void>{

	protected Job job;
	protected boolean isNowPerform=false;
	
	public SaveJobCmd(Job job,boolean isNowPerform){
		this.job=job;
		this.isNowPerform=isNowPerform;
	}
	
	
	public Void execute(CommandContext commandContext) {


		JobManager jobManager=commandContext.getJobManager();
		jobManager.saveJob((JobEntity)this.job);
		
		return null;
	}

}
