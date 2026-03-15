// Description: Java 25 Object interface for CFBam SchemaRef.

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
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public interface ICFBamSchemaRefObj
	extends ICFBamScopeObj
{
	/**
	 *	Move this object up in the chain and refresh the cache.
	 *
	 *	@return	ICFBamSchemaRefObj the reference to the moved and refreshed instance.
	 */
	ICFBamSchemaRefObj moveUp();

	/**
	 *	Move this object down in the chain and refresh the cache.
	 *
	 *	@return	ICFBamSchemaRefObj the reference to the moved and refreshed instance.
	 */
	ICFBamSchemaRefObj moveDown();

	/**
	 *	Get the current edition of this SchemaRef instance as a ICFBamSchemaRefEditObj.
	 *
	 *	@return	The ICFBamSchemaRefEditObj edition of this instance.
	 */
	ICFBamSchemaRefEditObj getEditAsSchemaRef();

	/**
	 *	Get the ICFBamSchemaRefTableObj table cache which manages this instance.
	 *
	 *	@return	ICFBamSchemaRefTableObj table cache which manages this instance.
	 */
	ICFBamSchemaRefTableObj getSchemaRefTable();

	/**
	 *	Get the ICFBamSchemaRef instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFBamSchemaRef instance which currently backs this object.
	 */
	ICFBamSchemaRef getSchemaRefRec();

	/**
	 *	Get the required ICFBamSchemaDefObj instance referenced by the Schema key.
	 *
	 *	@return	The required ICFBamSchemaDefObj instance referenced by the Schema key.
	 */
	ICFBamSchemaDefObj getRequiredContainerSchema();

	/**
	 *	Get the required ICFBamSchemaDefObj instance referenced by the Schema key.
	 *
	 *	@return	The required ICFBamSchemaDefObj instance referenced by the Schema key.
	 */
	ICFBamSchemaDefObj getRequiredContainerSchema( boolean forceRead );

	/**
	 *	Get the optional ICFBamSchemaDefObj instance referenced by the RefSchema key.
	 *
	 *	@return	The optional ICFBamSchemaDefObj instance referenced by the RefSchema key.
	 */
	ICFBamSchemaDefObj getOptionalLookupRefSchema();

	/**
	 *	Get the optional ICFBamSchemaDefObj instance referenced by the RefSchema key.
	 *
	 *	@return	The optional ICFBamSchemaDefObj instance referenced by the RefSchema key.
	 */
	ICFBamSchemaDefObj getOptionalLookupRefSchema( boolean forceRead );

	/**
	 *	Get the optional ICFBamSchemaRefObj instance referenced by the Prev key.
	 *
	 *	@return	The optional ICFBamSchemaRefObj instance referenced by the Prev key.
	 */
	ICFBamSchemaRefObj getOptionalLookupPrev();

	/**
	 *	Get the optional ICFBamSchemaRefObj instance referenced by the Prev key.
	 *
	 *	@return	The optional ICFBamSchemaRefObj instance referenced by the Prev key.
	 */
	ICFBamSchemaRefObj getOptionalLookupPrev( boolean forceRead );

	/**
	 *	Get the optional ICFBamSchemaRefObj instance referenced by the Next key.
	 *
	 *	@return	The optional ICFBamSchemaRefObj instance referenced by the Next key.
	 */
	ICFBamSchemaRefObj getOptionalLookupNext();

	/**
	 *	Get the optional ICFBamSchemaRefObj instance referenced by the Next key.
	 *
	 *	@return	The optional ICFBamSchemaRefObj instance referenced by the Next key.
	 */
	ICFBamSchemaRefObj getOptionalLookupNext( boolean forceRead );

	/**
	 *	Get the required CFLibDbKeyHash256 attribute Id.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute Id.
	 */
	CFLibDbKeyHash256 getRequiredId();

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
	 *	Get the required String attribute RefModelName.
	 *
	 *	@return	The required String attribute RefModelName.
	 */
	String getRequiredRefModelName();

	/**
	 *	Get the required String attribute IncludeRoot.
	 *
	 *	@return	The required String attribute IncludeRoot.
	 */
	String getRequiredIncludeRoot();

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
