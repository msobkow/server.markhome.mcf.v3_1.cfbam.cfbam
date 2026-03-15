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

public interface ICFBamInt32ColTableObj
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
	 *	Instantiate a new Int32Col instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamInt32ColObj newInstance();

	/**
	 *	Instantiate a new Int32Col edition of the specified Int32Col instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamInt32ColEditObj newEditInstance( ICFBamInt32ColObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamInt32ColObj realiseInt32Col( ICFBamInt32ColObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamInt32ColObj createInt32Col( ICFBamInt32ColObj Obj );

	/**
	 *	Read a Int32Col-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Int32Col-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamInt32ColObj readInt32Col( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a Int32Col-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Int32Col-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamInt32ColObj readInt32Col( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamInt32ColObj readCachedInt32Col( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeInt32Col( ICFBamInt32ColObj obj );

	void deepDisposeInt32Col( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamInt32ColObj lockInt32Col( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the Int32Col-derived instances in the database.
	 *
	 *	@return	List of ICFBamInt32ColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamInt32ColObj> readAllInt32Col();

	/**
	 *	Return a sorted map of all the Int32Col-derived instances in the database.
	 *
	 *	@return	List of ICFBamInt32ColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamInt32ColObj> readAllInt32Col( boolean forceRead );

	List<ICFBamInt32ColObj> readCachedAllInt32Col();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamInt32ColObj readInt32ColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamInt32ColObj readInt32ColByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamInt32ColObj readInt32ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamInt32ColObj readInt32ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32ColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32ColObj> readInt32ColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamInt32ColObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32ColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32ColObj> readInt32ColByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32ColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32ColObj> readInt32ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamInt32ColObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32ColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32ColObj> readInt32ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32ColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32ColObj> readInt32ColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamInt32ColObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32ColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32ColObj> readInt32ColByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32ColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32ColObj> readInt32ColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamInt32ColObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32ColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32ColObj> readInt32ColByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32ColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32ColObj> readInt32ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamInt32ColObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32ColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32ColObj> readInt32ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32ColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32ColObj> readInt32ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamInt32ColObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32ColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32ColObj> readInt32ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamInt32ColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32ColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32ColObj> readInt32ColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Get the map of CFBamInt32ColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamInt32ColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamInt32ColObj> readInt32ColByTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead );

	ICFBamInt32ColObj readCachedInt32ColByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamInt32ColObj readCachedInt32ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamInt32ColObj> readCachedInt32ColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamInt32ColObj> readCachedInt32ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamInt32ColObj> readCachedInt32ColByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamInt32ColObj> readCachedInt32ColByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamInt32ColObj> readCachedInt32ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamInt32ColObj> readCachedInt32ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	List<ICFBamInt32ColObj> readCachedInt32ColByTableIdx( CFLibDbKeyHash256 TableId );

	void deepDisposeInt32ColByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeInt32ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeInt32ColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeInt32ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeInt32ColByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeInt32ColByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeInt32ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeInt32ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeInt32ColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Internal use only.
	 */
	ICFBamInt32ColObj updateInt32Col( ICFBamInt32ColObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteInt32Col( ICFBamInt32ColObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The Int32Col key attribute of the instance generating the id.
	 */
	void deleteInt32ColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Int32Col key attribute of the instance generating the id.
	 */
	void deleteInt32ColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Int32Col key attribute of the instance generating the id.
	 */
	void deleteInt32ColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The Int32Col key attribute of the instance generating the id.
	 */
	void deleteInt32ColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The Int32Col key attribute of the instance generating the id.
	 */
	void deleteInt32ColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The Int32Col key attribute of the instance generating the id.
	 */
	void deleteInt32ColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Int32Col key attribute of the instance generating the id.
	 */
	void deleteInt32ColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Int32Col key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Int32Col key attribute of the instance generating the id.
	 */
	void deleteInt32ColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The Int32Col key attribute of the instance generating the id.
	 */
	void deleteInt32ColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Move the CFBamInt32ColObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamInt32ColObj refreshed cache instance.
	 */
	ICFBamInt32ColObj moveUpInt32Col( ICFBamInt32ColObj Obj );

	/**
	 *	Move the CFBamInt32ColObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamInt32ColObj refreshed cache instance.
	 */
	ICFBamInt32ColObj moveDownInt32Col( ICFBamInt32ColObj Obj );
}
