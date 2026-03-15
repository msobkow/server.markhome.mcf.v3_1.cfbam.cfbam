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

public interface ICFBamDbKeyHash224DefTableObj
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
	 *	Instantiate a new DbKeyHash224Def instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamDbKeyHash224DefObj newInstance();

	/**
	 *	Instantiate a new DbKeyHash224Def edition of the specified DbKeyHash224Def instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamDbKeyHash224DefEditObj newEditInstance( ICFBamDbKeyHash224DefObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash224DefObj realiseDbKeyHash224Def( ICFBamDbKeyHash224DefObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash224DefObj createDbKeyHash224Def( ICFBamDbKeyHash224DefObj Obj );

	/**
	 *	Read a DbKeyHash224Def-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DbKeyHash224Def-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDbKeyHash224DefObj readDbKeyHash224Def( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a DbKeyHash224Def-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DbKeyHash224Def-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDbKeyHash224DefObj readDbKeyHash224Def( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamDbKeyHash224DefObj readCachedDbKeyHash224Def( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeDbKeyHash224Def( ICFBamDbKeyHash224DefObj obj );

	void deepDisposeDbKeyHash224Def( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash224DefObj lockDbKeyHash224Def( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the DbKeyHash224Def-derived instances in the database.
	 *
	 *	@return	List of ICFBamDbKeyHash224DefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDbKeyHash224DefObj> readAllDbKeyHash224Def();

	/**
	 *	Return a sorted map of all the DbKeyHash224Def-derived instances in the database.
	 *
	 *	@return	List of ICFBamDbKeyHash224DefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDbKeyHash224DefObj> readAllDbKeyHash224Def( boolean forceRead );

	List<ICFBamDbKeyHash224DefObj> readCachedAllDbKeyHash224Def();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash224DefObj readDbKeyHash224DefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash224DefObj readDbKeyHash224DefByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash224DefObj readDbKeyHash224DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDbKeyHash224DefObj readDbKeyHash224DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash224DefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash224DefObj> readDbKeyHash224DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamDbKeyHash224DefObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash224DefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash224DefObj> readDbKeyHash224DefByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash224DefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash224DefObj> readDbKeyHash224DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamDbKeyHash224DefObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash224DefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash224DefObj> readDbKeyHash224DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash224DefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash224DefObj> readDbKeyHash224DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamDbKeyHash224DefObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash224DefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash224DefObj> readDbKeyHash224DefByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash224DefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash224DefObj> readDbKeyHash224DefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamDbKeyHash224DefObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash224DefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash224DefObj> readDbKeyHash224DefByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash224DefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash224DefObj> readDbKeyHash224DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamDbKeyHash224DefObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash224DefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash224DefObj> readDbKeyHash224DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash224DefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash224DefObj> readDbKeyHash224DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamDbKeyHash224DefObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDbKeyHash224DefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDbKeyHash224DefObj> readDbKeyHash224DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	ICFBamDbKeyHash224DefObj readCachedDbKeyHash224DefByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamDbKeyHash224DefObj readCachedDbKeyHash224DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamDbKeyHash224DefObj> readCachedDbKeyHash224DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamDbKeyHash224DefObj> readCachedDbKeyHash224DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamDbKeyHash224DefObj> readCachedDbKeyHash224DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamDbKeyHash224DefObj> readCachedDbKeyHash224DefByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamDbKeyHash224DefObj> readCachedDbKeyHash224DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamDbKeyHash224DefObj> readCachedDbKeyHash224DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeDbKeyHash224DefByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeDbKeyHash224DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeDbKeyHash224DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeDbKeyHash224DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeDbKeyHash224DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeDbKeyHash224DefByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeDbKeyHash224DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeDbKeyHash224DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 */
	ICFBamDbKeyHash224DefObj updateDbKeyHash224Def( ICFBamDbKeyHash224DefObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteDbKeyHash224Def( ICFBamDbKeyHash224DefObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The DbKeyHash224Def key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash224DefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DbKeyHash224Def key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash224DefByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash224Def key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash224DefByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The DbKeyHash224Def key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash224DefByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The DbKeyHash224Def key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash224DefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The DbKeyHash224Def key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash224DefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DbKeyHash224Def key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash224DefByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DbKeyHash224Def key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DbKeyHash224Def key attribute of the instance generating the id.
	 */
	void deleteDbKeyHash224DefByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Move the CFBamDbKeyHash224DefObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDbKeyHash224DefObj refreshed cache instance.
	 */
	ICFBamDbKeyHash224DefObj moveUpDbKeyHash224Def( ICFBamDbKeyHash224DefObj Obj );

	/**
	 *	Move the CFBamDbKeyHash224DefObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDbKeyHash224DefObj refreshed cache instance.
	 */
	ICFBamDbKeyHash224DefObj moveDownDbKeyHash224Def( ICFBamDbKeyHash224DefObj Obj );
}
