// Description: Java 25 Instance Edit Object interface for CFBam RelationCol.

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

public interface ICFBamRelationColEditObj
	extends ICFBamRelationColObj
{
	/*
	 *	Get the original for this edition as the base type for the class hierarchy.
	 *
	 *	@return The original, non-modifiable instance as a base ICFBamRelationColObj.
	 */
	ICFBamRelationColObj getOrig();

	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFBamRelationColObj.
	 */
	ICFBamRelationColObj getOrigAsRelationCol();

	/*
	 *	create() may return a different instance than the
	 *	one used to invoke the operation.  All future references
	 *	should be to the returned instance, not the original
	 *	invoker.  You should lose all references to the original
	 *	invoker.
	 *
	 *	@return The created instance.
	 */
	ICFBamRelationColObj create();

	/*
	 *	Update the instance.
	 */
	CFBamRelationColEditObj update();

	/*
	 *	Delete the instance.
	 */
	CFBamRelationColEditObj deleteInstance();

	/**
	 *	Set the user who created this instance.
	 *
	 *	@param	value	The ICFSecSecUserObj instance who created this instance.
	 */
	void setCreatedBy( ICFSecSecUserObj value );

	/**
	 *	Set the Calendar date-time this instance was created.
	 *
	 *	@param	value	The Calendar value for the create time of the instance.
	 */
	void setCreatedAt( LocalDateTime value );

	/**
	 *	Set the user who updated this instance.
	 *
	 *	@param	value	The ICFSecSecUserObj instance who updated this instance.
	 */
	void setUpdatedBy( ICFSecSecUserObj value );

	/**
	 *	Set the Calendar date-time this instance was updated.
	 *
	 *	@param	value	The Calendar value for the create time of the instance.
	 */
	void setUpdatedAt( LocalDateTime value );

	/**
	 *	Get the ICFBamRelationObj instance referenced by the Relation key.
	 *
	 *	@return	The ICFBamRelationObj instance referenced by the Relation key.
	 */
	ICFBamRelationObj getRequiredContainerRelation();

	/**
	 *	Set the ICFBamRelationObj instance referenced by the Relation key.
	 *
	 *	@param	value	the ICFBamRelationObj instance to be referenced by the Relation key.
	 */
	void setRequiredContainerRelation( ICFBamRelationObj value );

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
	 *	Get the ICFBamRelationColObj instance referenced by the Prev key.
	 *
	 *	@return	The ICFBamRelationColObj instance referenced by the Prev key.
	 */
	ICFBamRelationColObj getOptionalLookupPrev();

	/**
	 *	Set the ICFBamRelationColObj instance referenced by the Prev key.
	 *
	 *	@param	value	the ICFBamRelationColObj instance to be referenced by the Prev key.
	 */
	void setOptionalLookupPrev( ICFBamRelationColObj value );

	/**
	 *	Get the ICFBamRelationColObj instance referenced by the Next key.
	 *
	 *	@return	The ICFBamRelationColObj instance referenced by the Next key.
	 */
	ICFBamRelationColObj getOptionalLookupNext();

	/**
	 *	Set the ICFBamRelationColObj instance referenced by the Next key.
	 *
	 *	@param	value	the ICFBamRelationColObj instance to be referenced by the Next key.
	 */
	void setOptionalLookupNext( ICFBamRelationColObj value );

	/**
	 *	Get the ICFBamIndexColObj instance referenced by the FromCol key.
	 *
	 *	@return	The ICFBamIndexColObj instance referenced by the FromCol key.
	 */
	ICFBamIndexColObj getRequiredLookupFromCol();

	/**
	 *	Set the ICFBamIndexColObj instance referenced by the FromCol key.
	 *
	 *	@param	value	the ICFBamIndexColObj instance to be referenced by the FromCol key.
	 */
	void setRequiredLookupFromCol( ICFBamIndexColObj value );

	/**
	 *	Get the ICFBamIndexColObj instance referenced by the ToCol key.
	 *
	 *	@return	The ICFBamIndexColObj instance referenced by the ToCol key.
	 */
	ICFBamIndexColObj getRequiredLookupToCol();

	/**
	 *	Set the ICFBamIndexColObj instance referenced by the ToCol key.
	 *
	 *	@param	value	the ICFBamIndexColObj instance to be referenced by the ToCol key.
	 */
	void setRequiredLookupToCol( ICFBamIndexColObj value );

	/**
	 *	Get the required CFLibDbKeyHash256 attribute RelationId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute RelationId.
	 */
	CFLibDbKeyHash256 getRequiredRelationId();

	/**
	 *	Get the required CFLibDbKeyHash256 attribute Id.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute Id.
	 */
	CFLibDbKeyHash256 getRequiredId();

	/**
	 *	Set the required CFLibDbKeyHash256 attribute Id.
	 *
	 *	@param value The required CFLibDbKeyHash256 attribute Id value to be applied.
	 */
	void setRequiredId(CFLibDbKeyHash256 value);

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
	 *	Get the required CFLibDbKeyHash256 attribute FromColId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute FromColId.
	 */
	CFLibDbKeyHash256 getRequiredFromColId();

	/**
	 *	Get the required CFLibDbKeyHash256 attribute ToColId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute ToColId.
	 */
	CFLibDbKeyHash256 getRequiredToColId();

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute PrevId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute PrevId.
	 */
	CFLibDbKeyHash256 getOptionalPrevId();

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute NextId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute NextId.
	 */
	CFLibDbKeyHash256 getOptionalNextId();

	public void copyRecToOrig();
	public void copyOrigToRec();

}
