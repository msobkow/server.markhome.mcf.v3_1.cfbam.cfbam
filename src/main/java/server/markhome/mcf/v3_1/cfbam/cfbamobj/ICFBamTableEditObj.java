// Description: Java 25 Instance Edit Object interface for CFBam Table.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
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

public interface ICFBamTableEditObj
	extends ICFBamTableObj, ICFBamScopeEditObj
{
	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFBamTableObj.
	 */
	ICFBamTableObj getOrigAsTable();

	/**
	 *	Get the ICFBamSchemaDefObj instance referenced by the SchemaDef key.
	 *
	 *	@return	The ICFBamSchemaDefObj instance referenced by the SchemaDef key.
	 */
	ICFBamSchemaDefObj getRequiredContainerSchemaDef();

	/**
	 *	Set the ICFBamSchemaDefObj instance referenced by the SchemaDef key.
	 *
	 *	@param	value	the ICFBamSchemaDefObj instance to be referenced by the SchemaDef key.
	 */
	void setRequiredContainerSchemaDef( ICFBamSchemaDefObj value );

	/**
	 *	Get the ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 *
	 *	@return	The ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 */
	ICFBamSchemaDefObj getOptionalLookupDefSchema();

	/**
	 *	Set the ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 *
	 *	@param	value	the ICFBamSchemaDefObj instance to be referenced by the DefSchema key.
	 */
	void setOptionalLookupDefSchema( ICFBamSchemaDefObj value );

	/**
	 *	Get a list ICFBamRelationObj instances referenced by the Relation key.
	 *
	 *	@return	The (potentially empty) list of ICFBamRelationObj instances referenced by the Relation key.
	 */
	List<ICFBamRelationObj> getOptionalComponentsRelation();

	/**
	 *	Get the ICFBamIndexObj instance referenced by the LookupIndex key.
	 *
	 *	@return	The ICFBamIndexObj instance referenced by the LookupIndex key.
	 */
	ICFBamIndexObj getOptionalLookupLookupIndex();

	/**
	 *	Set the ICFBamIndexObj instance referenced by the LookupIndex key.
	 *
	 *	@param	value	the ICFBamIndexObj instance to be referenced by the LookupIndex key.
	 */
	void setOptionalLookupLookupIndex( ICFBamIndexObj value );

	/**
	 *	Get the ICFBamIndexObj instance referenced by the AltIndex key.
	 *
	 *	@return	The ICFBamIndexObj instance referenced by the AltIndex key.
	 */
	ICFBamIndexObj getOptionalLookupAltIndex();

	/**
	 *	Set the ICFBamIndexObj instance referenced by the AltIndex key.
	 *
	 *	@param	value	the ICFBamIndexObj instance to be referenced by the AltIndex key.
	 */
	void setOptionalLookupAltIndex( ICFBamIndexObj value );

	/**
	 *	Get the ICFBamTableObj instance referenced by the QualTable key.
	 *
	 *	@return	The ICFBamTableObj instance referenced by the QualTable key.
	 */
	ICFBamTableObj getOptionalLookupQualTable();

	/**
	 *	Set the ICFBamTableObj instance referenced by the QualTable key.
	 *
	 *	@param	value	the ICFBamTableObj instance to be referenced by the QualTable key.
	 */
	void setOptionalLookupQualTable( ICFBamTableObj value );

	/**
	 *	Get a list ICFBamIndexObj instances referenced by the Index key.
	 *
	 *	@return	The (potentially empty) list of ICFBamIndexObj instances referenced by the Index key.
	 */
	List<ICFBamIndexObj> getOptionalComponentsIndex();

	/**
	 *	Get the ICFBamIndexObj instance referenced by the PrimaryIndex key.
	 *
	 *	@return	The ICFBamIndexObj instance referenced by the PrimaryIndex key.
	 */
	ICFBamIndexObj getOptionalLookupPrimaryIndex();

	/**
	 *	Set the ICFBamIndexObj instance referenced by the PrimaryIndex key.
	 *
	 *	@param	value	the ICFBamIndexObj instance to be referenced by the PrimaryIndex key.
	 */
	void setOptionalLookupPrimaryIndex( ICFBamIndexObj value );

	/**
	 *	Get a list ICFBamValueObj instances referenced by the Columns key.
	 *
	 *	@return	The (potentially empty) list of ICFBamValueObj instances referenced by the Columns key.
	 */
	List<ICFBamValueObj> getOptionalComponentsColumns();

	/**
	 *	Get a list ICFBamRelationObj instances referenced by the ReverseRelations key.
	 *
	 *	@return	The (potentially empty) list of ICFBamRelationObj instances referenced by the ReverseRelations key.
	 */
	List<ICFBamRelationObj> getOptionalChildrenReverseRelations();

	/**
	 *	Get a list ICFBamChainObj instances referenced by the Chains key.
	 *
	 *	@return	The (potentially empty) list of ICFBamChainObj instances referenced by the Chains key.
	 */
	List<ICFBamChainObj> getOptionalComponentsChains();

	/**
	 *	Get a list ICFBamDelTopDepObj instances referenced by the DelDep key.
	 *
	 *	@return	The (potentially empty) list of ICFBamDelTopDepObj instances referenced by the DelDep key.
	 */
	List<ICFBamDelTopDepObj> getOptionalComponentsDelDep();

	/**
	 *	Get a list ICFBamClearTopDepObj instances referenced by the ClearDep key.
	 *
	 *	@return	The (potentially empty) list of ICFBamClearTopDepObj instances referenced by the ClearDep key.
	 */
	List<ICFBamClearTopDepObj> getOptionalComponentsClearDep();

	/**
	 *	Get a list ICFBamServerMethodObj instances referenced by the ServerMethods key.
	 *
	 *	@return	The (potentially empty) list of ICFBamServerMethodObj instances referenced by the ServerMethods key.
	 */
	List<ICFBamServerMethodObj> getOptionalComponentsServerMethods();

	/**
	 *	Get the required CFLibDbKeyHash256 attribute SchemaDefId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute SchemaDefId.
	 */
	CFLibDbKeyHash256 getRequiredSchemaDefId();

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute DefSchemaId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute DefSchemaId.
	 */
	CFLibDbKeyHash256 getOptionalDefSchemaId();

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
	 *	Get the required boolean attribute PageData.
	 *
	 *	@return	The required boolean attribute PageData.
	 */
	boolean getRequiredPageData();

	/**
	 *	Set the required boolean attribute PageData.
	 *
	 *	@param value The required boolean attribute PageData value to be applied.
	 */
	void setRequiredPageData(boolean value);

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute PrimaryIndexId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute PrimaryIndexId.
	 */
	CFLibDbKeyHash256 getOptionalPrimaryIndexId();

	/**
	 *	Get the required String attribute TableClassCode.
	 *
	 *	@return	The required String attribute TableClassCode.
	 */
	String getRequiredTableClassCode();

	/**
	 *	Set the required String attribute TableClassCode.
	 *
	 *	@param value The required String attribute TableClassCode value to be applied.
	 */
	void setRequiredTableClassCode(String value);

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute LookupIndexId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute LookupIndexId.
	 */
	CFLibDbKeyHash256 getOptionalLookupIndexId();

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute AltIndexId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute AltIndexId.
	 */
	CFLibDbKeyHash256 getOptionalAltIndexId();

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute QualifyingTableId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute QualifyingTableId.
	 */
	CFLibDbKeyHash256 getOptionalQualifyingTableId();

	/**
	 *	Get the required boolean attribute IsInstantiable.
	 *
	 *	@return	The required boolean attribute IsInstantiable.
	 */
	boolean getRequiredIsInstantiable();

	/**
	 *	Set the required boolean attribute IsInstantiable.
	 *
	 *	@param value The required boolean attribute IsInstantiable value to be applied.
	 */
	void setRequiredIsInstantiable(boolean value);

	/**
	 *	Get the required boolean attribute HasHistory.
	 *
	 *	@return	The required boolean attribute HasHistory.
	 */
	boolean getRequiredHasHistory();

	/**
	 *	Set the required boolean attribute HasHistory.
	 *
	 *	@param value The required boolean attribute HasHistory value to be applied.
	 */
	void setRequiredHasHistory(boolean value);

	/**
	 *	Get the required boolean attribute HasAuditColumns.
	 *
	 *	@return	The required boolean attribute HasAuditColumns.
	 */
	boolean getRequiredHasAuditColumns();

	/**
	 *	Set the required boolean attribute HasAuditColumns.
	 *
	 *	@param value The required boolean attribute HasAuditColumns value to be applied.
	 */
	void setRequiredHasAuditColumns(boolean value);

	/**
	 *	Get the required boolean attribute IsMutable.
	 *
	 *	@return	The required boolean attribute IsMutable.
	 */
	boolean getRequiredIsMutable();

	/**
	 *	Set the required boolean attribute IsMutable.
	 *
	 *	@param value The required boolean attribute IsMutable value to be applied.
	 */
	void setRequiredIsMutable(boolean value);

	/**
	 *	Get the required boolean attribute IsServerOnly.
	 *
	 *	@return	The required boolean attribute IsServerOnly.
	 */
	boolean getRequiredIsServerOnly();

	/**
	 *	Set the required boolean attribute IsServerOnly.
	 *
	 *	@param value The required boolean attribute IsServerOnly value to be applied.
	 */
	void setRequiredIsServerOnly(boolean value);

	/**
	 *	Get the required ICFBamSchema.LoaderBehaviourEnum attribute LoaderBehaviour.
	 *
	 *	@return	The required ICFBamSchema.LoaderBehaviourEnum attribute LoaderBehaviour.
	 */
	ICFBamSchema.LoaderBehaviourEnum getRequiredLoaderBehaviour();

	/**
	 *	Set the required ICFBamSchema.LoaderBehaviourEnum attribute LoaderBehaviour.
	 *
	 *	@param value The required ICFBamSchema.LoaderBehaviourEnum attribute LoaderBehaviour value to be applied.
	 */
	void setRequiredLoaderBehaviour(ICFBamSchema.LoaderBehaviourEnum value);

	/**
	 *	Get the required ICFBamSchema.SecScopeEnum attribute SecScope.
	 *
	 *	@return	The required ICFBamSchema.SecScopeEnum attribute SecScope.
	 */
	ICFBamSchema.SecScopeEnum getRequiredSecScope();

	/**
	 *	Set the required ICFBamSchema.SecScopeEnum attribute SecScope.
	 *
	 *	@param value The required ICFBamSchema.SecScopeEnum attribute SecScope value to be applied.
	 */
	void setRequiredSecScope(ICFBamSchema.SecScopeEnum value);

}
