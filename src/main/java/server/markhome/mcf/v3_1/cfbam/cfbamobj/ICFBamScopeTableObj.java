// Description: Java 25 Table Object interface for CFBam.

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

public interface ICFBamScopeTableObj
{
	public ICFBamSchemaObj getSchema();
	public void setSchema( ICFBamSchemaObj value );

	public void minimizeMemory();

	public String getTableName();
	public String getTableDbName();

	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	public int getClassCode();

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	// public static int getBackingClassCode();

	Class getObjQualifyingClass();

	/**
	 *	Instantiate a new Scope instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamScopeObj newInstance();

	/**
	 *	Instantiate a new Scope edition of the specified Scope instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamScopeEditObj newEditInstance( ICFBamScopeObj orig );

	/**
	 *	Construct an appropriate subclass instance based on the specified class code.
	 *
	 *	@param	backingClassCode	The backing store class code used to identify the proposed instance class.
	 *
	 *	@return	ICFBamScopeObj instance, which may be a subclass of a Scope instance.
	 */
	ICFBamScopeObj constructByClassCode( int backingClassCode );

	/**
	 *	Internal use only.
	 */
	ICFBamScopeObj realiseScope( ICFBamScopeObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamScopeObj createScope( ICFBamScopeObj Obj );

	/**
	 *	Read a Scope-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Scope-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamScopeObj readScope( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a Scope-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Scope-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamScopeObj readScope( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamScopeObj readCachedScope( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeScope( ICFBamScopeObj obj );

	void deepDisposeScope( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamScopeObj lockScope( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the Scope-derived instances in the database.
	 *
	 *	@return	List of ICFBamScopeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamScopeObj> readAllScope();

	/**
	 *	Return a sorted map of all the Scope-derived instances in the database.
	 *
	 *	@return	List of ICFBamScopeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamScopeObj> readAllScope( boolean forceRead );

	List<ICFBamScopeObj> readCachedAllScope();

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Scope key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamScopeObj readScopeByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Scope key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamScopeObj readScopeByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the map of CFBamScopeObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The Scope key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamScopeObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamScopeObj> readScopeByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Get the map of CFBamScopeObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The Scope key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamScopeObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamScopeObj> readScopeByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead );

	ICFBamScopeObj readCachedScopeByIdIdx( CFLibDbKeyHash256 Id );

	List<ICFBamScopeObj> readCachedScopeByTenantIdx( CFLibDbKeyHash256 TenantId );

	void deepDisposeScopeByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeScopeByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Internal use only.
	 */
	ICFBamScopeObj updateScope( ICFBamScopeObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteScope( ICFBamScopeObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The Scope key attribute of the instance generating the id.
	 */
	void deleteScopeByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The Scope key attribute of the instance generating the id.
	 */
	void deleteScopeByTenantIdx( CFLibDbKeyHash256 TenantId );
}
