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

public interface ICFBamNmTokenTypeTableObj
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
	 *	Instantiate a new NmTokenType instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamNmTokenTypeObj newInstance();

	/**
	 *	Instantiate a new NmTokenType edition of the specified NmTokenType instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamNmTokenTypeEditObj newEditInstance( ICFBamNmTokenTypeObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamNmTokenTypeObj realiseNmTokenType( ICFBamNmTokenTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamNmTokenTypeObj createNmTokenType( ICFBamNmTokenTypeObj Obj );

	/**
	 *	Read a NmTokenType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The NmTokenType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamNmTokenTypeObj readNmTokenType( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Read a NmTokenType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The NmTokenType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamNmTokenTypeObj readNmTokenType( $implCommaIJavaOptAtomType$ pkey,
		boolean forceRead );

	ICFBamNmTokenTypeObj readCachedNmTokenType( $implCommaIJavaOptAtomType$ pkey );

	public void reallyDeepDisposeNmTokenType( ICFBamNmTokenTypeObj obj );

	void deepDisposeNmTokenType( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamNmTokenTypeObj lockNmTokenType( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Return a sorted list of all the NmTokenType-derived instances in the database.
	 *
	 *	@return	List of ICFBamNmTokenTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamNmTokenTypeObj> readAllNmTokenType();

	/**
	 *	Return a sorted map of all the NmTokenType-derived instances in the database.
	 *
	 *	@return	List of ICFBamNmTokenTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamNmTokenTypeObj> readAllNmTokenType( boolean forceRead );

	List<ICFBamNmTokenTypeObj> readCachedAllNmTokenType();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamNmTokenTypeObj readNmTokenTypeByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamNmTokenTypeObj readNmTokenTypeByIdIdx( ICFLibKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamNmTokenTypeObj readNmTokenTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamNmTokenTypeObj readNmTokenTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNmTokenTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNmTokenTypeObj> readNmTokenTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamNmTokenTypeObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNmTokenTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNmTokenTypeObj> readNmTokenTypeByScopeIdx( ICFLibKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNmTokenTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNmTokenTypeObj> readNmTokenTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamNmTokenTypeObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNmTokenTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNmTokenTypeObj> readNmTokenTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNmTokenTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNmTokenTypeObj> readNmTokenTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamNmTokenTypeObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNmTokenTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNmTokenTypeObj> readNmTokenTypeByPrevIdx( ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNmTokenTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNmTokenTypeObj> readNmTokenTypeByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamNmTokenTypeObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNmTokenTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNmTokenTypeObj> readNmTokenTypeByNextIdx( ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNmTokenTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNmTokenTypeObj> readNmTokenTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamNmTokenTypeObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNmTokenTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNmTokenTypeObj> readNmTokenTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNmTokenTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNmTokenTypeObj> readNmTokenTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamNmTokenTypeObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNmTokenTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNmTokenTypeObj> readNmTokenTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamNmTokenTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNmTokenTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNmTokenTypeObj> readNmTokenTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Get the map of CFBamNmTokenTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamNmTokenTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamNmTokenTypeObj> readNmTokenTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId,
		boolean forceRead );

	ICFBamNmTokenTypeObj readCachedNmTokenTypeByIdIdx( ICFLibKeyHash256 Id );

	ICFBamNmTokenTypeObj readCachedNmTokenTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	List<ICFBamNmTokenTypeObj> readCachedNmTokenTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	List<ICFBamNmTokenTypeObj> readCachedNmTokenTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	List<ICFBamNmTokenTypeObj> readCachedNmTokenTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	List<ICFBamNmTokenTypeObj> readCachedNmTokenTypeByNextIdx( ICFLibKeyHash256 NextId );

	List<ICFBamNmTokenTypeObj> readCachedNmTokenTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	List<ICFBamNmTokenTypeObj> readCachedNmTokenTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	List<ICFBamNmTokenTypeObj> readCachedNmTokenTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	void deepDisposeNmTokenTypeByIdIdx( ICFLibKeyHash256 Id );

	void deepDisposeNmTokenTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	void deepDisposeNmTokenTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	void deepDisposeNmTokenTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	void deepDisposeNmTokenTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	void deepDisposeNmTokenTypeByNextIdx( ICFLibKeyHash256 NextId );

	void deepDisposeNmTokenTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	void deepDisposeNmTokenTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	void deepDisposeNmTokenTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Internal use only.
	 */
	ICFBamNmTokenTypeObj updateNmTokenType( ICFBamNmTokenTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteNmTokenType( ICFBamNmTokenTypeObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The NmTokenType key attribute of the instance generating the id.
	 */
	void deleteNmTokenTypeByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The NmTokenType key attribute of the instance generating the id.
	 */
	void deleteNmTokenTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The NmTokenType key attribute of the instance generating the id.
	 */
	void deleteNmTokenTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The NmTokenType key attribute of the instance generating the id.
	 */
	void deleteNmTokenTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The NmTokenType key attribute of the instance generating the id.
	 */
	void deleteNmTokenTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The NmTokenType key attribute of the instance generating the id.
	 */
	void deleteNmTokenTypeByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The NmTokenType key attribute of the instance generating the id.
	 */
	void deleteNmTokenTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The NmTokenType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The NmTokenType key attribute of the instance generating the id.
	 */
	void deleteNmTokenTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaDefId	The NmTokenType key attribute of the instance generating the id.
	 */
	void deleteNmTokenTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Move the CFBamNmTokenTypeObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamNmTokenTypeObj refreshed cache instance.
	 */
	ICFBamNmTokenTypeObj moveUpNmTokenType( ICFBamNmTokenTypeObj Obj );

	/**
	 *	Move the CFBamNmTokenTypeObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamNmTokenTypeObj refreshed cache instance.
	 */
	ICFBamNmTokenTypeObj moveDownNmTokenType( ICFBamNmTokenTypeObj Obj );
}
