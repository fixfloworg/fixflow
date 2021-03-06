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
package org.fixflow.core.impl.flowgraphics.svg.component;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.fixflow.core.exception.FixFlowException;
import org.fixflow.core.impl.flowgraphics.svg.FlowSvgUtil;
import org.fixflow.core.impl.flowgraphics.svg.SvgBench;
import org.fixflow.core.impl.flowgraphics.svg.to.SvgBaseTo;
import org.fixflow.core.impl.flowgraphics.svg.to.SvgLaneTo;
import org.fixflow.core.impl.util.StringUtil;
import org.fixflow.core.impl.util.XmlUtil;

public class SvgLaneComponent implements ISvgComponent {

	private static String comPath = "/svgcomponent/lane.xml";
	

	private static String text_x="{text_x}";
	
	private static String text_y="{text_y}";
	
	public String createComponent(SvgBaseTo svgTo) {
		String result = null;
		try {
			SvgLaneTo lane = (SvgLaneTo)svgTo;
			
			if(lane.isHorizontal()){
				comPath = "/svgcomponent/lane.xml";
			}
			else {
				comPath = "/svgcomponent/lane_h.xml";
			}
			InputStream in = SvgBench.class.getResourceAsStream(comPath);
			Document doc = XmlUtil.read(in);
			String str = doc.getRootElement().asXML();
			str = FlowSvgUtil.replaceAll(str, local_x, StringUtil.getString(lane.getX()));
			str = FlowSvgUtil.replaceAll(str, local_y, StringUtil.getString(lane.getY()));
			str = FlowSvgUtil.replaceAll(str, id, lane.getId());
			str = FlowSvgUtil.replaceAll(str, text, lane.getLabel());
			str = FlowSvgUtil.replaceAll(str, width, StringUtil.getString(lane.getWidth()));
			str = FlowSvgUtil.replaceAll(str, hight, StringUtil.getString(lane.getHeight()));
			if(lane.isHorizontal()){
				str = FlowSvgUtil.replaceAll(str, text_y, StringUtil.getString(lane.getHeight()/2));
			}
			else {
				str = FlowSvgUtil.replaceAll(str, text_x, StringUtil.getString(lane.getWidth()/2));
			}
			
			result = str;
		} catch (DocumentException e) {
			throw new FixFlowException("",e);
		}
		return result;
	}

}
