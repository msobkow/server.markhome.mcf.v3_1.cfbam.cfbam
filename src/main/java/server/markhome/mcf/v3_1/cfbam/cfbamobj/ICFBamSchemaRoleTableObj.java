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

public interface ICFBamSchemaRoleTableObj
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
	 *	Instantiate a new SchemaRole instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamSchemaRoleObj newInstance();

	/**
	 *	Instantiate a new SchemaRole edition of the specified SchemaRole instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamSchemaRoleEditObj newEditInstance( ICFBamSchemaRoleObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamSchemaRoleObj realiseSchemaRole( ICFBamSchemaRoleObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamSchemaRoleObj createSchemaRole( ICFBamSchemaRoleObj Obj );

	/**
	 *	Read a SchemaRole-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SchemaRole-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamSchemaRoleObj readSchemaRole( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a SchemaRole-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SchemaRole-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamSchemaRoleObj readSchemaRole( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamSchemaRoleObj readCachedSchemaRole( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeSchemaRole( ICFBamSchemaRoleObj obj );

	void deepDisposeSchemaRole( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamSchemaRoleObj lockSchemaRole( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the SchemaRole-derived instances in the database.
	 *
	 *	@return	List of ICFBamSchemaRoleObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamSchemaRoleObj> readAllSchemaRole();

	/**
	 *	Return a sorted map of all the SchemaRole-derived instances in the database.
	 *
	 *	@return	List of ICFBamSchemaRoleObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamSchemaRoleObj> readAllSchemaRole( boolean forceRead );

	List<ICFBamSchemaRoleObj> readCachedAllSchemaRole();

	/**
	 *	Get the CFBamRoleDefObj instance for the primary key attributes.
	 *
	 *	@param	Id	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@return	CFBamRoleDefObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamSchemaRoleObj readSchemaRoleByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamRoleDefObj instance for the primary key attributes.
	 *
	 *	@param	Id	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@return	CFBamRoleDefObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamSchemaRoleObj readSchemaRoleByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamRoleDefObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@return	CFBamRoleDefObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamSchemaRoleObj readSchemaRoleByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamRoleDefObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@return	CFBamRoleDefObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamSchemaRoleObj readSchemaRoleByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRoleDefObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaRoleObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaRoleObj> readSchemaRoleByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamSchemaRoleObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaRoleObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaRoleObj> readSchemaRoleByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRoleDefObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaRoleObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaRoleObj> readSchemaRoleByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamSchemaRoleObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaRoleObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaRoleObj> readSchemaRoleByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the CFBamRoleDefObj instance for the unique UDefIdx key.
	 *
	 *	@param	ScopeId	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@param	DefSchemaId	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@return	CFBamRoleDefObj cached instance for the unique UDefIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamSchemaRoleObj readSchemaRoleByUDefIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name );

	/**
	 *	Get the CFBamRoleDefObj instance for the unique UDefIdx key.
	 *
	 *	@param	ScopeId	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@param	DefSchemaId	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@return	CFBamRoleDefObj refreshed instance for the unique UDefIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamSchemaRoleObj readSchemaRoleByUDefIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamSchemaRoleObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaRoleObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaRoleObj> readSchemaRoleBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Get the map of CFBamSchemaRoleObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaRoleObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaRoleObj> readSchemaRoleBySchemaIdx( CFLibDbKeyHash256 SchemaDefId,
		boolean forceRead );

	ICFBamSchemaRoleObj readCachedSchemaRoleByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamSchemaRoleObj readCachedSchemaRoleByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamSchemaRoleObj> readCachedSchemaRoleByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamSchemaRoleObj> readCachedSchemaRoleByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	ICFBamSchemaRoleObj readCachedSchemaRoleByUDefIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name );

	List<ICFBamSchemaRoleObj> readCachedSchemaRoleBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	void deepDisposeSchemaRoleByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeSchemaRoleByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeSchemaRoleByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeSchemaRoleByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeSchemaRoleByUDefIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name );

	void deepDisposeSchemaRoleBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Internal use only.
	 */
	ICFBamSchemaRoleObj updateSchemaRole( ICFBamSchemaRoleObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSchemaRole( ICFBamSchemaRoleObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The SchemaRole key attribute of the instance generating the id.
	 */
	void deleteSchemaRoleByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaRole key attribute of the instance generating the id.
	 */
	void deleteSchemaRoleByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The SchemaRole key attribute of the instance generating the id.
	 */
	void deleteSchemaRoleByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The SchemaRole key attribute of the instance generating the id.
	 */
	void deleteSchemaRoleByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@param	DefSchemaId	The SchemaRole key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaRole key attribute of the instance generating the id.
	 */
	void deleteSchemaRoleByUDefIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaDefId	The SchemaRole key attribute of the instance generating the id.
	 */
	void deleteSchemaRoleBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );
}
