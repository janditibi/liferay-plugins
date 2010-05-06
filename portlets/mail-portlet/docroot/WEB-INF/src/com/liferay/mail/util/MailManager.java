/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.mail.util;

import com.liferay.mail.MailException;
import com.liferay.mail.mailbox.Mailbox;
import com.liferay.mail.mailbox.MailboxFactoryUtil;
import com.liferay.mail.model.Account;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * <a href="MailManager.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 */
public class MailManager {

	public static MailManager getInstance(HttpServletRequest request)
		throws PortalException, SystemException {

		User user = PortalUtil.getUser(request);

		return new MailManager(user);
	}

	public MailManager(User user) {
		_user = user;
	}

	public JSONObject addAccount(
			String address, String personalName, String protocol,
			String incomingHostName, int incomingPort, boolean incomingSecure,
			String outgoingHostName, int outgoingPort, boolean outgoingSecure,
			String login, String unencryptedPassword, boolean savePassword,
			String signature, boolean useSignature, String folderPrefix,
			boolean defaultSender)
		throws PortalException, SystemException {

		try {
			Mailbox mailbox = MailboxFactoryUtil.getMailbox(
				_user.getUserId(), protocol);

			mailbox.addAccount(
				address, personalName, protocol, incomingHostName, incomingPort,
				incomingSecure, outgoingHostName, outgoingPort, outgoingSecure,
				login, unencryptedPassword, savePassword, signature,
				useSignature, folderPrefix, defaultSender);

			return createJSONResult("success", "account-has-been-created");
		}
		catch (MailException me) {
			_log.error(me, me);

			return createJSONResult("failure", "unable-to-add-account");
		}
	}

	public List<Account> getAccounts() throws SystemException {
		return AccountLocalServiceUtil.getAccounts(_user.getUserId());
	}

	public void synchronizeAccount(long accountId)
		throws PortalException, SystemException {

		Mailbox mailbox = MailboxFactoryUtil.getMailbox(
			_user.getUserId(), accountId);

		mailbox.synchronize();
	}

	protected JSONObject createJSONResult(String status, String message) {
		return createJSONResult(status, message, null);
	}

	protected JSONObject createJSONResult(
		String status, String message, String value) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("status", status);
		jsonObject.put("message", LanguageUtil.get(_user.getLocale(), message));

		if (Validator.isNotNull(value)) {
			jsonObject.put("value", value);
		}

		return jsonObject;
	}

	private static Log _log = LogFactoryUtil.getLog(MailManager.class);

	private User _user;

}