// Description: Java 25 Instance Edit Object interface for CFBam SchemaDef.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public interface ICFBamSchemaDefEditObj
	extends ICFBamSchemaDefObj, ICFBamScopeEditObj
{
	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFBamSchemaDefObj.
	 */
	ICFBamSchemaDefObj getOrigAsSchemaDef();

	/**
	 *	Get the ICFIntMinorVersionObj instance referenced by the MinorVersion key.
	 *
	 *	@return	The ICFIntMinorVersionObj instance referenced by the MinorVersion key.
	 */
	ICFIntMinorVersionObj getRequiredContainerMinorVersion();

	/**
	 *	Set the ICFIntMinorVersionObj instance referenced by the MinorVersion key.
	 *
	 *	@param	value	the ICFIntMinorVersionObj instance to be referenced by the MinorVersion key.
	 */
	void setRequiredContainerMinorVersion( ICFIntMinorVersionObj value );

	/**
	 *	Get a list ICFBamTableObj instances referenced by the Tables key.
	 *
	 *	@return	The (potentially empty) list of ICFBamTableObj instances referenced by the Tables key.
	 */
	List<ICFBamTableObj> getOptionalComponentsTables();

	/**
	 *	Get a list ICFBamValueObj instances referenced by the Types key.
	 *
	 *	@return	The (potentially empty) list of ICFBamValueObj instances referenced by the Types key.
	 */
	List<ICFBamValueObj> getOptionalComponentsTypes();

	/**
	 *	Get a list ICFBamSchemaRefObj instances referenced by the SchemaRefs key.
	 *
	 *	@return	The (potentially empty) list of ICFBamSchemaRefObj instances referenced by the SchemaRefs key.
	 */
	List<ICFBamSchemaRefObj> getOptionalComponentsSchemaRefs();

	/**
	 *	Get the ICFSecTenantObj instance referenced by the CTenant key.
	 *
	 *	@return	The ICFSecTenantObj instance referenced by the CTenant key.
	 */
	ICFSecTenantObj getRequiredOwnerCTenant();

	/**
	 *	Set the ICFSecTenantObj instance referenced by the CTenant key.
	 *
	 *	@param	value	the ICFSecTenantObj instance to be referenced by the CTenant key.
	 */
	void setRequiredOwnerCTenant( ICFSecTenantObj value );

	/**
	 *	Get the required CFLibDbKeyHash256 attribute CTenantId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute CTenantId.
	 */
	CFLibDbKeyHash256 getRequiredCTenantId();

	/**
	 *	Get the required CFLibDbKeyHash256 attribute MinorVersionId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute MinorVersionId.
	 */
	CFLibDbKeyHash256 getRequiredMinorVersionId();

	/**
	 *	Get the required String attribute Name.
	 *
	 *	@return	The required String attribute Name.
	 */
	String getRequiredName();

	/**
	 *	Set the required String attribute Name.
	 *
	 *	@param value The required String attribute Name value to be applied.
	 */
	void setRequiredName(String value);

	/**
	 *	Get the optional String attribute DbName.
	 *
	 *	@return	The optional String attribute DbName.
	 */
	String getOptionalDbName();

	/**
	 *	Set the optional String attribute DbName.
	 *
	 *	@param value The optional String attribute DbName value to be applied.
	 */
	void setOptionalDbName(String value);

	/**
	 *	Get the optional String attribute ShortName.
	 *
	 *	@return	The optional String attribute ShortName.
	 */
	String getOptionalShortName();

	/**
	 *	Set the optional String attribute ShortName.
	 *
	 *	@param value The optional String attribute ShortName value to be applied.
	 */
	void setOptionalShortName(String value);

	/**
	 *	Get the optional String attribute Label.
	 *
	 *	@return	The optional String attribute Label.
	 */
	String getOptionalLabel();

	/**
	 *	Set the optional String attribute Label.
	 *
	 *	@param value The optional String attribute Label value to be applied.
	 */
	void setOptionalLabel(String value);

	/**
	 *	Get the optional String attribute ShortDescription.
	 *
	 *	@return	The optional String attribute ShortDescription.
	 */
	String getOptionalShortDescription();

	/**
	 *	Set the optional String attribute ShortDescription.
	 *
	 *	@param value The optional String attribute ShortDescription value to be applied.
	 */
	void setOptionalShortDescription(String value);

	/**
	 *	Get the optional String attribute Description.
	 *
	 *	@return	The optional String attribute Description.
	 */
	String getOptionalDescription();

	/**
	 *	Set the optional String attribute Description.
	 *
	 *	@param value The optional String attribute Description value to be applied.
	 */
	void setOptionalDescription(String value);

	/**
	 *	Get the required String attribute CopyrightPeriod.
	 *
	 *	@return	The required String attribute CopyrightPeriod.
	 */
	String getRequiredCopyrightPeriod();

	/**
	 *	Set the required String attribute CopyrightPeriod.
	 *
	 *	@param value The required String attribute CopyrightPeriod value to be applied.
	 */
	void setRequiredCopyrightPeriod(String value);

	/**
	 *	Get the required String attribute CopyrightHolder.
	 *
	 *	@return	The required String attribute CopyrightHolder.
	 */
	String getRequiredCopyrightHolder();

	/**
	 *	Set the required String attribute CopyrightHolder.
	 *
	 *	@param value The required String attribute CopyrightHolder value to be applied.
	 */
	void setRequiredCopyrightHolder(String value);

	/**
	 *	Get the required String attribute AuthorEMail.
	 *
	 *	@return	The required String attribute AuthorEMail.
	 */
	String getRequiredAuthorEMail();

	/**
	 *	Set the required String attribute AuthorEMail.
	 *
	 *	@param value The required String attribute AuthorEMail value to be applied.
	 */
	void setRequiredAuthorEMail(String value);

	/**
	 *	Get the required String attribute ProjectURL.
	 *
	 *	@return	The required String attribute ProjectURL.
	 */
	String getRequiredProjectURL();

	/**
	 *	Set the required String attribute ProjectURL.
	 *
	 *	@param value The required String attribute ProjectURL value to be applied.
	 */
	void setRequiredProjectURL(String value);

	/**
	 *	Get the required String attribute PublishURI.
	 *
	 *	@return	The required String attribute PublishURI.
	 */
	String getRequiredPublishURI();

	/**
	 *	Set the required String attribute PublishURI.
	 *
	 *	@param value The required String attribute PublishURI value to be applied.
	 */
	void setRequiredPublishURI(String value);

}
