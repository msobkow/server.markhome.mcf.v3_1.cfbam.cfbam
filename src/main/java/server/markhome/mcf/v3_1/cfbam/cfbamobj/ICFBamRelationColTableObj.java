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

public interface ICFBamRelationColTableObj
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
	 *	Instantiate a new RelationCol instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamRelationColObj newInstance();

	/**
	 *	Instantiate a new RelationCol edition of the specified RelationCol instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamRelationColEditObj newEditInstance( ICFBamRelationColObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamRelationColObj realiseRelationCol( ICFBamRelationColObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamRelationColObj createRelationCol( ICFBamRelationColObj Obj );

	/**
	 *	Read a RelationCol-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The RelationCol-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamRelationColObj readRelationCol( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a RelationCol-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The RelationCol-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamRelationColObj readRelationCol( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamRelationColObj readCachedRelationCol( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeRelationCol( ICFBamRelationColObj obj );

	void deepDisposeRelationCol( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamRelationColObj lockRelationCol( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the RelationCol-derived instances in the database.
	 *
	 *	@return	List of ICFBamRelationColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamRelationColObj> readAllRelationCol();

	/**
	 *	Return a sorted map of all the RelationCol-derived instances in the database.
	 *
	 *	@return	List of ICFBamRelationColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamRelationColObj> readAllRelationCol( boolean forceRead );

	List<ICFBamRelationColObj> readCachedAllRelationCol();

	/**
	 *	Get the CFBamRelationColObj instance for the primary key attributes.
	 *
	 *	@param	Id	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamRelationColObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamRelationColObj readRelationColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamRelationColObj instance for the primary key attributes.
	 *
	 *	@param	Id	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamRelationColObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamRelationColObj readRelationColByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamRelationColObj instance for the unique UNameIdx key.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamRelationColObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamRelationColObj readRelationColByUNameIdx(CFLibDbKeyHash256 RelationId,
		String Name );

	/**
	 *	Get the CFBamRelationColObj instance for the unique UNameIdx key.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamRelationColObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamRelationColObj readRelationColByUNameIdx(CFLibDbKeyHash256 RelationId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRelationColObj instances sorted by their primary keys for the duplicate RelationIdx key.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationColObj cached instances sorted by their primary keys for the duplicate RelationIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationColObj> readRelationColByRelationIdx( CFLibDbKeyHash256 RelationId );

	/**
	 *	Get the map of CFBamRelationColObj instances sorted by their primary keys for the duplicate RelationIdx key.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationColObj cached instances sorted by their primary keys for the duplicate RelationIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationColObj> readRelationColByRelationIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRelationColObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationColObj> readRelationColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamRelationColObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationColObj> readRelationColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRelationColObj instances sorted by their primary keys for the duplicate FromColIdx key.
	 *
	 *	@param	FromColId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationColObj cached instances sorted by their primary keys for the duplicate FromColIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationColObj> readRelationColByFromColIdx( CFLibDbKeyHash256 FromColId );

	/**
	 *	Get the map of CFBamRelationColObj instances sorted by their primary keys for the duplicate FromColIdx key.
	 *
	 *	@param	FromColId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationColObj cached instances sorted by their primary keys for the duplicate FromColIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationColObj> readRelationColByFromColIdx( CFLibDbKeyHash256 FromColId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRelationColObj instances sorted by their primary keys for the duplicate ToColIdx key.
	 *
	 *	@param	ToColId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationColObj cached instances sorted by their primary keys for the duplicate ToColIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationColObj> readRelationColByToColIdx( CFLibDbKeyHash256 ToColId );

	/**
	 *	Get the map of CFBamRelationColObj instances sorted by their primary keys for the duplicate ToColIdx key.
	 *
	 *	@param	ToColId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationColObj cached instances sorted by their primary keys for the duplicate ToColIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationColObj> readRelationColByToColIdx( CFLibDbKeyHash256 ToColId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRelationColObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationColObj> readRelationColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamRelationColObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationColObj> readRelationColByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRelationColObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationColObj> readRelationColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamRelationColObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationColObj> readRelationColByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRelationColObj instances sorted by their primary keys for the duplicate RelPrevIdx key.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationColObj cached instances sorted by their primary keys for the duplicate RelPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationColObj> readRelationColByRelPrevIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamRelationColObj instances sorted by their primary keys for the duplicate RelPrevIdx key.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationColObj cached instances sorted by their primary keys for the duplicate RelPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationColObj> readRelationColByRelPrevIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRelationColObj instances sorted by their primary keys for the duplicate RelNextIdx key.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationColObj cached instances sorted by their primary keys for the duplicate RelNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationColObj> readRelationColByRelNextIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamRelationColObj instances sorted by their primary keys for the duplicate RelNextIdx key.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationColObj cached instances sorted by their primary keys for the duplicate RelNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationColObj> readRelationColByRelNextIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	ICFBamRelationColObj readCachedRelationColByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamRelationColObj readCachedRelationColByUNameIdx( CFLibDbKeyHash256 RelationId,
		String Name );

	List<ICFBamRelationColObj> readCachedRelationColByRelationIdx( CFLibDbKeyHash256 RelationId );

	List<ICFBamRelationColObj> readCachedRelationColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamRelationColObj> readCachedRelationColByFromColIdx( CFLibDbKeyHash256 FromColId );

	List<ICFBamRelationColObj> readCachedRelationColByToColIdx( CFLibDbKeyHash256 ToColId );

	List<ICFBamRelationColObj> readCachedRelationColByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamRelationColObj> readCachedRelationColByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamRelationColObj> readCachedRelationColByRelPrevIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamRelationColObj> readCachedRelationColByRelNextIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeRelationColByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeRelationColByUNameIdx( CFLibDbKeyHash256 RelationId,
		String Name );

	void deepDisposeRelationColByRelationIdx( CFLibDbKeyHash256 RelationId );

	void deepDisposeRelationColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeRelationColByFromColIdx( CFLibDbKeyHash256 FromColId );

	void deepDisposeRelationColByToColIdx( CFLibDbKeyHash256 ToColId );

	void deepDisposeRelationColByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeRelationColByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeRelationColByRelPrevIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeRelationColByRelNextIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 */
	ICFBamRelationColObj updateRelationCol( ICFBamRelationColObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteRelationCol( ICFBamRelationColObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByUNameIdx(CFLibDbKeyHash256 RelationId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByRelationIdx( CFLibDbKeyHash256 RelationId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	FromColId	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByFromColIdx( CFLibDbKeyHash256 FromColId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ToColId	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByToColIdx( CFLibDbKeyHash256 ToColId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByRelPrevIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	RelationId	The RelationCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The RelationCol key attribute of the instance generating the id.
	 */
	void deleteRelationColByRelNextIdx( CFLibDbKeyHash256 RelationId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Move the CFBamRelationColObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamRelationColObj refreshed cache instance.
	 */
	ICFBamRelationColObj moveUpRelationCol( ICFBamRelationColObj Obj );

	/**
	 *	Move the CFBamRelationColObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamRelationColObj refreshed cache instance.
	 */
	ICFBamRelationColObj moveDownRelationCol( ICFBamRelationColObj Obj );
}
