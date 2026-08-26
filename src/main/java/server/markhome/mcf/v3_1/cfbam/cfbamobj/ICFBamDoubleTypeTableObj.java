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

public interface ICFBamDoubleTypeTableObj
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
	 *	Instantiate a new DoubleType instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamDoubleTypeObj newInstance();

	/**
	 *	Instantiate a new DoubleType edition of the specified DoubleType instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamDoubleTypeEditObj newEditInstance( ICFBamDoubleTypeObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamDoubleTypeObj realiseDoubleType( ICFBamDoubleTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamDoubleTypeObj createDoubleType( ICFBamDoubleTypeObj Obj );

	/**
	 *	Read a DoubleType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DoubleType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDoubleTypeObj readDoubleType( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Read a DoubleType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DoubleType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDoubleTypeObj readDoubleType( $implCommaIJavaOptAtomType$ pkey,
		boolean forceRead );

	ICFBamDoubleTypeObj readCachedDoubleType( $implCommaIJavaOptAtomType$ pkey );

	public void reallyDeepDisposeDoubleType( ICFBamDoubleTypeObj obj );

	void deepDisposeDoubleType( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamDoubleTypeObj lockDoubleType( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Return a sorted list of all the DoubleType-derived instances in the database.
	 *
	 *	@return	List of ICFBamDoubleTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDoubleTypeObj> readAllDoubleType();

	/**
	 *	Return a sorted map of all the DoubleType-derived instances in the database.
	 *
	 *	@return	List of ICFBamDoubleTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDoubleTypeObj> readAllDoubleType( boolean forceRead );

	List<ICFBamDoubleTypeObj> readCachedAllDoubleType();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDoubleTypeObj readDoubleTypeByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDoubleTypeObj readDoubleTypeByIdIdx( ICFLibKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDoubleTypeObj readDoubleTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDoubleTypeObj readDoubleTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamDoubleTypeObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByScopeIdx( ICFLibKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamDoubleTypeObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamDoubleTypeObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByPrevIdx( ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamDoubleTypeObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByNextIdx( ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamDoubleTypeObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamDoubleTypeObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDoubleTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Get the map of CFBamDoubleTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDoubleTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDoubleTypeObj> readDoubleTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId,
		boolean forceRead );

	ICFBamDoubleTypeObj readCachedDoubleTypeByIdIdx( ICFLibKeyHash256 Id );

	ICFBamDoubleTypeObj readCachedDoubleTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	List<ICFBamDoubleTypeObj> readCachedDoubleTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	List<ICFBamDoubleTypeObj> readCachedDoubleTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	List<ICFBamDoubleTypeObj> readCachedDoubleTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	List<ICFBamDoubleTypeObj> readCachedDoubleTypeByNextIdx( ICFLibKeyHash256 NextId );

	List<ICFBamDoubleTypeObj> readCachedDoubleTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	List<ICFBamDoubleTypeObj> readCachedDoubleTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	List<ICFBamDoubleTypeObj> readCachedDoubleTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	void deepDisposeDoubleTypeByIdIdx( ICFLibKeyHash256 Id );

	void deepDisposeDoubleTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	void deepDisposeDoubleTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	void deepDisposeDoubleTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	void deepDisposeDoubleTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	void deepDisposeDoubleTypeByNextIdx( ICFLibKeyHash256 NextId );

	void deepDisposeDoubleTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	void deepDisposeDoubleTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	void deepDisposeDoubleTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Internal use only.
	 */
	ICFBamDoubleTypeObj updateDoubleType( ICFBamDoubleTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteDoubleType( ICFBamDoubleTypeObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DoubleType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaDefId	The DoubleType key attribute of the instance generating the id.
	 */
	void deleteDoubleTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Move the CFBamDoubleTypeObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDoubleTypeObj refreshed cache instance.
	 */
	ICFBamDoubleTypeObj moveUpDoubleType( ICFBamDoubleTypeObj Obj );

	/**
	 *	Move the CFBamDoubleTypeObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDoubleTypeObj refreshed cache instance.
	 */
	ICFBamDoubleTypeObj moveDownDoubleType( ICFBamDoubleTypeObj Obj );
}
