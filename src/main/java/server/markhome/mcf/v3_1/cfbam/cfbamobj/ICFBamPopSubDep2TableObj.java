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

public interface ICFBamPopSubDep2TableObj
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
	 *	Instantiate a new PopSubDep2 instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamPopSubDep2Obj newInstance();

	/**
	 *	Instantiate a new PopSubDep2 edition of the specified PopSubDep2 instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamPopSubDep2EditObj newEditInstance( ICFBamPopSubDep2Obj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamPopSubDep2Obj realisePopSubDep2( ICFBamPopSubDep2Obj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamPopSubDep2Obj createPopSubDep2( ICFBamPopSubDep2Obj Obj );

	/**
	 *	Read a PopSubDep2-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The PopSubDep2-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamPopSubDep2Obj readPopSubDep2( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a PopSubDep2-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The PopSubDep2-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamPopSubDep2Obj readPopSubDep2( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamPopSubDep2Obj readCachedPopSubDep2( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposePopSubDep2( ICFBamPopSubDep2Obj obj );

	void deepDisposePopSubDep2( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamPopSubDep2Obj lockPopSubDep2( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the PopSubDep2-derived instances in the database.
	 *
	 *	@return	List of ICFBamPopSubDep2Obj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamPopSubDep2Obj> readAllPopSubDep2();

	/**
	 *	Return a sorted map of all the PopSubDep2-derived instances in the database.
	 *
	 *	@return	List of ICFBamPopSubDep2Obj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamPopSubDep2Obj> readAllPopSubDep2( boolean forceRead );

	List<ICFBamPopSubDep2Obj> readCachedAllPopSubDep2();

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The PopSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamPopSubDep2Obj readPopSubDep2ByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The PopSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamPopSubDep2Obj readPopSubDep2ByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the map of CFBamScopeObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The PopSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamPopSubDep2Obj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamPopSubDep2Obj> readPopSubDep2ByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Get the map of CFBamPopSubDep2Obj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The PopSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamPopSubDep2Obj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamPopSubDep2Obj> readPopSubDep2ByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamPopDepObj instances sorted by their primary keys for the duplicate RelationIdx key.
	 *
	 *	@param	RelationId	The PopSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamPopSubDep2Obj cached instances sorted by their primary keys for the duplicate RelationIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamPopSubDep2Obj> readPopSubDep2ByRelationIdx( CFLibDbKeyHash256 RelationId );

	/**
	 *	Get the map of CFBamPopSubDep2Obj instances sorted by their primary keys for the duplicate RelationIdx key.
	 *
	 *	@param	RelationId	The PopSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamPopSubDep2Obj cached instances sorted by their primary keys for the duplicate RelationIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamPopSubDep2Obj> readPopSubDep2ByRelationIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamPopDepObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The PopSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamPopSubDep2Obj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamPopSubDep2Obj> readPopSubDep2ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamPopSubDep2Obj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The PopSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamPopSubDep2Obj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamPopSubDep2Obj> readPopSubDep2ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamPopSubDep2Obj instances sorted by their primary keys for the duplicate PopSubDep1Idx key.
	 *
	 *	@param	PopSubDep1Id	The PopSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamPopSubDep2Obj cached instances sorted by their primary keys for the duplicate PopSubDep1Idx key,
	 *		which may be an empty set.
	 */
	List<ICFBamPopSubDep2Obj> readPopSubDep2ByPopSubDep1Idx( CFLibDbKeyHash256 PopSubDep1Id );

	/**
	 *	Get the map of CFBamPopSubDep2Obj instances sorted by their primary keys for the duplicate PopSubDep1Idx key.
	 *
	 *	@param	PopSubDep1Id	The PopSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamPopSubDep2Obj cached instances sorted by their primary keys for the duplicate PopSubDep1Idx key,
	 *		which may be an empty set.
	 */
	List<ICFBamPopSubDep2Obj> readPopSubDep2ByPopSubDep1Idx( CFLibDbKeyHash256 PopSubDep1Id,
		boolean forceRead );

	/**
	 *	Get the CFBamPopSubDep2Obj instance for the unique UNameIdx key.
	 *
	 *	@param	PopSubDep1Id	The PopSubDep2 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The PopSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return	CFBamPopSubDep2Obj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamPopSubDep2Obj readPopSubDep2ByUNameIdx(CFLibDbKeyHash256 PopSubDep1Id,
		String Name );

	/**
	 *	Get the CFBamPopSubDep2Obj instance for the unique UNameIdx key.
	 *
	 *	@param	PopSubDep1Id	The PopSubDep2 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The PopSubDep2 key attribute of the instance generating the id.
	 *
	 *	@return	CFBamPopSubDep2Obj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamPopSubDep2Obj readPopSubDep2ByUNameIdx(CFLibDbKeyHash256 PopSubDep1Id,
		String Name,
		boolean forceRead );

	ICFBamPopSubDep2Obj readCachedPopSubDep2ByIdIdx( CFLibDbKeyHash256 Id );

	List<ICFBamPopSubDep2Obj> readCachedPopSubDep2ByTenantIdx( CFLibDbKeyHash256 TenantId );

	List<ICFBamPopSubDep2Obj> readCachedPopSubDep2ByRelationIdx( CFLibDbKeyHash256 RelationId );

	List<ICFBamPopSubDep2Obj> readCachedPopSubDep2ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamPopSubDep2Obj> readCachedPopSubDep2ByPopSubDep1Idx( CFLibDbKeyHash256 PopSubDep1Id );

	ICFBamPopSubDep2Obj readCachedPopSubDep2ByUNameIdx( CFLibDbKeyHash256 PopSubDep1Id,
		String Name );

	void deepDisposePopSubDep2ByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposePopSubDep2ByTenantIdx( CFLibDbKeyHash256 TenantId );

	void deepDisposePopSubDep2ByRelationIdx( CFLibDbKeyHash256 RelationId );

	void deepDisposePopSubDep2ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposePopSubDep2ByPopSubDep1Idx( CFLibDbKeyHash256 PopSubDep1Id );

	void deepDisposePopSubDep2ByUNameIdx( CFLibDbKeyHash256 PopSubDep1Id,
		String Name );

	/**
	 *	Internal use only.
	 */
	ICFBamPopSubDep2Obj updatePopSubDep2( ICFBamPopSubDep2Obj Obj );

	/**
	 *	Internal use only.
	 */
	void deletePopSubDep2( ICFBamPopSubDep2Obj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The PopSubDep2 key attribute of the instance generating the id.
	 */
	void deletePopSubDep2ByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The PopSubDep2 key attribute of the instance generating the id.
	 */
	void deletePopSubDep2ByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Internal use only.
	 *
	 *	@param	RelationId	The PopSubDep2 key attribute of the instance generating the id.
	 */
	void deletePopSubDep2ByRelationIdx( CFLibDbKeyHash256 RelationId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The PopSubDep2 key attribute of the instance generating the id.
	 */
	void deletePopSubDep2ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PopSubDep1Id	The PopSubDep2 key attribute of the instance generating the id.
	 */
	void deletePopSubDep2ByPopSubDep1Idx( CFLibDbKeyHash256 PopSubDep1Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	PopSubDep1Id	The PopSubDep2 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The PopSubDep2 key attribute of the instance generating the id.
	 */
	void deletePopSubDep2ByUNameIdx(CFLibDbKeyHash256 PopSubDep1Id,
		String Name );
}
