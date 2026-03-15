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

public interface ICFBamDbKeyHash512GenTableObj
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
	 *	Instantiate a new DbKeyHash512Gen instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamDbKeyHash512GenObj newInstance();

	/**
	 *	Instantiate a new DbKeyHash512Gen edition of the specified DbKeyHash512Gen instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamDbKeyHash512GenEditObj newEditInstance( ICFBamDbKeyHash512GenObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash512GenObj realiseDbKeyHash512Gen( ICFBamDbKeyHash512GenObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash512GenObj createDbKeyHash512Gen( ICFBamDbKeyHash512GenObj Obj );

	/**
	 *	Read a DbKeyHash512Gen-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DbKeyHash512Gen-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDbKeyHash512GenObj readDbKeyHash512Gen( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a DbKeyHash512Gen-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DbKeyHash512Gen-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDbKeyHash512GenObj readDbKeyHash512Gen( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamDbKeyHash512GenObj readCachedDbKeyHash512Gen( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeDbKeyHash512Gen( ICFBamDbKeyHash512GenObj obj );

	void deepDisposeDbKeyHash512Gen( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash512GenObj lockDbKeyHash512Gen( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the DbKeyHash512Gen-derived instances in the database.
	 *
	 *	@return	List of ICFBamDbKeyHash512GenObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDbKeyHash512GenObj> readAllDbKeyHash512Gen();

	/**
	 *	Return a sorted map of all the DbKeyHash512Gen-derived instances in the database.
	 *
	 *	@return	List of ICFBamDbKeyHash512GenObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDbKeyHash512GenObj> readAllDbKeyHash512Gen( boolean forceRead );

	List<ICFBamDbKeyHash512GenObj> readCachedAllDbKeyHash512Gen();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash512GenObj readDbKeyHash512GenByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash512GenObj readDbKeyHash512GenByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash512GenObj readDbKeyHash512GenByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash512GenObj readDbKeyHash512GenByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512GenObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512GenObj> readDbKeyHash512GenByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamDbKeyHash512GenObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512GenObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512GenObj> readDbKeyHash512GenByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512GenObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512GenObj> readDbKeyHash512GenByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamDbKeyHash512GenObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512GenObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512GenObj> readDbKeyHash512GenByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512GenObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512GenObj> readDbKeyHash512GenByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamDbKeyHash512GenObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512GenObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512GenObj> readDbKeyHash512GenByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512GenObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512GenObj> readDbKeyHash512GenByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamDbKeyHash512GenObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512GenObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512GenObj> readDbKeyHash512GenByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512GenObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512GenObj> readDbKeyHash512GenByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamDbKeyHash512GenObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512GenObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512GenObj> readDbKeyHash512GenByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512GenObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512GenObj> readDbKeyHash512GenByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamDbKeyHash512GenObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512GenObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512GenObj> readDbKeyHash512GenByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDbKeyHash512TypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512GenObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512GenObj> readDbKeyHash512GenBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Get the map of CFBamDbKeyHash512GenObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash512GenObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash512GenObj> readDbKeyHash512GenBySchemaIdx( CFLibDbKeyHash256 SchemaDefId,
		boolean forceRead );

	ICFBamDbKeyHash512GenObj readCachedDbKeyHash512GenByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamDbKeyHash512GenObj readCachedDbKeyHash512GenByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamDbKeyHash512GenObj> readCachedDbKeyHash512GenByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamDbKeyHash512GenObj> readCachedDbKeyHash512GenByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamDbKeyHash512GenObj> readCachedDbKeyHash512GenByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamDbKeyHash512GenObj> readCachedDbKeyHash512GenByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamDbKeyHash512GenObj> readCachedDbKeyHash512GenByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamDbKeyHash512GenObj> readCachedDbKeyHash512GenByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	List<ICFBamDbKeyHash512GenObj> readCachedDbKeyHash512GenBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	void deepDisposeDbKeyHash512GenByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeDbKeyHash512GenByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeDbKeyHash512GenByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeDbKeyHash512GenByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeDbKeyHash512GenByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeDbKeyHash512GenByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeDbKeyHash512GenByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeDbKeyHash512GenByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeDbKeyHash512GenBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash512GenObj updateDbKeyHash512Gen( ICFBamDbKeyHash512GenObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteDbKeyHash512Gen( ICFBamDbKeyHash512GenObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The DbKeyHash512Gen key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512GenByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash512Gen key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512GenByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512GenByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512GenByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512GenByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512GenByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512GenByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512GenByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaDefId	The DbKeyHash512Gen key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash512GenBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Move the CFBamDbKeyHash512GenObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDbKeyHash512GenObj refreshed cache instance.
	 */
	ICFBamDbKeyHash512GenObj moveUpDbKeyHash512Gen( ICFBamDbKeyHash512GenObj Obj );

	/**
	 *	Move the CFBamDbKeyHash512GenObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDbKeyHash512GenObj refreshed cache instance.
	 */
	ICFBamDbKeyHash512GenObj moveDownDbKeyHash512Gen( ICFBamDbKeyHash512GenObj Obj );
}
