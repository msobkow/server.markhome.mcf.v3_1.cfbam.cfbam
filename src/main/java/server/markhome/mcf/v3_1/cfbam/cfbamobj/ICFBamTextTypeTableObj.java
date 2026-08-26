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

public interface ICFBamTextTypeTableObj
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
	 *	Instantiate a new TextType instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamTextTypeObj newInstance();

	/**
	 *	Instantiate a new TextType edition of the specified TextType instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamTextTypeEditObj newEditInstance( ICFBamTextTypeObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamTextTypeObj realiseTextType( ICFBamTextTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamTextTypeObj createTextType( ICFBamTextTypeObj Obj );

	/**
	 *	Read a TextType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TextType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTextTypeObj readTextType( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Read a TextType-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TextType-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTextTypeObj readTextType( $implCommaIJavaOptAtomType$ pkey,
		boolean forceRead );

	ICFBamTextTypeObj readCachedTextType( $implCommaIJavaOptAtomType$ pkey );

	public void reallyDeepDisposeTextType( ICFBamTextTypeObj obj );

	void deepDisposeTextType( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamTextTypeObj lockTextType( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Return a sorted list of all the TextType-derived instances in the database.
	 *
	 *	@return	List of ICFBamTextTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTextTypeObj> readAllTextType();

	/**
	 *	Return a sorted map of all the TextType-derived instances in the database.
	 *
	 *	@return	List of ICFBamTextTypeObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTextTypeObj> readAllTextType( boolean forceRead );

	List<ICFBamTextTypeObj> readCachedAllTextType();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTextTypeObj readTextTypeByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTextTypeObj readTextTypeByIdIdx( ICFLibKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTextTypeObj readTextTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTextTypeObj readTextTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamTextTypeObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByScopeIdx( ICFLibKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamTextTypeObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamTextTypeObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByPrevIdx( ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamTextTypeObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByNextIdx( ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamTextTypeObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamTextTypeObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamTextTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Get the map of CFBamTextTypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The TextType key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTextTypeObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTextTypeObj> readTextTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId,
		boolean forceRead );

	ICFBamTextTypeObj readCachedTextTypeByIdIdx( ICFLibKeyHash256 Id );

	ICFBamTextTypeObj readCachedTextTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	List<ICFBamTextTypeObj> readCachedTextTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	List<ICFBamTextTypeObj> readCachedTextTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	List<ICFBamTextTypeObj> readCachedTextTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	List<ICFBamTextTypeObj> readCachedTextTypeByNextIdx( ICFLibKeyHash256 NextId );

	List<ICFBamTextTypeObj> readCachedTextTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	List<ICFBamTextTypeObj> readCachedTextTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	List<ICFBamTextTypeObj> readCachedTextTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	void deepDisposeTextTypeByIdIdx( ICFLibKeyHash256 Id );

	void deepDisposeTextTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	void deepDisposeTextTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	void deepDisposeTextTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	void deepDisposeTextTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	void deepDisposeTextTypeByNextIdx( ICFLibKeyHash256 NextId );

	void deepDisposeTextTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	void deepDisposeTextTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	void deepDisposeTextTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Internal use only.
	 */
	ICFBamTextTypeObj updateTextType( ICFBamTextTypeObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteTextType( ICFBamTextTypeObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TextType key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaDefId	The TextType key attribute of the instance generating the id.
	 */
	void deleteTextTypeBySchemaIdx( ICFLibKeyHash256 SchemaDefId );

	/**
	 *	Move the CFBamTextTypeObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTextTypeObj refreshed cache instance.
	 */
	ICFBamTextTypeObj moveUpTextType( ICFBamTextTypeObj Obj );

	/**
	 *	Move the CFBamTextTypeObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamTextTypeObj refreshed cache instance.
	 */
	ICFBamTextTypeObj moveDownTextType( ICFBamTextTypeObj Obj );
}
