/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.youtube.action;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * <a href="ConfigurationActionImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author David Truong
 *
 */
public class ConfigurationActionImpl implements ConfigurationAction {

	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (!cmd.equals(Constants.UPDATE)) {
			return;
		}

		boolean autoplay = ParamUtil.getBoolean(actionRequest, "autoplay");
		boolean enableEnhancedGenieMenu = ParamUtil.getBoolean(actionRequest, "enableEnhancedGenieMenu");
		boolean enableFullscreen = ParamUtil.getBoolean(actionRequest, "enableFullscreen");
		boolean enableKeyboardControls = ParamUtil.getBoolean(actionRequest, "enableKeyboardControls");
		boolean enableRelatedVideos = ParamUtil.getBoolean(actionRequest, "enableRelatedVideos");
		boolean enableSearch = ParamUtil.getBoolean(actionRequest, "enableSearch");
		boolean hd = ParamUtil.getBoolean(actionRequest, "hd");
		boolean loop = ParamUtil.getBoolean(actionRequest, "loop");
		boolean showInfo = ParamUtil.getBoolean(actionRequest, "showInfo");
		boolean showThickerBorder = ParamUtil.getBoolean(actionRequest, "showThickerBorder");

		String annotations = ParamUtil.getString(actionRequest, "annotations");
		String borderColor = ParamUtil.getString(actionRequest, "borderColor");
		String height = ParamUtil.getString(actionRequest, "height");
		String playerColor = ParamUtil.getString(actionRequest, "playerColor");
		String startTime = ParamUtil.getString(actionRequest, "startTime");
		String url = ParamUtil.getString(actionRequest, "url");
		String width = ParamUtil.getString(actionRequest, "width");

		String portletResource = ParamUtil.getString(actionRequest, "portletResource");

		PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);

		preferences.setValue("autoplay", String.valueOf(autoplay));
		preferences.setValue("enableEnhancedGenieMenu", String.valueOf(enableEnhancedGenieMenu));
		preferences.setValue("enableFullscreen", String.valueOf(enableFullscreen));
		preferences.setValue("enableKeyboardControls", String.valueOf(enableKeyboardControls));
		preferences.setValue("enableRelatedVideos", String.valueOf(enableRelatedVideos));
		preferences.setValue("enableSearch", String.valueOf(enableSearch));
		preferences.setValue("hd", String.valueOf(hd));
		preferences.setValue("loop", String.valueOf(loop));
		preferences.setValue("showInfo", String.valueOf(showInfo));
		preferences.setValue("showThickerBorder", String.valueOf(showThickerBorder));

		preferences.setValue("annotations", annotations);
		preferences.setValue("borderColor", borderColor);
		preferences.setValue("height", height);
		preferences.setValue("playerColor", playerColor);
		preferences.setValue("startTime", startTime);
		preferences.setValue("url", url);
		preferences.setValue("width", width);

		preferences.store();

		SessionMessages.add(actionRequest, portletConfig.getPortletName() + ".doConfigure");
	}

	public String render(
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		return "/configuration.jsp";
	}

}