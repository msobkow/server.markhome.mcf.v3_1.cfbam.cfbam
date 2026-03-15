// Description: Java 25 Instance Edit Object interface for CFBam Index.

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

public interface ICFBamIndexEditObj
	extends ICFBamIndexObj, ICFBamScopeEditObj
{
	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFBamIndexObj.
	 */
	ICFBamIndexObj getOrigAsIndex();

	/**
	 *	Get the ICFBamTableObj instance referenced by the Table key.
	 *
	 *	@return	The ICFBamTableObj instance referenced by the Table key.
	 */
	ICFBamTableObj getRequiredContainerTable();

	/**
	 *	Set the ICFBamTableObj instance referenced by the Table key.
	 *
	 *	@param	value	the ICFBamTableObj instance to be referenced by the Table key.
	 */
	void setRequiredContainerTable( ICFBamTableObj value );

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
	 *	Get a list ICFBamIndexColObj instances referenced by the Columns key.
	 *
	 *	@return	The (potentially empty) list of ICFBamIndexColObj instances referenced by the Columns key.
	 */
	List<ICFBamIndexColObj> getOptionalComponentsColumns();

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
	 *	Get the required boolean attribute IsUnique.
	 *
	 *	@return	The required boolean attribute IsUnique.
	 */
	boolean getRequiredIsUnique();

	/**
	 *	Set the required boolean attribute IsUnique.
	 *
	 *	@param value The required boolean attribute IsUnique value to be applied.
	 */
	void setRequiredIsUnique(boolean value);

	/**
	 *	Get the required boolean attribute IsDbMapped.
	 *
	 *	@return	The required boolean attribute IsDbMapped.
	 */
	boolean getRequiredIsDbMapped();

	/**
	 *	Set the required boolean attribute IsDbMapped.
	 *
	 *	@param value The required boolean attribute IsDbMapped value to be applied.
	 */
	void setRequiredIsDbMapped(boolean value);

}
