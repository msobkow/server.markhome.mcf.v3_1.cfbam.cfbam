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
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfint.cfintpub.*;
import server.markhome.mcf.v3_1.cfbam.cfbampub.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprot.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfint.cfintpubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbampubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprotobj.*;

public interface ICFBamTimestampTypeTableObj
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
	 *	Instantiate a new TimestampType instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamTimestampTypeObj newInstance();

	/**
	 *	Instantiate a new TimestampType edition of the specified TimestampType instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamTimestampTypeEditObj newEditInstance( ICFBamTimestampTypeObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamTimestampTypeObj realiseTimestampType( ICFBamTimestampTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamTimestampTypeObj createTimestampType( ICFBamTimestampTypeObj Obj );

	/**
	 *	Read a TimestampType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TimestampType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTimestampTypeObj readTimestampType( ICFLibKeyHash256 pkey );

	/**
	 *	Read a TimestampType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TimestampType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTimestampTypeObj readTimestampType( ICFLibKeyHash256 pkey,
		boolean forceRead );

	ICFBamTimestampTypeObj readCachedTimestampType( ICFLibKeyHash256 pkey );

	public void reallyDeepDisposeTimestampType( ICFBamTimestampTypeObj obj );

	void deepDisposeTimestampType( ICFLibKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamTimestampTypeObj lockTimestampType( ICFLibKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the TimestampType-derived instances in the database.
	 *
	 *	@return	List of ICFBamTimestampTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTimestampTypeObj> readAllTimestampType();

	/**
	 *	Return a sorted map of all the TimestampType-derived instances in the database.
	 *
	 *	@return	List of ICFBamTimestampTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTimestampTypeObj> readAllTimestampType( boolean forceRead );

	List<ICFBamTimestampTypeObj> readCachedAllTimestampType();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTimestampTypeObj readTimestampTypeByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTimestampTypeObj readTimestampTypeByIdIdx( ICFLibKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTimestampTypeObj readTimestampTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTimestampTypeObj readTimestampTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampTypeObj> readTimestampTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamTimestampTypeObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampTypeObj> readTimestampTypeByScopeIdx( ICFLibKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampTypeObj> readTimestampTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamTimestampTypeObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampTypeObj> readTimestampTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampTypeObj> readTimestampTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamTimestampTypeObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampTypeObj> readTimestampTypeByPrevIdx( ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampTypeObj> readTimestampTypeByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamTimestampTypeObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampTypeObj> readTimestampTypeByNextIdx( ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampTypeObj> readTimestampTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamTimestampTypeObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampTypeObj> readTimestampTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampTypeObj> readTimestampTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamTimestampTypeObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampTypeObj> readTimestampTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamTimestampTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampTypeObj> readTimestampTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Get the map of CFBamTimestampTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTimestampTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTimestampTypeObj> readTimestampTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId,
		boolean forceRead );

	ICFBamTimestampTypeObj readCachedTimestampTypeByIdIdx( ICFLibKeyHash256 Id );

	ICFBamTimestampTypeObj readCachedTimestampTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	List<ICFBamTimestampTypeObj> readCachedTimestampTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	List<ICFBamTimestampTypeObj> readCachedTimestampTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	List<ICFBamTimestampTypeObj> readCachedTimestampTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	List<ICFBamTimestampTypeObj> readCachedTimestampTypeByNextIdx( ICFLibKeyHash256 NextId );

	List<ICFBamTimestampTypeObj> readCachedTimestampTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	List<ICFBamTimestampTypeObj> readCachedTimestampTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	List<ICFBamTimestampTypeObj> readCachedTimestampTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	void deepDisposeTimestampTypeByIdIdx( ICFLibKeyHash256 Id );

	void deepDisposeTimestampTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	void deepDisposeTimestampTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	void deepDisposeTimestampTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	void deepDisposeTimestampTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	void deepDisposeTimestampTypeByNextIdx( ICFLibKeyHash256 NextId );

	void deepDisposeTimestampTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	void deepDisposeTimestampTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	void deepDisposeTimestampTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Internal use only.
	 */
	ICFBamTimestampTypeObj updateTimestampType( ICFBamTimestampTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteTimestampType( ICFBamTimestampTypeObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The TimestampType key attribute of the instance generating the id.
	 */
	void deleteTimestampTypeByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TimestampType key attribute of the instance generating the id.
	 */
	void deleteTimestampTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TimestampType key attribute of the instance generating the id.
	 */
	void deleteTimestampTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The TimestampType key attribute of the instance generating the id.
	 */
	void deleteTimestampTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The TimestampType key attribute of the instance generating the id.
	 */
	void deleteTimestampTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The TimestampType key attribute of the instance generating the id.
	 */
	void deleteTimestampTypeByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TimestampType key attribute of the instance generating the id.
	 */
	void deleteTimestampTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TimestampType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TimestampType key attribute of the instance generating the id.
	 */
	void deleteTimestampTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaDefId	The TimestampType key attribute of the instance generating the id.
	 */
	void deleteTimestampTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Move the CFBamTimestampTypeObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTimestampTypeObj refreshed cache instance.
	 */
	ICFBamTimestampTypeObj moveUpTimestampType( ICFBamTimestampTypeObj Obj );

	/**
	 *	Move the CFBamTimestampTypeObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTimestampTypeObj refreshed cache instance.
	 */
	ICFBamTimestampTypeObj moveDownTimestampType( ICFBamTimestampTypeObj Obj );
}
