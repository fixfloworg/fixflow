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
package org.fixflow.expand.cmd;


import org.fixflow.core.exception.FixFlowException;
import org.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import org.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import org.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import org.fixflow.core.impl.interceptor.CommandContext;
import org.fixflow.core.impl.task.TaskInstanceEntity;
import org.fixflow.expand.command.RollBackTaskCommand;

public class RollBackTaskCmd extends AbstractExpandTaskCmd<RollBackTaskCommand, Void> {

	/**
	 * 退回到的节点
	 */
	protected String rollBackNodeId;

	public RollBackTaskCmd(RollBackTaskCommand rollBackTaskCommand) {
		super(rollBackTaskCommand);
		this.rollBackNodeId = rollBackTaskCommand.getRollBackNodeId();
	}

	public Void execute(CommandContext commandContext) {

		// 初始化任务命令执行所需要的常用对象
		loadProcessParameter(commandContext);

		// 将外部变量注册到流程实例运行环境中
		addVariable();

		// 执行处理命令中的开发人员设置的表达式
		runCommandExpression();

		// 获取正在操作的任务实例对象
		TaskInstanceEntity taskInstance = getTaskInstanceEntity();

		// 获取正在操作的任务命令对象实例
		TaskCommandInst taskCommand = getTaskCommandInst();

		try {

			UserTaskBehavior backNodeUserTask = (UserTaskBehavior) getExecutionContext().getProcessDefinition().getDefinitions().getElement(rollBackNodeId);
			taskInstance.toFlowNodeEnd(taskCommand, taskComment, backNodeUserTask, null);

		} catch (Exception e) {

			throw new FixFlowException("任务: " + taskId + " 退回失败!", e);
		}

		try {
			saveProcessInstance(commandContext);
		} catch (Exception e) {
			throw new FixFlowException("流程实例持久化失败!", e);
		}
		return null;

	}

}
