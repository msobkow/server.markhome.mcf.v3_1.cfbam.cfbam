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

public interface ICFBamDelTopDepTableObj
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
	 *	Instantiate a new DelTopDep instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamDelTopDepObj newInstance();

	/**
	 *	Instantiate a new DelTopDep edition of the specified DelTopDep instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamDelTopDepEditObj newEditInstance( ICFBamDelTopDepObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamDelTopDepObj realiseDelTopDep( ICFBamDelTopDepObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamDelTopDepObj createDelTopDep( ICFBamDelTopDepObj Obj );

	/**
	 *	Read a DelTopDep-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DelTopDep-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDelTopDepObj readDelTopDep( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a DelTopDep-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DelTopDep-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDelTopDepObj readDelTopDep( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamDelTopDepObj readCachedDelTopDep( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeDelTopDep( ICFBamDelTopDepObj obj );

	void deepDisposeDelTopDep( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamDelTopDepObj lockDelTopDep( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the DelTopDep-derived instances in the database.
	 *
	 *	@return	List of ICFBamDelTopDepObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDelTopDepObj> readAllDelTopDep();

	/**
	 *	Return a sorted map of all the DelTopDep-derived instances in the database.
	 *
	 *	@return	List of ICFBamDelTopDepObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDelTopDepObj> readAllDelTopDep( boolean forceRead );

	List<ICFBamDelTopDepObj> readCachedAllDelTopDep();

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDelTopDepObj readDelTopDepByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDelTopDepObj readDelTopDepByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the map of CFBamScopeObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelTopDepObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelTopDepObj> readDelTopDepByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Get the map of CFBamDelTopDepObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelTopDepObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelTopDepObj> readDelTopDepByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDelDepObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelTopDepObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelTopDepObj> readDelTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamDelTopDepObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelTopDepObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelTopDepObj> readDelTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDelDepObj instances sorted by their primary keys for the duplicate DelDepIdx key.
	 *
	 *	@param	RelationId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelTopDepObj cached instances sorted by their primary keys for the duplicate DelDepIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelTopDepObj> readDelTopDepByDelDepIdx( CFLibDbKeyHash256 RelationId );

	/**
	 *	Get the map of CFBamDelTopDepObj instances sorted by their primary keys for the duplicate DelDepIdx key.
	 *
	 *	@param	RelationId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelTopDepObj cached instances sorted by their primary keys for the duplicate DelDepIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelTopDepObj> readDelTopDepByDelDepIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDelTopDepObj instances sorted by their primary keys for the duplicate DelTopDepTblIdx key.
	 *
	 *	@param	TableId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelTopDepObj cached instances sorted by their primary keys for the duplicate DelTopDepTblIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelTopDepObj> readDelTopDepByDelTopDepTblIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Get the map of CFBamDelTopDepObj instances sorted by their primary keys for the duplicate DelTopDepTblIdx key.
	 *
	 *	@param	TableId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelTopDepObj cached instances sorted by their primary keys for the duplicate DelTopDepTblIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelTopDepObj> readDelTopDepByDelTopDepTblIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead );

	/**
	 *	Get the CFBamDelTopDepObj instance for the unique UNameIdx key.
	 *
	 *	@param	TableId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return	CFBamDelTopDepObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDelTopDepObj readDelTopDepByUNameIdx(CFLibDbKeyHash256 TableId,
		String Name );

	/**
	 *	Get the CFBamDelTopDepObj instance for the unique UNameIdx key.
	 *
	 *	@param	TableId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return	CFBamDelTopDepObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDelTopDepObj readDelTopDepByUNameIdx(CFLibDbKeyHash256 TableId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDelTopDepObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelTopDepObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelTopDepObj> readDelTopDepByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamDelTopDepObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelTopDepObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelTopDepObj> readDelTopDepByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDelTopDepObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelTopDepObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelTopDepObj> readDelTopDepByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamDelTopDepObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelTopDepObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelTopDepObj> readDelTopDepByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	ICFBamDelTopDepObj readCachedDelTopDepByIdIdx( CFLibDbKeyHash256 Id );

	List<ICFBamDelTopDepObj> readCachedDelTopDepByTenantIdx( CFLibDbKeyHash256 TenantId );

	List<ICFBamDelTopDepObj> readCachedDelTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamDelTopDepObj> readCachedDelTopDepByDelDepIdx( CFLibDbKeyHash256 RelationId );

	List<ICFBamDelTopDepObj> readCachedDelTopDepByDelTopDepTblIdx( CFLibDbKeyHash256 TableId );

	ICFBamDelTopDepObj readCachedDelTopDepByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name );

	List<ICFBamDelTopDepObj> readCachedDelTopDepByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamDelTopDepObj> readCachedDelTopDepByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeDelTopDepByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeDelTopDepByTenantIdx( CFLibDbKeyHash256 TenantId );

	void deepDisposeDelTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeDelTopDepByDelDepIdx( CFLibDbKeyHash256 RelationId );

	void deepDisposeDelTopDepByDelTopDepTblIdx( CFLibDbKeyHash256 TableId );

	void deepDisposeDelTopDepByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name );

	void deepDisposeDelTopDepByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeDelTopDepByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 */
	ICFBamDelTopDepObj updateDelTopDep( ICFBamDelTopDepObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteDelTopDep( ICFBamDelTopDepObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The DelTopDep key attribute of the instance generating the id.
	 */
	void deleteDelTopDepByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The DelTopDep key attribute of the instance generating the id.
	 */
	void deleteDelTopDepByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The DelTopDep key attribute of the instance generating the id.
	 */
	void deleteDelTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	RelationId	The DelTopDep key attribute of the instance generating the id.
	 */
	void deleteDelTopDepByDelDepIdx( CFLibDbKeyHash256 RelationId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The DelTopDep key attribute of the instance generating the id.
	 */
	void deleteDelTopDepByDelTopDepTblIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The DelTopDep key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DelTopDep key attribute of the instance generating the id.
	 */
	void deleteDelTopDepByUNameIdx(CFLibDbKeyHash256 TableId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The DelTopDep key attribute of the instance generating the id.
	 */
	void deleteDelTopDepByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The DelTopDep key attribute of the instance generating the id.
	 */
	void deleteDelTopDepByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Move the CFBamDelTopDepObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDelTopDepObj refreshed cache instance.
	 */
	ICFBamDelTopDepObj moveUpDelTopDep( ICFBamDelTopDepObj Obj );

	/**
	 *	Move the CFBamDelTopDepObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDelTopDepObj refreshed cache instance.
	 */
	ICFBamDelTopDepObj moveDownDelTopDep( ICFBamDelTopDepObj Obj );
}
