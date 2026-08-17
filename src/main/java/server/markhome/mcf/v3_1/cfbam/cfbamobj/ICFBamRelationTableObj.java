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

public interface ICFBamRelationTableObj
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
	 *	Instantiate a new Relation instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamRelationObj newInstance();

	/**
	 *	Instantiate a new Relation edition of the specified Relation instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamRelationEditObj newEditInstance( ICFBamRelationObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamRelationObj realiseRelation( ICFBamRelationObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamRelationObj createRelation( ICFBamRelationObj Obj );

	/**
	 *	Read a Relation-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Relation-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamRelationObj readRelation( ICFLibKeyHash256 pkey );

	/**
	 *	Read a Relation-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Relation-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamRelationObj readRelation( ICFLibKeyHash256 pkey,
		boolean forceRead );

	ICFBamRelationObj readCachedRelation( ICFLibKeyHash256 pkey );

	public void reallyDeepDisposeRelation( ICFBamRelationObj obj );

	void deepDisposeRelation( ICFLibKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamRelationObj lockRelation( ICFLibKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the Relation-derived instances in the database.
	 *
	 *	@return	List of ICFBamRelationObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamRelationObj> readAllRelation();

	/**
	 *	Return a sorted map of all the Relation-derived instances in the database.
	 *
	 *	@return	List of ICFBamRelationObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamRelationObj> readAllRelation( boolean forceRead );

	List<ICFBamRelationObj> readCachedAllRelation();

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamRelationObj readRelationByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamRelationObj readRelationByIdIdx( ICFLibKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the map of CFBamScopeObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByTenantIdx( ICFLibKeyHash256 TenantId );

	/**
	 *	Get the map of CFBamRelationObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByTenantIdx( ICFLibKeyHash256 TenantId,
		boolean forceRead );

	/**
	 *	Get the CFBamRelationObj instance for the unique UNameIdx key.
	 *
	 *	@param	TableId	The Relation key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	CFBamRelationObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamRelationObj readRelationByUNameIdx(ICFLibKeyHash256 TableId,
		String Name );

	/**
	 *	Get the CFBamRelationObj instance for the unique UNameIdx key.
	 *
	 *	@param	TableId	The Relation key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	CFBamRelationObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamRelationObj readRelationByUNameIdx(ICFLibKeyHash256 TableId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRelationObj instances sorted by their primary keys for the duplicate RelTableIdx key.
	 *
	 *	@param	TableId	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate RelTableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByRelTableIdx( ICFLibKeyHash256 TableId );

	/**
	 *	Get the map of CFBamRelationObj instances sorted by their primary keys for the duplicate RelTableIdx key.
	 *
	 *	@param	TableId	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate RelTableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByRelTableIdx( ICFLibKeyHash256 TableId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRelationObj instances sorted by their primary keys for the duplicate RelCodeVisIdx key.
	 *
	 *	@param	CodeVis	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate RelCodeVisIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByRelCodeVisIdx( ICFBamPubSchema.CodeVisibilityEnum CodeVis );

	/**
	 *	Get the map of CFBamRelationObj instances sorted by their primary keys for the duplicate RelCodeVisIdx key.
	 *
	 *	@param	CodeVis	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate RelCodeVisIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByRelCodeVisIdx( ICFBamPubSchema.CodeVisibilityEnum CodeVis,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRelationObj instances sorted by their primary keys for the duplicate RelTableCodeVisX key.
	 *
	 *	@param	TableId	The Relation key attribute of the instance generating the id.
	 *
	 *	@param	CodeVis	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate RelTableCodeVisX key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByRelTableCodeVisX( ICFLibKeyHash256 TableId,
		ICFBamPubSchema.CodeVisibilityEnum CodeVis );

	/**
	 *	Get the map of CFBamRelationObj instances sorted by their primary keys for the duplicate RelTableCodeVisX key.
	 *
	 *	@param	TableId	The Relation key attribute of the instance generating the id.
	 *
	 *	@param	CodeVis	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate RelTableCodeVisX key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByRelTableCodeVisX( ICFLibKeyHash256 TableId,
		ICFBamPubSchema.CodeVisibilityEnum CodeVis,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRelationObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamRelationObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRelationObj instances sorted by their primary keys for the duplicate FromKeyIdx key.
	 *
	 *	@param	FromIndexId	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate FromKeyIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByFromKeyIdx( ICFLibKeyHash256 FromIndexId );

	/**
	 *	Get the map of CFBamRelationObj instances sorted by their primary keys for the duplicate FromKeyIdx key.
	 *
	 *	@param	FromIndexId	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate FromKeyIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByFromKeyIdx( ICFLibKeyHash256 FromIndexId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRelationObj instances sorted by their primary keys for the duplicate ToTblIdx key.
	 *
	 *	@param	ToTableId	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate ToTblIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByToTblIdx( ICFLibKeyHash256 ToTableId );

	/**
	 *	Get the map of CFBamRelationObj instances sorted by their primary keys for the duplicate ToTblIdx key.
	 *
	 *	@param	ToTableId	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate ToTblIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByToTblIdx( ICFLibKeyHash256 ToTableId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRelationObj instances sorted by their primary keys for the duplicate ToKeyIdx key.
	 *
	 *	@param	ToIndexId	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate ToKeyIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByToKeyIdx( ICFLibKeyHash256 ToIndexId );

	/**
	 *	Get the map of CFBamRelationObj instances sorted by their primary keys for the duplicate ToKeyIdx key.
	 *
	 *	@param	ToIndexId	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate ToKeyIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByToKeyIdx( ICFLibKeyHash256 ToIndexId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamRelationObj instances sorted by their primary keys for the duplicate NarrowedIdx key.
	 *
	 *	@param	NarrowedId	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate NarrowedIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByNarrowedIdx( ICFLibKeyHash256 NarrowedId );

	/**
	 *	Get the map of CFBamRelationObj instances sorted by their primary keys for the duplicate NarrowedIdx key.
	 *
	 *	@param	NarrowedId	The Relation key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamRelationObj cached instances sorted by their primary keys for the duplicate NarrowedIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamRelationObj> readRelationByNarrowedIdx( ICFLibKeyHash256 NarrowedId,
		boolean forceRead );

	ICFBamRelationObj readCachedRelationByIdIdx( ICFLibKeyHash256 Id );

	List<ICFBamRelationObj> readCachedRelationByTenantIdx( ICFLibKeyHash256 TenantId );

	ICFBamRelationObj readCachedRelationByUNameIdx( ICFLibKeyHash256 TableId,
		String Name );

	List<ICFBamRelationObj> readCachedRelationByRelTableIdx( ICFLibKeyHash256 TableId );

	List<ICFBamRelationObj> readCachedRelationByRelCodeVisIdx( ICFBamPubSchema.CodeVisibilityEnum CodeVis );

	List<ICFBamRelationObj> readCachedRelationByRelTableCodeVisX( ICFLibKeyHash256 TableId,
		ICFBamPubSchema.CodeVisibilityEnum CodeVis );

	List<ICFBamRelationObj> readCachedRelationByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	List<ICFBamRelationObj> readCachedRelationByFromKeyIdx( ICFLibKeyHash256 FromIndexId );

	List<ICFBamRelationObj> readCachedRelationByToTblIdx( ICFLibKeyHash256 ToTableId );

	List<ICFBamRelationObj> readCachedRelationByToKeyIdx( ICFLibKeyHash256 ToIndexId );

	List<ICFBamRelationObj> readCachedRelationByNarrowedIdx( ICFLibKeyHash256 NarrowedId );

	void deepDisposeRelationByIdIdx( ICFLibKeyHash256 Id );

	void deepDisposeRelationByTenantIdx( ICFLibKeyHash256 TenantId );

	void deepDisposeRelationByUNameIdx( ICFLibKeyHash256 TableId,
		String Name );

	void deepDisposeRelationByRelTableIdx( ICFLibKeyHash256 TableId );

	void deepDisposeRelationByRelCodeVisIdx( ICFBamPubSchema.CodeVisibilityEnum CodeVis );

	void deepDisposeRelationByRelTableCodeVisX( ICFLibKeyHash256 TableId,
		ICFBamPubSchema.CodeVisibilityEnum CodeVis );

	void deepDisposeRelationByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	void deepDisposeRelationByFromKeyIdx( ICFLibKeyHash256 FromIndexId );

	void deepDisposeRelationByToTblIdx( ICFLibKeyHash256 ToTableId );

	void deepDisposeRelationByToKeyIdx( ICFLibKeyHash256 ToIndexId );

	void deepDisposeRelationByNarrowedIdx( ICFLibKeyHash256 NarrowedId );

	/**
	 *	Internal use only.
	 */
	ICFBamRelationObj updateRelation( ICFBamRelationObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteRelation( ICFBamRelationObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The Relation key attribute of the instance generating the id.
	 */
	void deleteRelationByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The Relation key attribute of the instance generating the id.
	 */
	void deleteRelationByTenantIdx( ICFLibKeyHash256 TenantId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The Relation key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Relation key attribute of the instance generating the id.
	 */
	void deleteRelationByUNameIdx(ICFLibKeyHash256 TableId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The Relation key attribute of the instance generating the id.
	 */
	void deleteRelationByRelTableIdx( ICFLibKeyHash256 TableId );

	/**
	 *	Internal use only.
	 *
	 *	@param	CodeVis	The Relation key attribute of the instance generating the id.
	 */
	void deleteRelationByRelCodeVisIdx( ICFBamPubSchema.CodeVisibilityEnum CodeVis );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The Relation key attribute of the instance generating the id.
	 *
	 *	@param	CodeVis	The Relation key attribute of the instance generating the id.
	 */
	void deleteRelationByRelTableCodeVisX( ICFLibKeyHash256 TableId,
		ICFBamPubSchema.CodeVisibilityEnum CodeVis );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The Relation key attribute of the instance generating the id.
	 */
	void deleteRelationByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	FromIndexId	The Relation key attribute of the instance generating the id.
	 */
	void deleteRelationByFromKeyIdx( ICFLibKeyHash256 FromIndexId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ToTableId	The Relation key attribute of the instance generating the id.
	 */
	void deleteRelationByToTblIdx( ICFLibKeyHash256 ToTableId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ToIndexId	The Relation key attribute of the instance generating the id.
	 */
	void deleteRelationByToKeyIdx( ICFLibKeyHash256 ToIndexId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NarrowedId	The Relation key attribute of the instance generating the id.
	 */
	void deleteRelationByNarrowedIdx( ICFLibKeyHash256 NarrowedId );
}
