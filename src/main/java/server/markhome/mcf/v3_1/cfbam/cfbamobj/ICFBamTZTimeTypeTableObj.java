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

public interface ICFBamTZTimeTypeTableObj
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
	 *	Instantiate a new TZTimeType instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamTZTimeTypeObj newInstance();

	/**
	 *	Instantiate a new TZTimeType edition of the specified TZTimeType instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamTZTimeTypeEditObj newEditInstance( ICFBamTZTimeTypeObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamTZTimeTypeObj realiseTZTimeType( ICFBamTZTimeTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamTZTimeTypeObj createTZTimeType( ICFBamTZTimeTypeObj Obj );

	/**
	 *	Read a TZTimeType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TZTimeType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTZTimeTypeObj readTZTimeType( ICFLibKeyHash256 pkey );

	/**
	 *	Read a TZTimeType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TZTimeType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTZTimeTypeObj readTZTimeType( ICFLibKeyHash256 pkey,
		boolean forceRead );

	ICFBamTZTimeTypeObj readCachedTZTimeType( ICFLibKeyHash256 pkey );

	public void reallyDeepDisposeTZTimeType( ICFBamTZTimeTypeObj obj );

	void deepDisposeTZTimeType( ICFLibKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamTZTimeTypeObj lockTZTimeType( ICFLibKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the TZTimeType-derived instances in the database.
	 *
	 *	@return	List of ICFBamTZTimeTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTZTimeTypeObj> readAllTZTimeType();

	/**
	 *	Return a sorted map of all the TZTimeType-derived instances in the database.
	 *
	 *	@return	List of ICFBamTZTimeTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTZTimeTypeObj> readAllTZTimeType( boolean forceRead );

	List<ICFBamTZTimeTypeObj> readCachedAllTZTimeType();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTZTimeTypeObj readTZTimeTypeByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTZTimeTypeObj readTZTimeTypeByIdIdx( ICFLibKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTZTimeTypeObj readTZTimeTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTZTimeTypeObj readTZTimeTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZTimeTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZTimeTypeObj> readTZTimeTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamTZTimeTypeObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZTimeTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZTimeTypeObj> readTZTimeTypeByScopeIdx( ICFLibKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZTimeTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZTimeTypeObj> readTZTimeTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamTZTimeTypeObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZTimeTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZTimeTypeObj> readTZTimeTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZTimeTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZTimeTypeObj> readTZTimeTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamTZTimeTypeObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZTimeTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZTimeTypeObj> readTZTimeTypeByPrevIdx( ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZTimeTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZTimeTypeObj> readTZTimeTypeByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamTZTimeTypeObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZTimeTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZTimeTypeObj> readTZTimeTypeByNextIdx( ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZTimeTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZTimeTypeObj> readTZTimeTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamTZTimeTypeObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZTimeTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZTimeTypeObj> readTZTimeTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZTimeTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZTimeTypeObj> readTZTimeTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamTZTimeTypeObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZTimeTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZTimeTypeObj> readTZTimeTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamTZTimeTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZTimeTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZTimeTypeObj> readTZTimeTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Get the map of CFBamTZTimeTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZTimeTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZTimeTypeObj> readTZTimeTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId,
		boolean forceRead );

	ICFBamTZTimeTypeObj readCachedTZTimeTypeByIdIdx( ICFLibKeyHash256 Id );

	ICFBamTZTimeTypeObj readCachedTZTimeTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	List<ICFBamTZTimeTypeObj> readCachedTZTimeTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	List<ICFBamTZTimeTypeObj> readCachedTZTimeTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	List<ICFBamTZTimeTypeObj> readCachedTZTimeTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	List<ICFBamTZTimeTypeObj> readCachedTZTimeTypeByNextIdx( ICFLibKeyHash256 NextId );

	List<ICFBamTZTimeTypeObj> readCachedTZTimeTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	List<ICFBamTZTimeTypeObj> readCachedTZTimeTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	List<ICFBamTZTimeTypeObj> readCachedTZTimeTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	void deepDisposeTZTimeTypeByIdIdx( ICFLibKeyHash256 Id );

	void deepDisposeTZTimeTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	void deepDisposeTZTimeTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	void deepDisposeTZTimeTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	void deepDisposeTZTimeTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	void deepDisposeTZTimeTypeByNextIdx( ICFLibKeyHash256 NextId );

	void deepDisposeTZTimeTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	void deepDisposeTZTimeTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	void deepDisposeTZTimeTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Internal use only.
	 */
	ICFBamTZTimeTypeObj updateTZTimeType( ICFBamTZTimeTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteTZTimeType( ICFBamTZTimeTypeObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The TZTimeType key attribute of the instance generating the id.
	 */
	void deleteTZTimeTypeByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TZTimeType key attribute of the instance generating the id.
	 */
	void deleteTZTimeTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TZTimeType key attribute of the instance generating the id.
	 */
	void deleteTZTimeTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The TZTimeType key attribute of the instance generating the id.
	 */
	void deleteTZTimeTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The TZTimeType key attribute of the instance generating the id.
	 */
	void deleteTZTimeTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The TZTimeType key attribute of the instance generating the id.
	 */
	void deleteTZTimeTypeByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TZTimeType key attribute of the instance generating the id.
	 */
	void deleteTZTimeTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TZTimeType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TZTimeType key attribute of the instance generating the id.
	 */
	void deleteTZTimeTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaDefId	The TZTimeType key attribute of the instance generating the id.
	 */
	void deleteTZTimeTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Move the CFBamTZTimeTypeObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTZTimeTypeObj refreshed cache instance.
	 */
	ICFBamTZTimeTypeObj moveUpTZTimeType( ICFBamTZTimeTypeObj Obj );

	/**
	 *	Move the CFBamTZTimeTypeObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTZTimeTypeObj refreshed cache instance.
	 */
	ICFBamTZTimeTypeObj moveDownTZTimeType( ICFBamTZTimeTypeObj Obj );
}
