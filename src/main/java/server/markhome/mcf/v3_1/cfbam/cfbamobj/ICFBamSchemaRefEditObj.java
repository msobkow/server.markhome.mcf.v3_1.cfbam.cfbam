// Description: Java 25 Instance Edit Object interface for CFBam SchemaRef.

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

public interface ICFBamSchemaRefEditObj
	extends ICFBamSchemaRefObj, ICFBamScopeEditObj
{
	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFBamSchemaRefObj.
	 */
	ICFBamSchemaRefObj getOrigAsSchemaRef();

	/**
	 *	Get the ICFBamSchemaDefObj instance referenced by the Schema key.
	 *
	 *	@return	The ICFBamSchemaDefObj instance referenced by the Schema key.
	 */
	ICFBamSchemaDefObj getRequiredContainerSchema();

	/**
	 *	Set the ICFBamSchemaDefObj instance referenced by the Schema key.
	 *
	 *	@param	value	the ICFBamSchemaDefObj instance to be referenced by the Schema key.
	 */
	void setRequiredContainerSchema( ICFBamSchemaDefObj value );

	/**
	 *	Get the ICFBamSchemaDefObj instance referenced by the RefSchema key.
	 *
	 *	@return	The ICFBamSchemaDefObj instance referenced by the RefSchema key.
	 */
	ICFBamSchemaDefObj getOptionalLookupRefSchema();

	/**
	 *	Set the ICFBamSchemaDefObj instance referenced by the RefSchema key.
	 *
	 *	@param	value	the ICFBamSchemaDefObj instance to be referenced by the RefSchema key.
	 */
	void setOptionalLookupRefSchema( ICFBamSchemaDefObj value );

	/**
	 *	Get the ICFBamSchemaRefObj instance referenced by the Prev key.
	 *
	 *	@return	The ICFBamSchemaRefObj instance referenced by the Prev key.
	 */
	ICFBamSchemaRefObj getOptionalLookupPrev();

	/**
	 *	Set the ICFBamSchemaRefObj instance referenced by the Prev key.
	 *
	 *	@param	value	the ICFBamSchemaRefObj instance to be referenced by the Prev key.
	 */
	void setOptionalLookupPrev( ICFBamSchemaRefObj value );

	/**
	 *	Get the ICFBamSchemaRefObj instance referenced by the Next key.
	 *
	 *	@return	The ICFBamSchemaRefObj instance referenced by the Next key.
	 */
	ICFBamSchemaRefObj getOptionalLookupNext();

	/**
	 *	Set the ICFBamSchemaRefObj instance referenced by the Next key.
	 *
	 *	@param	value	the ICFBamSchemaRefObj instance to be referenced by the Next key.
	 */
	void setOptionalLookupNext( ICFBamSchemaRefObj value );

	/**
	 *	Get the required CFLibDbKeyHash256 attribute SchemaId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute SchemaId.
	 */
	CFLibDbKeyHash256 getRequiredSchemaId();

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
	 *	Get the required String attribute RefModelName.
	 *
	 *	@return	The required String attribute RefModelName.
	 */
	String getRequiredRefModelName();

	/**
	 *	Set the required String attribute RefModelName.
	 *
	 *	@param value The required String attribute RefModelName value to be applied.
	 */
	void setRequiredRefModelName(String value);

	/**
	 *	Get the required String attribute IncludeRoot.
	 *
	 *	@return	The required String attribute IncludeRoot.
	 */
	String getRequiredIncludeRoot();

	/**
	 *	Set the required String attribute IncludeRoot.
	 *
	 *	@param value The required String attribute IncludeRoot value to be applied.
	 */
	void setRequiredIncludeRoot(String value);

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute RefSchemaId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute RefSchemaId.
	 */
	CFLibDbKeyHash256 getOptionalRefSchemaId();

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

}
