// Description: Java 25 Instance Edit Object interface for CFBam IndexCol.

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

public interface ICFBamIndexColEditObj
	extends ICFBamIndexColObj
{
	/*
	 *	Get the original for this edition as the base type for the class hierarchy.
	 *
	 *	@return The original, non-modifiable instance as a base ICFBamIndexColObj.
	 */
	ICFBamIndexColObj getOrig();

	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFBamIndexColObj.
	 */
	ICFBamIndexColObj getOrigAsIndexCol();

	/*
	 *	create() may return a different instance than the
	 *	one used to invoke the operation.  All future references
	 *	should be to the returned instance, not the original
	 *	invoker.  You should lose all references to the original
	 *	invoker.
	 *
	 *	@return The created instance.
	 */
	ICFBamIndexColObj create();

	/*
	 *	Update the instance.
	 */
	CFBamIndexColEditObj update();

	/*
	 *	Delete the instance.
	 */
	CFBamIndexColEditObj deleteInstance();

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
	 *	Get the ICFBamIndexObj instance referenced by the Index key.
	 *
	 *	@return	The ICFBamIndexObj instance referenced by the Index key.
	 */
	ICFBamIndexObj getRequiredContainerIndex();

	/**
	 *	Set the ICFBamIndexObj instance referenced by the Index key.
	 *
	 *	@param	value	the ICFBamIndexObj instance to be referenced by the Index key.
	 */
	void setRequiredContainerIndex( ICFBamIndexObj value );

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
	 *	Get a list ICFBamRelationColObj instances referenced by the RefRelFromCol key.
	 *
	 *	@return	The (potentially empty) list of ICFBamRelationColObj instances referenced by the RefRelFromCol key.
	 */
	List<ICFBamRelationColObj> getOptionalChildrenRefRelFromCol();

	/**
	 *	Get a list ICFBamRelationColObj instances referenced by the RefRelToCol key.
	 *
	 *	@return	The (potentially empty) list of ICFBamRelationColObj instances referenced by the RefRelToCol key.
	 */
	List<ICFBamRelationColObj> getOptionalChildrenRefRelToCol();

	/**
	 *	Get the ICFBamIndexColObj instance referenced by the Prev key.
	 *
	 *	@return	The ICFBamIndexColObj instance referenced by the Prev key.
	 */
	ICFBamIndexColObj getOptionalLookupPrev();

	/**
	 *	Set the ICFBamIndexColObj instance referenced by the Prev key.
	 *
	 *	@param	value	the ICFBamIndexColObj instance to be referenced by the Prev key.
	 */
	void setOptionalLookupPrev( ICFBamIndexColObj value );

	/**
	 *	Get the ICFBamIndexColObj instance referenced by the Next key.
	 *
	 *	@return	The ICFBamIndexColObj instance referenced by the Next key.
	 */
	ICFBamIndexColObj getOptionalLookupNext();

	/**
	 *	Set the ICFBamIndexColObj instance referenced by the Next key.
	 *
	 *	@param	value	the ICFBamIndexColObj instance to be referenced by the Next key.
	 */
	void setOptionalLookupNext( ICFBamIndexColObj value );

	/**
	 *	Get the ICFBamValueObj instance referenced by the Column key.
	 *
	 *	@return	The ICFBamValueObj instance referenced by the Column key.
	 */
	ICFBamValueObj getRequiredLookupColumn();

	/**
	 *	Set the ICFBamValueObj instance referenced by the Column key.
	 *
	 *	@param	value	the ICFBamValueObj instance to be referenced by the Column key.
	 */
	void setRequiredLookupColumn( ICFBamValueObj value );

	/**
	 *	Get the required CFLibDbKeyHash256 attribute IndexId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute IndexId.
	 */
	CFLibDbKeyHash256 getRequiredIndexId();

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
	 *	Get the required CFLibDbKeyHash256 attribute ColumnId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute ColumnId.
	 */
	CFLibDbKeyHash256 getRequiredColumnId();

	/**
	 *	Get the required boolean attribute IsAscending.
	 *
	 *	@return	The required boolean attribute IsAscending.
	 */
	boolean getRequiredIsAscending();

	/**
	 *	Set the required boolean attribute IsAscending.
	 *
	 *	@param value The required boolean attribute IsAscending value to be applied.
	 */
	void setRequiredIsAscending(boolean value);

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
