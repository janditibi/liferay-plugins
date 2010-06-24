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

package com.liferay.ams.service.base;

import com.liferay.ams.model.Asset;
import com.liferay.ams.service.AssetLocalService;
import com.liferay.ams.service.CheckoutLocalService;
import com.liferay.ams.service.DefinitionLocalService;
import com.liferay.ams.service.TypeLocalService;
import com.liferay.ams.service.persistence.AssetPersistence;
import com.liferay.ams.service.persistence.CheckoutPersistence;
import com.liferay.ams.service.persistence.DefinitionPersistence;
import com.liferay.ams.service.persistence.TypePersistence;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import java.util.List;

import javax.sql.DataSource;

/**
 * <a href="AssetLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class AssetLocalServiceBaseImpl implements AssetLocalService {
	public Asset addAsset(Asset asset) throws SystemException {
		asset.setNew(true);

		return assetPersistence.update(asset, false);
	}

	public Asset createAsset(long assetId) {
		return assetPersistence.create(assetId);
	}

	public void deleteAsset(long assetId)
		throws PortalException, SystemException {
		assetPersistence.remove(assetId);
	}

	public void deleteAsset(Asset asset) throws SystemException {
		assetPersistence.remove(asset);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return assetPersistence.findWithDynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return assetPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return assetPersistence.findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return assetPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public Asset getAsset(long assetId) throws PortalException, SystemException {
		return assetPersistence.findByPrimaryKey(assetId);
	}

	public List<Asset> getAssets(int start, int end) throws SystemException {
		return assetPersistence.findAll(start, end);
	}

	public int getAssetsCount() throws SystemException {
		return assetPersistence.countAll();
	}

	public Asset updateAsset(Asset asset) throws SystemException {
		asset.setNew(false);

		return assetPersistence.update(asset, true);
	}

	public Asset updateAsset(Asset asset, boolean merge)
		throws SystemException {
		asset.setNew(false);

		return assetPersistence.update(asset, merge);
	}

	public AssetLocalService getAssetLocalService() {
		return assetLocalService;
	}

	public void setAssetLocalService(AssetLocalService assetLocalService) {
		this.assetLocalService = assetLocalService;
	}

	public AssetPersistence getAssetPersistence() {
		return assetPersistence;
	}

	public void setAssetPersistence(AssetPersistence assetPersistence) {
		this.assetPersistence = assetPersistence;
	}

	public CheckoutLocalService getCheckoutLocalService() {
		return checkoutLocalService;
	}

	public void setCheckoutLocalService(
		CheckoutLocalService checkoutLocalService) {
		this.checkoutLocalService = checkoutLocalService;
	}

	public CheckoutPersistence getCheckoutPersistence() {
		return checkoutPersistence;
	}

	public void setCheckoutPersistence(CheckoutPersistence checkoutPersistence) {
		this.checkoutPersistence = checkoutPersistence;
	}

	public DefinitionLocalService getDefinitionLocalService() {
		return definitionLocalService;
	}

	public void setDefinitionLocalService(
		DefinitionLocalService definitionLocalService) {
		this.definitionLocalService = definitionLocalService;
	}

	public DefinitionPersistence getDefinitionPersistence() {
		return definitionPersistence;
	}

	public void setDefinitionPersistence(
		DefinitionPersistence definitionPersistence) {
		this.definitionPersistence = definitionPersistence;
	}

	public TypeLocalService getTypeLocalService() {
		return typeLocalService;
	}

	public void setTypeLocalService(TypeLocalService typeLocalService) {
		this.typeLocalService = typeLocalService;
	}

	public TypePersistence getTypePersistence() {
		return typePersistence;
	}

	public void setTypePersistence(TypePersistence typePersistence) {
		this.typePersistence = typePersistence;
	}

	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = assetPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = AssetLocalService.class)
	protected AssetLocalService assetLocalService;
	@BeanReference(type = AssetPersistence.class)
	protected AssetPersistence assetPersistence;
	@BeanReference(type = CheckoutLocalService.class)
	protected CheckoutLocalService checkoutLocalService;
	@BeanReference(type = CheckoutPersistence.class)
	protected CheckoutPersistence checkoutPersistence;
	@BeanReference(type = DefinitionLocalService.class)
	protected DefinitionLocalService definitionLocalService;
	@BeanReference(type = DefinitionPersistence.class)
	protected DefinitionPersistence definitionPersistence;
	@BeanReference(type = TypeLocalService.class)
	protected TypeLocalService typeLocalService;
	@BeanReference(type = TypePersistence.class)
	protected TypePersistence typePersistence;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}