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
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfint.cfintpub.*;
import server.markhome.mcf.v3_1.cfbam.cfbampub.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprot.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfint.cfintpubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbampubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprotobj.*;

public interface ICFBamTimestampDefTableObj
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
	 *	Instantiate a new TimestampDef instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamTimestampDefObj newInstance();

	/**
	 *	Instantiate a new TimestampDef edition of the specified TimestampDef instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamTimestampDefEditObj newEditInstance( ICFBamTimestampDefObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamTimestampDefObj realiseTimestampDef( ICFBamTimestampDefObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamTimestampDefObj createTimestampDef( ICFBamTimestampDefObj Obj );

	/**
	 *	Read a TimestampDef-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TimestampDef-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTimestampDefObj readTimestampDef( ICFLibKeyHash256 pkey );

	/**
	 *	Read a TimestampDef-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TimestampDef-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTimestampDefObj readTimestampDef( ICFLibKeyHash256 pkey,
		boolean forceRead );

	ICFBamTimestampDefObj readCachedTimestampDef( ICFLibKeyHash256 pkey );

	public void reallyDeepDisposeTimestampDef( ICFBamTimestampDefObj obj );

	void deepDisposeTimestampDef( ICFLibKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamTimestampDefObj lockTimestampDef( ICFLibKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the TimestampDef-derived instances in the database.
	 *
	 *	@return	List of ICFBamTimestampDefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTimestampDefObj> readAllTimestampDef();

	/**
	 *	Return a sorted map of all the TimestampDef-derived instances in the database.
	 *
	 *	@return	List of ICFBamTimestampDefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTimestampDefObj> readAllTimestampDef( boolean forceRead );

	List<ICFBamTimestampDefObj> readCachedAllTimestampDef();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTimestampDefObj readTimestampDefByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTimestampDefObj readTimestampDefByIdIdx( ICFLibKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTimestampDefObj readTimestampDefByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTimestampDefObj readTimestampDefByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampDefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampDefObj> readTimestampDefByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamTimestampDefObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampDefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampDefObj> readTimestampDefByScopeIdx( ICFLibKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampDefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampDefObj> readTimestampDefByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamTimestampDefObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampDefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampDefObj> readTimestampDefByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampDefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampDefObj> readTimestampDefByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamTimestampDefObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampDefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampDefObj> readTimestampDefByPrevIdx( ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampDefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampDefObj> readTimestampDefByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamTimestampDefObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampDefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampDefObj> readTimestampDefByNextIdx( ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampDefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampDefObj> readTimestampDefByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamTimestampDefObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampDefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampDefObj> readTimestampDefByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampDefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampDefObj> readTimestampDefByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamTimestampDefObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampDefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampDefObj> readTimestampDefByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId,
		boolean forceRead );

	ICFBamTimestampDefObj readCachedTimestampDefByIdIdx( ICFLibKeyHash256 Id );

	ICFBamTimestampDefObj readCachedTimestampDefByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	List<ICFBamTimestampDefObj> readCachedTimestampDefByScopeIdx( ICFLibKeyHash256 ScopeId );

	List<ICFBamTimestampDefObj> readCachedTimestampDefByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	List<ICFBamTimestampDefObj> readCachedTimestampDefByPrevIdx( ICFLibKeyHash256 PrevId );

	List<ICFBamTimestampDefObj> readCachedTimestampDefByNextIdx( ICFLibKeyHash256 NextId );

	List<ICFBamTimestampDefObj> readCachedTimestampDefByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	List<ICFBamTimestampDefObj> readCachedTimestampDefByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	void deepDisposeTimestampDefByIdIdx( ICFLibKeyHash256 Id );

	void deepDisposeTimestampDefByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	void deepDisposeTimestampDefByScopeIdx( ICFLibKeyHash256 ScopeId );

	void deepDisposeTimestampDefByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	void deepDisposeTimestampDefByPrevIdx( ICFLibKeyHash256 PrevId );

	void deepDisposeTimestampDefByNextIdx( ICFLibKeyHash256 NextId );

	void deepDisposeTimestampDefByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	void deepDisposeTimestampDefByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 */
	ICFBamTimestampDefObj updateTimestampDef( ICFBamTimestampDefObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteTimestampDef( ICFBamTimestampDefObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The TimestampDef key attribute of the instance generating the id.
	 */
	void deleteTimestampDefByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TimestampDef key attribute of the instance generating the id.
	 */
	void deleteTimestampDefByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TimestampDef key attribute of the instance generating the id.
	 */
	void deleteTimestampDefByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The TimestampDef key attribute of the instance generating the id.
	 */
	void deleteTimestampDefByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The TimestampDef key attribute of the instance generating the id.
	 */
	void deleteTimestampDefByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The TimestampDef key attribute of the instance generating the id.
	 */
	void deleteTimestampDefByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TimestampDef key attribute of the instance generating the id.
	 */
	void deleteTimestampDefByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TimestampDef key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TimestampDef key attribute of the instance generating the id.
	 */
	void deleteTimestampDefByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Move the CFBamTimestampDefObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTimestampDefObj refreshed cache instance.
	 */
	ICFBamTimestampDefObj moveUpTimestampDef( ICFBamTimestampDefObj Obj );

	/**
	 *	Move the CFBamTimestampDefObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTimestampDefObj refreshed cache instance.
	 */
	ICFBamTimestampDefObj moveDownTimestampDef( ICFBamTimestampDefObj Obj );
}
