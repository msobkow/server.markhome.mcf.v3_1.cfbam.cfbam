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

public interface ICFBamTZDateTypeTableObj
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
	 *	Instantiate a new TZDateType instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamTZDateTypeObj newInstance();

	/**
	 *	Instantiate a new TZDateType edition of the specified TZDateType instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamTZDateTypeEditObj newEditInstance( ICFBamTZDateTypeObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamTZDateTypeObj realiseTZDateType( ICFBamTZDateTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamTZDateTypeObj createTZDateType( ICFBamTZDateTypeObj Obj );

	/**
	 *	Read a TZDateType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TZDateType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTZDateTypeObj readTZDateType( ICFLibKeyHash256 pkey );

	/**
	 *	Read a TZDateType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TZDateType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTZDateTypeObj readTZDateType( ICFLibKeyHash256 pkey,
		boolean forceRead );

	ICFBamTZDateTypeObj readCachedTZDateType( ICFLibKeyHash256 pkey );

	public void reallyDeepDisposeTZDateType( ICFBamTZDateTypeObj obj );

	void deepDisposeTZDateType( ICFLibKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamTZDateTypeObj lockTZDateType( ICFLibKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the TZDateType-derived instances in the database.
	 *
	 *	@return	List of ICFBamTZDateTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTZDateTypeObj> readAllTZDateType();

	/**
	 *	Return a sorted map of all the TZDateType-derived instances in the database.
	 *
	 *	@return	List of ICFBamTZDateTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTZDateTypeObj> readAllTZDateType( boolean forceRead );

	List<ICFBamTZDateTypeObj> readCachedAllTZDateType();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTZDateTypeObj readTZDateTypeByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTZDateTypeObj readTZDateTypeByIdIdx( ICFLibKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTZDateTypeObj readTZDateTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTZDateTypeObj readTZDateTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateTypeObj> readTZDateTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamTZDateTypeObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateTypeObj> readTZDateTypeByScopeIdx( ICFLibKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateTypeObj> readTZDateTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamTZDateTypeObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateTypeObj> readTZDateTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateTypeObj> readTZDateTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamTZDateTypeObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateTypeObj> readTZDateTypeByPrevIdx( ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateTypeObj> readTZDateTypeByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamTZDateTypeObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateTypeObj> readTZDateTypeByNextIdx( ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateTypeObj> readTZDateTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamTZDateTypeObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateTypeObj> readTZDateTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateTypeObj> readTZDateTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamTZDateTypeObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateTypeObj> readTZDateTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamTZDateTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateTypeObj> readTZDateTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Get the map of CFBamTZDateTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTZDateTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTZDateTypeObj> readTZDateTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId,
		boolean forceRead );

	ICFBamTZDateTypeObj readCachedTZDateTypeByIdIdx( ICFLibKeyHash256 Id );

	ICFBamTZDateTypeObj readCachedTZDateTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	List<ICFBamTZDateTypeObj> readCachedTZDateTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	List<ICFBamTZDateTypeObj> readCachedTZDateTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	List<ICFBamTZDateTypeObj> readCachedTZDateTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	List<ICFBamTZDateTypeObj> readCachedTZDateTypeByNextIdx( ICFLibKeyHash256 NextId );

	List<ICFBamTZDateTypeObj> readCachedTZDateTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	List<ICFBamTZDateTypeObj> readCachedTZDateTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	List<ICFBamTZDateTypeObj> readCachedTZDateTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	void deepDisposeTZDateTypeByIdIdx( ICFLibKeyHash256 Id );

	void deepDisposeTZDateTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	void deepDisposeTZDateTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	void deepDisposeTZDateTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	void deepDisposeTZDateTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	void deepDisposeTZDateTypeByNextIdx( ICFLibKeyHash256 NextId );

	void deepDisposeTZDateTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	void deepDisposeTZDateTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	void deepDisposeTZDateTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Internal use only.
	 */
	ICFBamTZDateTypeObj updateTZDateType( ICFBamTZDateTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteTZDateType( ICFBamTZDateTypeObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The TZDateType key attribute of the instance generating the id.
	 */
	void deleteTZDateTypeByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TZDateType key attribute of the instance generating the id.
	 */
	void deleteTZDateTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TZDateType key attribute of the instance generating the id.
	 */
	void deleteTZDateTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The TZDateType key attribute of the instance generating the id.
	 */
	void deleteTZDateTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The TZDateType key attribute of the instance generating the id.
	 */
	void deleteTZDateTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The TZDateType key attribute of the instance generating the id.
	 */
	void deleteTZDateTypeByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TZDateType key attribute of the instance generating the id.
	 */
	void deleteTZDateTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TZDateType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TZDateType key attribute of the instance generating the id.
	 */
	void deleteTZDateTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaDefId	The TZDateType key attribute of the instance generating the id.
	 */
	void deleteTZDateTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Move the CFBamTZDateTypeObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTZDateTypeObj refreshed cache instance.
	 */
	ICFBamTZDateTypeObj moveUpTZDateType( ICFBamTZDateTypeObj Obj );

	/**
	 *	Move the CFBamTZDateTypeObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTZDateTypeObj refreshed cache instance.
	 */
	ICFBamTZDateTypeObj moveDownTZDateType( ICFBamTZDateTypeObj Obj );
}
