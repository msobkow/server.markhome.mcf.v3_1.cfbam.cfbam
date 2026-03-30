// Description: Java 25 Instance Edit Object interface for CFBam Relation.

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

public interface ICFBamRelationEditObj
	extends ICFBamRelationObj, ICFBamScopeEditObj
{
	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFBamRelationObj.
	 */
	ICFBamRelationObj getOrigAsRelation();

	/**
	 *	Get the ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 *
	 *	@return	The ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 */
	ICFBamSchemaDefObj getOptionalLookupDefSchema();

	/**
	 *	Get the optional ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 *
	 *	@return	The optional ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 */
	ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead );

	/**
	 *	Set the ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 *
	 *	@param	value	the ICFBamSchemaDefObj instance to be referenced by the DefSchema key.
	 */
	void setOptionalLookupDefSchema( ICFBamSchemaDefObj value );

	/**
	 *	Get the ICFBamTableObj instance referenced by the FromTable key.
	 *
	 *	@return	The ICFBamTableObj instance referenced by the FromTable key.
	 */
	ICFBamTableObj getRequiredContainerFromTable();

	/**
	 *	Get the required ICFBamTableObj instance referenced by the FromTable key.
	 *
	 *	@return	The required ICFBamTableObj instance referenced by the FromTable key.
	 */
	ICFBamTableObj getRequiredContainerFromTable( boolean forceRead );

	/**
	 *	Set the ICFBamTableObj instance referenced by the FromTable key.
	 *
	 *	@param	value	the ICFBamTableObj instance to be referenced by the FromTable key.
	 */
	void setRequiredContainerFromTable( ICFBamTableObj value );

	/**
	 *	Get a list ICFBamRelationColObj instances referenced by the Columns key.
	 *
	 *	@return	The (potentially empty) list of ICFBamRelationColObj instances referenced by the Columns key.
	 */
	List<ICFBamRelationColObj> getOptionalComponentsColumns();

	/**
	 *	Get a list ICFBamPopTopDepObj instances referenced by the PopDep key.
	 *
	 *	@return	The (potentially empty) list of ICFBamPopTopDepObj instances referenced by the PopDep key.
	 */
	List<ICFBamPopTopDepObj> getOptionalComponentsPopDep();

	/**
	 *	Get the ICFBamIndexObj instance referenced by the FromIndex key.
	 *
	 *	@return	The ICFBamIndexObj instance referenced by the FromIndex key.
	 */
	ICFBamIndexObj getRequiredLookupFromIndex();

	/**
	 *	Get the required ICFBamIndexObj instance referenced by the FromIndex key.
	 *
	 *	@return	The required ICFBamIndexObj instance referenced by the FromIndex key.
	 */
	ICFBamIndexObj getRequiredLookupFromIndex( boolean forceRead );

	/**
	 *	Set the ICFBamIndexObj instance referenced by the FromIndex key.
	 *
	 *	@param	value	the ICFBamIndexObj instance to be referenced by the FromIndex key.
	 */
	void setRequiredLookupFromIndex( ICFBamIndexObj value );

	/**
	 *	Get the ICFBamTableObj instance referenced by the ToTable key.
	 *
	 *	@return	The ICFBamTableObj instance referenced by the ToTable key.
	 */
	ICFBamTableObj getRequiredLookupToTable();

	/**
	 *	Get the required ICFBamTableObj instance referenced by the ToTable key.
	 *
	 *	@return	The required ICFBamTableObj instance referenced by the ToTable key.
	 */
	ICFBamTableObj getRequiredLookupToTable( boolean forceRead );

	/**
	 *	Set the ICFBamTableObj instance referenced by the ToTable key.
	 *
	 *	@param	value	the ICFBamTableObj instance to be referenced by the ToTable key.
	 */
	void setRequiredLookupToTable( ICFBamTableObj value );

	/**
	 *	Get the ICFBamIndexObj instance referenced by the ToIndex key.
	 *
	 *	@return	The ICFBamIndexObj instance referenced by the ToIndex key.
	 */
	ICFBamIndexObj getRequiredLookupToIndex();

	/**
	 *	Get the required ICFBamIndexObj instance referenced by the ToIndex key.
	 *
	 *	@return	The required ICFBamIndexObj instance referenced by the ToIndex key.
	 */
	ICFBamIndexObj getRequiredLookupToIndex( boolean forceRead );

	/**
	 *	Set the ICFBamIndexObj instance referenced by the ToIndex key.
	 *
	 *	@param	value	the ICFBamIndexObj instance to be referenced by the ToIndex key.
	 */
	void setRequiredLookupToIndex( ICFBamIndexObj value );

	/**
	 *	Get the ICFBamRelationObj instance referenced by the Narrowed key.
	 *
	 *	@return	The ICFBamRelationObj instance referenced by the Narrowed key.
	 */
	ICFBamRelationObj getOptionalLookupNarrowed();

	/**
	 *	Get the optional ICFBamRelationObj instance referenced by the Narrowed key.
	 *
	 *	@return	The optional ICFBamRelationObj instance referenced by the Narrowed key.
	 */
	ICFBamRelationObj getOptionalLookupNarrowed( boolean forceRead );

	/**
	 *	Set the ICFBamRelationObj instance referenced by the Narrowed key.
	 *
	 *	@param	value	the ICFBamRelationObj instance to be referenced by the Narrowed key.
	 */
	void setOptionalLookupNarrowed( ICFBamRelationObj value );

	/**
	 *	Get the required CFLibDbKeyHash256 attribute TableId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute TableId.
	 */
	CFLibDbKeyHash256 getRequiredTableId();

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
	 *	Get the required ICFBamSchema.RelationTypeEnum attribute RelationType.
	 *
	 *	@return	The required ICFBamSchema.RelationTypeEnum attribute RelationType.
	 */
	ICFBamSchema.RelationTypeEnum getRequiredRelationType();

	/**
	 *	Set the required ICFBamSchema.RelationTypeEnum attribute RelationType.
	 *
	 *	@param value The required ICFBamSchema.RelationTypeEnum attribute RelationType value to be applied.
	 */
	void setRequiredRelationType(ICFBamSchema.RelationTypeEnum value);

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
	 *	Get the optional String attribute Suffix.
	 *
	 *	@return	The optional String attribute Suffix.
	 */
	String getOptionalSuffix();

	/**
	 *	Set the optional String attribute Suffix.
	 *
	 *	@param value The optional String attribute Suffix value to be applied.
	 */
	void setOptionalSuffix(String value);

	/**
	 *	Get the required CFLibDbKeyHash256 attribute FromIndexId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute FromIndexId.
	 */
	CFLibDbKeyHash256 getRequiredFromIndexId();

	/**
	 *	Get the required CFLibDbKeyHash256 attribute ToTableId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute ToTableId.
	 */
	CFLibDbKeyHash256 getRequiredToTableId();

	/**
	 *	Get the required CFLibDbKeyHash256 attribute ToIndexId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute ToIndexId.
	 */
	CFLibDbKeyHash256 getRequiredToIndexId();

	/**
	 *	Get the required boolean attribute IsRequired.
	 *
	 *	@return	The required boolean attribute IsRequired.
	 */
	boolean getRequiredIsRequired();

	/**
	 *	Set the required boolean attribute IsRequired.
	 *
	 *	@param value The required boolean attribute IsRequired value to be applied.
	 */
	void setRequiredIsRequired(boolean value);

	/**
	 *	Get the required boolean attribute IsXsdContainer.
	 *
	 *	@return	The required boolean attribute IsXsdContainer.
	 */
	boolean getRequiredIsXsdContainer();

	/**
	 *	Set the required boolean attribute IsXsdContainer.
	 *
	 *	@param value The required boolean attribute IsXsdContainer value to be applied.
	 */
	void setRequiredIsXsdContainer(boolean value);

	/**
	 *	Get the required boolean attribute IsLateResolver.
	 *
	 *	@return	The required boolean attribute IsLateResolver.
	 */
	boolean getRequiredIsLateResolver();

	/**
	 *	Set the required boolean attribute IsLateResolver.
	 *
	 *	@param value The required boolean attribute IsLateResolver value to be applied.
	 */
	void setRequiredIsLateResolver(boolean value);

	/**
	 *	Get the required boolean attribute AllowAddendum.
	 *
	 *	@return	The required boolean attribute AllowAddendum.
	 */
	boolean getRequiredAllowAddendum();

	/**
	 *	Set the required boolean attribute AllowAddendum.
	 *
	 *	@param value The required boolean attribute AllowAddendum value to be applied.
	 */
	void setRequiredAllowAddendum(boolean value);

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute NarrowedId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute NarrowedId.
	 */
	CFLibDbKeyHash256 getOptionalNarrowedId();

}
