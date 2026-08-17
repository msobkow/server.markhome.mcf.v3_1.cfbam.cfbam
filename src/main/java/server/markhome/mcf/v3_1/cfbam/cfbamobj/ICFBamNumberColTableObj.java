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

public interface ICFBamNumberColTableObj
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
	 *	Instantiate a new NumberCol instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamNumberColObj newInstance();

	/**
	 *	Instantiate a new NumberCol edition of the specified NumberCol instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamNumberColEditObj newEditInstance( ICFBamNumberColObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamNumberColObj realiseNumberCol( ICFBamNumberColObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamNumberColObj createNumberCol( ICFBamNumberColObj Obj );

	/**
	 *	Read a NumberCol-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The NumberCol-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamNumberColObj readNumberCol( ICFLibKeyHash256 pkey );

	/**
	 *	Read a NumberCol-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The NumberCol-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamNumberColObj readNumberCol( ICFLibKeyHash256 pkey,
		boolean forceRead );

	ICFBamNumberColObj readCachedNumberCol( ICFLibKeyHash256 pkey );

	public void reallyDeepDisposeNumberCol( ICFBamNumberColObj obj );

	void deepDisposeNumberCol( ICFLibKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamNumberColObj lockNumberCol( ICFLibKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the NumberCol-derived instances in the database.
	 *
	 *	@return	List of ICFBamNumberColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamNumberColObj> readAllNumberCol();

	/**
	 *	Return a sorted map of all the NumberCol-derived instances in the database.
	 *
	 *	@return	List of ICFBamNumberColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamNumberColObj> readAllNumberCol( boolean forceRead );

	List<ICFBamNumberColObj> readCachedAllNumberCol();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamNumberColObj readNumberColByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamNumberColObj readNumberColByIdIdx( ICFLibKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamNumberColObj readNumberColByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamNumberColObj readNumberColByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNumberColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNumberColObj> readNumberColByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamNumberColObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNumberColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNumberColObj> readNumberColByScopeIdx( ICFLibKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNumberColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNumberColObj> readNumberColByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamNumberColObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNumberColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNumberColObj> readNumberColByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNumberColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNumberColObj> readNumberColByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamNumberColObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNumberColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNumberColObj> readNumberColByPrevIdx( ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNumberColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNumberColObj> readNumberColByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamNumberColObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNumberColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNumberColObj> readNumberColByNextIdx( ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNumberColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNumberColObj> readNumberColByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamNumberColObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNumberColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNumberColObj> readNumberColByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNumberColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNumberColObj> readNumberColByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamNumberColObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNumberColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNumberColObj> readNumberColByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamNumberColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNumberColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNumberColObj> readNumberColByTableIdx( ICFLibKeyHash256 TableId );

	/**
	 *	Get the map of CFBamNumberColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNumberColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNumberColObj> readNumberColByTableIdx( ICFLibKeyHash256 TableId,
		boolean forceRead );

	ICFBamNumberColObj readCachedNumberColByIdIdx( ICFLibKeyHash256 Id );

	ICFBamNumberColObj readCachedNumberColByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	List<ICFBamNumberColObj> readCachedNumberColByScopeIdx( ICFLibKeyHash256 ScopeId );

	List<ICFBamNumberColObj> readCachedNumberColByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	List<ICFBamNumberColObj> readCachedNumberColByPrevIdx( ICFLibKeyHash256 PrevId );

	List<ICFBamNumberColObj> readCachedNumberColByNextIdx( ICFLibKeyHash256 NextId );

	List<ICFBamNumberColObj> readCachedNumberColByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	List<ICFBamNumberColObj> readCachedNumberColByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	List<ICFBamNumberColObj> readCachedNumberColByTableIdx( ICFLibKeyHash256 TableId );

	void deepDisposeNumberColByIdIdx( ICFLibKeyHash256 Id );

	void deepDisposeNumberColByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	void deepDisposeNumberColByScopeIdx( ICFLibKeyHash256 ScopeId );

	void deepDisposeNumberColByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	void deepDisposeNumberColByPrevIdx( ICFLibKeyHash256 PrevId );

	void deepDisposeNumberColByNextIdx( ICFLibKeyHash256 NextId );

	void deepDisposeNumberColByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	void deepDisposeNumberColByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	void deepDisposeNumberColByTableIdx( ICFLibKeyHash256 TableId );

	/**
	 *	Internal use only.
	 */
	ICFBamNumberColObj updateNumberCol( ICFBamNumberColObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteNumberCol( ICFBamNumberColObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The NumberCol key attribute of the instance generating the id.
	 */
	void deleteNumberColByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The NumberCol key attribute of the instance generating the id.
	 */
	void deleteNumberColByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The NumberCol key attribute of the instance generating the id.
	 */
	void deleteNumberColByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The NumberCol key attribute of the instance generating the id.
	 */
	void deleteNumberColByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The NumberCol key attribute of the instance generating the id.
	 */
	void deleteNumberColByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The NumberCol key attribute of the instance generating the id.
	 */
	void deleteNumberColByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The NumberCol key attribute of the instance generating the id.
	 */
	void deleteNumberColByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The NumberCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The NumberCol key attribute of the instance generating the id.
	 */
	void deleteNumberColByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The NumberCol key attribute of the instance generating the id.
	 */
	void deleteNumberColByTableIdx( ICFLibKeyHash256 TableId );

	/**
	 *	Move the CFBamNumberColObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamNumberColObj refreshed cache instance.
	 */
	ICFBamNumberColObj moveUpNumberCol( ICFBamNumberColObj Obj );

	/**
	 *	Move the CFBamNumberColObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamNumberColObj refreshed cache instance.
	 */
	ICFBamNumberColObj moveDownNumberCol( ICFBamNumberColObj Obj );
}
