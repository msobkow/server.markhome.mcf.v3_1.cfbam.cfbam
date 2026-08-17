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

public interface ICFBamUuidGenTableObj
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
	 *	Instantiate a new UuidGen instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamUuidGenObj newInstance();

	/**
	 *	Instantiate a new UuidGen edition of the specified UuidGen instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamUuidGenEditObj newEditInstance( ICFBamUuidGenObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamUuidGenObj realiseUuidGen( ICFBamUuidGenObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamUuidGenObj createUuidGen( ICFBamUuidGenObj Obj );

	/**
	 *	Read a UuidGen-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The UuidGen-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamUuidGenObj readUuidGen( ICFLibKeyHash256 pkey );

	/**
	 *	Read a UuidGen-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The UuidGen-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamUuidGenObj readUuidGen( ICFLibKeyHash256 pkey,
		boolean forceRead );

	ICFBamUuidGenObj readCachedUuidGen( ICFLibKeyHash256 pkey );

	public void reallyDeepDisposeUuidGen( ICFBamUuidGenObj obj );

	void deepDisposeUuidGen( ICFLibKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamUuidGenObj lockUuidGen( ICFLibKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the UuidGen-derived instances in the database.
	 *
	 *	@return	List of ICFBamUuidGenObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamUuidGenObj> readAllUuidGen();

	/**
	 *	Return a sorted map of all the UuidGen-derived instances in the database.
	 *
	 *	@return	List of ICFBamUuidGenObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamUuidGenObj> readAllUuidGen( boolean forceRead );

	List<ICFBamUuidGenObj> readCachedAllUuidGen();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamUuidGenObj readUuidGenByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamUuidGenObj readUuidGenByIdIdx( ICFLibKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@param	Name	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamUuidGenObj readUuidGenByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@param	Name	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamUuidGenObj readUuidGenByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidGenObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidGenObj> readUuidGenByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamUuidGenObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidGenObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidGenObj> readUuidGenByScopeIdx( ICFLibKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidGenObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidGenObj> readUuidGenByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamUuidGenObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidGenObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidGenObj> readUuidGenByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidGenObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidGenObj> readUuidGenByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamUuidGenObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidGenObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidGenObj> readUuidGenByPrevIdx( ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidGenObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidGenObj> readUuidGenByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamUuidGenObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidGenObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidGenObj> readUuidGenByNextIdx( ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidGenObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidGenObj> readUuidGenByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamUuidGenObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidGenObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidGenObj> readUuidGenByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidGenObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidGenObj> readUuidGenByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamUuidGenObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidGenObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidGenObj> readUuidGenByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamUuidTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidGenObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidGenObj> readUuidGenBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Get the map of CFBamUuidGenObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamUuidGenObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamUuidGenObj> readUuidGenBySchemaIdx( ICFLibKeyHash256 SchemaDefId,
		boolean forceRead );

	ICFBamUuidGenObj readCachedUuidGenByIdIdx( ICFLibKeyHash256 Id );

	ICFBamUuidGenObj readCachedUuidGenByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	List<ICFBamUuidGenObj> readCachedUuidGenByScopeIdx( ICFLibKeyHash256 ScopeId );

	List<ICFBamUuidGenObj> readCachedUuidGenByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	List<ICFBamUuidGenObj> readCachedUuidGenByPrevIdx( ICFLibKeyHash256 PrevId );

	List<ICFBamUuidGenObj> readCachedUuidGenByNextIdx( ICFLibKeyHash256 NextId );

	List<ICFBamUuidGenObj> readCachedUuidGenByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	List<ICFBamUuidGenObj> readCachedUuidGenByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	List<ICFBamUuidGenObj> readCachedUuidGenBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	void deepDisposeUuidGenByIdIdx( ICFLibKeyHash256 Id );

	void deepDisposeUuidGenByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	void deepDisposeUuidGenByScopeIdx( ICFLibKeyHash256 ScopeId );

	void deepDisposeUuidGenByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	void deepDisposeUuidGenByPrevIdx( ICFLibKeyHash256 PrevId );

	void deepDisposeUuidGenByNextIdx( ICFLibKeyHash256 NextId );

	void deepDisposeUuidGenByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	void deepDisposeUuidGenByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	void deepDisposeUuidGenBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Internal use only.
	 */
	ICFBamUuidGenObj updateUuidGen( ICFBamUuidGenObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteUuidGen( ICFBamUuidGenObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The UuidGen key attribute of the instance generating the id.
	 */
	void deleteUuidGenByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@param	Name	The UuidGen key attribute of the instance generating the id.
	 */
	void deleteUuidGenByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UuidGen key attribute of the instance generating the id.
	 */
	void deleteUuidGenByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The UuidGen key attribute of the instance generating the id.
	 */
	void deleteUuidGenByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The UuidGen key attribute of the instance generating the id.
	 */
	void deleteUuidGenByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The UuidGen key attribute of the instance generating the id.
	 */
	void deleteUuidGenByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The UuidGen key attribute of the instance generating the id.
	 */
	void deleteUuidGenByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The UuidGen key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The UuidGen key attribute of the instance generating the id.
	 */
	void deleteUuidGenByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaDefId	The UuidGen key attribute of the instance generating the id.
	 */
	void deleteUuidGenBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Move the CFBamUuidGenObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamUuidGenObj refreshed cache instance.
	 */
	ICFBamUuidGenObj moveUpUuidGen( ICFBamUuidGenObj Obj );

	/**
	 *	Move the CFBamUuidGenObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamUuidGenObj refreshed cache instance.
	 */
	ICFBamUuidGenObj moveDownUuidGen( ICFBamUuidGenObj Obj );
}
