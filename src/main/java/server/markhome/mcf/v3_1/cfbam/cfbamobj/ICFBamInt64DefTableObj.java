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

public interface ICFBamInt64DefTableObj
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
	 *	Instantiate a new Int64Def instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamInt64DefObj newInstance();

	/**
	 *	Instantiate a new Int64Def edition of the specified Int64Def instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamInt64DefEditObj newEditInstance( ICFBamInt64DefObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamInt64DefObj realiseInt64Def( ICFBamInt64DefObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamInt64DefObj createInt64Def( ICFBamInt64DefObj Obj );

	/**
	 *	Read a Int64Def-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Int64Def-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamInt64DefObj readInt64Def( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a Int64Def-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Int64Def-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamInt64DefObj readInt64Def( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamInt64DefObj readCachedInt64Def( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeInt64Def( ICFBamInt64DefObj obj );

	void deepDisposeInt64Def( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamInt64DefObj lockInt64Def( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the Int64Def-derived instances in the database.
	 *
	 *	@return	List of ICFBamInt64DefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamInt64DefObj> readAllInt64Def();

	/**
	 *	Return a sorted map of all the Int64Def-derived instances in the database.
	 *
	 *	@return	List of ICFBamInt64DefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamInt64DefObj> readAllInt64Def( boolean forceRead );

	List<ICFBamInt64DefObj> readCachedAllInt64Def();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamInt64DefObj readInt64DefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamInt64DefObj readInt64DefByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamInt64DefObj readInt64DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamInt64DefObj readInt64DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt64DefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt64DefObj> readInt64DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamInt64DefObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt64DefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt64DefObj> readInt64DefByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt64DefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt64DefObj> readInt64DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamInt64DefObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt64DefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt64DefObj> readInt64DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt64DefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt64DefObj> readInt64DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamInt64DefObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt64DefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt64DefObj> readInt64DefByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt64DefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt64DefObj> readInt64DefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamInt64DefObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt64DefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt64DefObj> readInt64DefByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt64DefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt64DefObj> readInt64DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamInt64DefObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt64DefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt64DefObj> readInt64DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt64DefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt64DefObj> readInt64DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamInt64DefObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt64DefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt64DefObj> readInt64DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	ICFBamInt64DefObj readCachedInt64DefByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamInt64DefObj readCachedInt64DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamInt64DefObj> readCachedInt64DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamInt64DefObj> readCachedInt64DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamInt64DefObj> readCachedInt64DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamInt64DefObj> readCachedInt64DefByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamInt64DefObj> readCachedInt64DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamInt64DefObj> readCachedInt64DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeInt64DefByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeInt64DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeInt64DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeInt64DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeInt64DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeInt64DefByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeInt64DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeInt64DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 */
	ICFBamInt64DefObj updateInt64Def( ICFBamInt64DefObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteInt64Def( ICFBamInt64DefObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The Int64Def key attribute of the instance generating the id.
	 */
	void deleteInt64DefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Int64Def key attribute of the instance generating the id.
	 */
	void deleteInt64DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Int64Def key attribute of the instance generating the id.
	 */
	void deleteInt64DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The Int64Def key attribute of the instance generating the id.
	 */
	void deleteInt64DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The Int64Def key attribute of the instance generating the id.
	 */
	void deleteInt64DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The Int64Def key attribute of the instance generating the id.
	 */
	void deleteInt64DefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Int64Def key attribute of the instance generating the id.
	 */
	void deleteInt64DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Int64Def key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Int64Def key attribute of the instance generating the id.
	 */
	void deleteInt64DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Move the CFBamInt64DefObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamInt64DefObj refreshed cache instance.
	 */
	ICFBamInt64DefObj moveUpInt64Def( ICFBamInt64DefObj Obj );

	/**
	 *	Move the CFBamInt64DefObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamInt64DefObj refreshed cache instance.
	 */
	ICFBamInt64DefObj moveDownInt64Def( ICFBamInt64DefObj Obj );
}
