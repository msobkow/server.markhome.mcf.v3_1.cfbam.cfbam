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

public interface ICFBamPopSubDep1TableObj
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
	 *	Instantiate a new PopSubDep1 instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamPopSubDep1Obj newInstance();

	/**
	 *	Instantiate a new PopSubDep1 edition of the specified PopSubDep1 instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamPopSubDep1EditObj newEditInstance( ICFBamPopSubDep1Obj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamPopSubDep1Obj realisePopSubDep1( ICFBamPopSubDep1Obj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamPopSubDep1Obj createPopSubDep1( ICFBamPopSubDep1Obj Obj );

	/**
	 *	Read a PopSubDep1-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The PopSubDep1-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamPopSubDep1Obj readPopSubDep1( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a PopSubDep1-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The PopSubDep1-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamPopSubDep1Obj readPopSubDep1( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamPopSubDep1Obj readCachedPopSubDep1( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposePopSubDep1( ICFBamPopSubDep1Obj obj );

	void deepDisposePopSubDep1( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamPopSubDep1Obj lockPopSubDep1( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the PopSubDep1-derived instances in the database.
	 *
	 *	@return	List of ICFBamPopSubDep1Obj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamPopSubDep1Obj> readAllPopSubDep1();

	/**
	 *	Return a sorted map of all the PopSubDep1-derived instances in the database.
	 *
	 *	@return	List of ICFBamPopSubDep1Obj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamPopSubDep1Obj> readAllPopSubDep1( boolean forceRead );

	List<ICFBamPopSubDep1Obj> readCachedAllPopSubDep1();

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The PopSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamPopSubDep1Obj readPopSubDep1ByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The PopSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamPopSubDep1Obj readPopSubDep1ByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the map of CFBamScopeObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The PopSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamPopSubDep1Obj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamPopSubDep1Obj> readPopSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Get the map of CFBamPopSubDep1Obj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The PopSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamPopSubDep1Obj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamPopSubDep1Obj> readPopSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamPopDepObj instances sorted by their primary keys for the duplicate RelationIdx key.
	 *
	 *	@param	RelationId	The PopSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamPopSubDep1Obj cached instances sorted by their primary keys for the duplicate RelationIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamPopSubDep1Obj> readPopSubDep1ByRelationIdx( CFLibDbKeyHash256 RelationId );

	/**
	 *	Get the map of CFBamPopSubDep1Obj instances sorted by their primary keys for the duplicate RelationIdx key.
	 *
	 *	@param	RelationId	The PopSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamPopSubDep1Obj cached instances sorted by their primary keys for the duplicate RelationIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamPopSubDep1Obj> readPopSubDep1ByRelationIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamPopDepObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The PopSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamPopSubDep1Obj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamPopSubDep1Obj> readPopSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamPopSubDep1Obj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The PopSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamPopSubDep1Obj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamPopSubDep1Obj> readPopSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamPopSubDep1Obj instances sorted by their primary keys for the duplicate PopTopDepIdx key.
	 *
	 *	@param	PopTopDepId	The PopSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamPopSubDep1Obj cached instances sorted by their primary keys for the duplicate PopTopDepIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamPopSubDep1Obj> readPopSubDep1ByPopTopDepIdx( CFLibDbKeyHash256 PopTopDepId );

	/**
	 *	Get the map of CFBamPopSubDep1Obj instances sorted by their primary keys for the duplicate PopTopDepIdx key.
	 *
	 *	@param	PopTopDepId	The PopSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamPopSubDep1Obj cached instances sorted by their primary keys for the duplicate PopTopDepIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamPopSubDep1Obj> readPopSubDep1ByPopTopDepIdx( CFLibDbKeyHash256 PopTopDepId,
		boolean forceRead );

	/**
	 *	Get the CFBamPopSubDep1Obj instance for the unique UNameIdx key.
	 *
	 *	@param	PopTopDepId	The PopSubDep1 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The PopSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	CFBamPopSubDep1Obj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamPopSubDep1Obj readPopSubDep1ByUNameIdx(CFLibDbKeyHash256 PopTopDepId,
		String Name );

	/**
	 *	Get the CFBamPopSubDep1Obj instance for the unique UNameIdx key.
	 *
	 *	@param	PopTopDepId	The PopSubDep1 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The PopSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	CFBamPopSubDep1Obj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamPopSubDep1Obj readPopSubDep1ByUNameIdx(CFLibDbKeyHash256 PopTopDepId,
		String Name,
		boolean forceRead );

	ICFBamPopSubDep1Obj readCachedPopSubDep1ByIdIdx( CFLibDbKeyHash256 Id );

	List<ICFBamPopSubDep1Obj> readCachedPopSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId );

	List<ICFBamPopSubDep1Obj> readCachedPopSubDep1ByRelationIdx( CFLibDbKeyHash256 RelationId );

	List<ICFBamPopSubDep1Obj> readCachedPopSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamPopSubDep1Obj> readCachedPopSubDep1ByPopTopDepIdx( CFLibDbKeyHash256 PopTopDepId );

	ICFBamPopSubDep1Obj readCachedPopSubDep1ByUNameIdx( CFLibDbKeyHash256 PopTopDepId,
		String Name );

	void deepDisposePopSubDep1ByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposePopSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId );

	void deepDisposePopSubDep1ByRelationIdx( CFLibDbKeyHash256 RelationId );

	void deepDisposePopSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposePopSubDep1ByPopTopDepIdx( CFLibDbKeyHash256 PopTopDepId );

	void deepDisposePopSubDep1ByUNameIdx( CFLibDbKeyHash256 PopTopDepId,
		String Name );

	/**
	 *	Internal use only.
	 */
	ICFBamPopSubDep1Obj updatePopSubDep1( ICFBamPopSubDep1Obj Obj );

	/**
	 *	Internal use only.
	 */
	void deletePopSubDep1( ICFBamPopSubDep1Obj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The PopSubDep1 key attribute of the instance generating the id.
	 */
	void deletePopSubDep1ByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The PopSubDep1 key attribute of the instance generating the id.
	 */
	void deletePopSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Internal use only.
	 *
	 *	@param	RelationId	The PopSubDep1 key attribute of the instance generating the id.
	 */
	void deletePopSubDep1ByRelationIdx( CFLibDbKeyHash256 RelationId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The PopSubDep1 key attribute of the instance generating the id.
	 */
	void deletePopSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PopTopDepId	The PopSubDep1 key attribute of the instance generating the id.
	 */
	void deletePopSubDep1ByPopTopDepIdx( CFLibDbKeyHash256 PopTopDepId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PopTopDepId	The PopSubDep1 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The PopSubDep1 key attribute of the instance generating the id.
	 */
	void deletePopSubDep1ByUNameIdx(CFLibDbKeyHash256 PopTopDepId,
		String Name );
}
