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

public interface ICFBamId32GenTableObj
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
	 *	Instantiate a new Id32Gen instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamId32GenObj newInstance();

	/**
	 *	Instantiate a new Id32Gen edition of the specified Id32Gen instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamId32GenEditObj newEditInstance( ICFBamId32GenObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamId32GenObj realiseId32Gen( ICFBamId32GenObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamId32GenObj createId32Gen( ICFBamId32GenObj Obj );

	/**
	 *	Read a Id32Gen-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Id32Gen-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamId32GenObj readId32Gen( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a Id32Gen-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Id32Gen-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamId32GenObj readId32Gen( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamId32GenObj readCachedId32Gen( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeId32Gen( ICFBamId32GenObj obj );

	void deepDisposeId32Gen( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamId32GenObj lockId32Gen( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the Id32Gen-derived instances in the database.
	 *
	 *	@return	List of ICFBamId32GenObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamId32GenObj> readAllId32Gen();

	/**
	 *	Return a sorted map of all the Id32Gen-derived instances in the database.
	 *
	 *	@return	List of ICFBamId32GenObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamId32GenObj> readAllId32Gen( boolean forceRead );

	List<ICFBamId32GenObj> readCachedAllId32Gen();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamId32GenObj readId32GenByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamId32GenObj readId32GenByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamId32GenObj readId32GenByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamId32GenObj readId32GenByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamId32GenObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamId32GenObj> readId32GenByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamId32GenObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamId32GenObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamId32GenObj> readId32GenByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamId32GenObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamId32GenObj> readId32GenByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamId32GenObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamId32GenObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamId32GenObj> readId32GenByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamId32GenObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamId32GenObj> readId32GenByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamId32GenObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamId32GenObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamId32GenObj> readId32GenByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamId32GenObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamId32GenObj> readId32GenByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamId32GenObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamId32GenObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamId32GenObj> readId32GenByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamId32GenObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamId32GenObj> readId32GenByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamId32GenObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamId32GenObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamId32GenObj> readId32GenByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamId32GenObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamId32GenObj> readId32GenByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamId32GenObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamId32GenObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamId32GenObj> readId32GenByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamInt32TypeObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamId32GenObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamId32GenObj> readId32GenBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Get the map of CFBamId32GenObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaDefId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamId32GenObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamId32GenObj> readId32GenBySchemaIdx( CFLibDbKeyHash256 SchemaDefId,
		boolean forceRead );

	ICFBamId32GenObj readCachedId32GenByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamId32GenObj readCachedId32GenByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamId32GenObj> readCachedId32GenByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamId32GenObj> readCachedId32GenByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamId32GenObj> readCachedId32GenByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamId32GenObj> readCachedId32GenByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamId32GenObj> readCachedId32GenByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamId32GenObj> readCachedId32GenByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	List<ICFBamId32GenObj> readCachedId32GenBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	void deepDisposeId32GenByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeId32GenByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeId32GenByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeId32GenByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeId32GenByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeId32GenByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeId32GenByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeId32GenByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeId32GenBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Internal use only.
	 */
	ICFBamId32GenObj updateId32Gen( ICFBamId32GenObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteId32Gen( ICFBamId32GenObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The Id32Gen key attribute of the instance generating the id.
	 */
	void deleteId32GenByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Id32Gen key attribute of the instance generating the id.
	 */
	void deleteId32GenByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Id32Gen key attribute of the instance generating the id.
	 */
	void deleteId32GenByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The Id32Gen key attribute of the instance generating the id.
	 */
	void deleteId32GenByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The Id32Gen key attribute of the instance generating the id.
	 */
	void deleteId32GenByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The Id32Gen key attribute of the instance generating the id.
	 */
	void deleteId32GenByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Id32Gen key attribute of the instance generating the id.
	 */
	void deleteId32GenByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Id32Gen key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Id32Gen key attribute of the instance generating the id.
	 */
	void deleteId32GenByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaDefId	The Id32Gen key attribute of the instance generating the id.
	 */
	void deleteId32GenBySchemaIdx( CFLibDbKeyHash256 SchemaDefId );

	/**
	 *	Move the CFBamId32GenObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamId32GenObj refreshed cache instance.
	 */
	ICFBamId32GenObj moveUpId32Gen( ICFBamId32GenObj Obj );

	/**
	 *	Move the CFBamId32GenObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamId32GenObj refreshed cache instance.
	 */
	ICFBamId32GenObj moveDownId32Gen( ICFBamId32GenObj Obj );
}
