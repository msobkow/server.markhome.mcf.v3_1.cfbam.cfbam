// Description: Java 25 Table Object interface for CFBam.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
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
 *	
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

public interface ICFBamDelSubDep1TableObj
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
	 *	Instantiate a new DelSubDep1 instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamDelSubDep1Obj newInstance();

	/**
	 *	Instantiate a new DelSubDep1 edition of the specified DelSubDep1 instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamDelSubDep1EditObj newEditInstance( ICFBamDelSubDep1Obj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamDelSubDep1Obj realiseDelSubDep1( ICFBamDelSubDep1Obj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamDelSubDep1Obj createDelSubDep1( ICFBamDelSubDep1Obj Obj );

	/**
	 *	Read a DelSubDep1-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DelSubDep1-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDelSubDep1Obj readDelSubDep1( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a DelSubDep1-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DelSubDep1-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDelSubDep1Obj readDelSubDep1( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamDelSubDep1Obj readCachedDelSubDep1( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeDelSubDep1( ICFBamDelSubDep1Obj obj );

	void deepDisposeDelSubDep1( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamDelSubDep1Obj lockDelSubDep1( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the DelSubDep1-derived instances in the database.
	 *
	 *	@return	List of ICFBamDelSubDep1Obj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDelSubDep1Obj> readAllDelSubDep1();

	/**
	 *	Return a sorted map of all the DelSubDep1-derived instances in the database.
	 *
	 *	@return	List of ICFBamDelSubDep1Obj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDelSubDep1Obj> readAllDelSubDep1( boolean forceRead );

	List<ICFBamDelSubDep1Obj> readCachedAllDelSubDep1();

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDelSubDep1Obj readDelSubDep1ByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDelSubDep1Obj readDelSubDep1ByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the map of CFBamScopeObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelSubDep1Obj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelSubDep1Obj> readDelSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Get the map of CFBamDelSubDep1Obj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelSubDep1Obj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelSubDep1Obj> readDelSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDelDepObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelSubDep1Obj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelSubDep1Obj> readDelSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamDelSubDep1Obj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelSubDep1Obj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelSubDep1Obj> readDelSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDelDepObj instances sorted by their primary keys for the duplicate DelDepIdx key.
	 *
	 *	@param	RelationId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelSubDep1Obj cached instances sorted by their primary keys for the duplicate DelDepIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelSubDep1Obj> readDelSubDep1ByDelDepIdx( CFLibDbKeyHash256 RelationId );

	/**
	 *	Get the map of CFBamDelSubDep1Obj instances sorted by their primary keys for the duplicate DelDepIdx key.
	 *
	 *	@param	RelationId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelSubDep1Obj cached instances sorted by their primary keys for the duplicate DelDepIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelSubDep1Obj> readDelSubDep1ByDelDepIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDelSubDep1Obj instances sorted by their primary keys for the duplicate DelTopDepIdx key.
	 *
	 *	@param	DelTopDepId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelSubDep1Obj cached instances sorted by their primary keys for the duplicate DelTopDepIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelSubDep1Obj> readDelSubDep1ByDelTopDepIdx( CFLibDbKeyHash256 DelTopDepId );

	/**
	 *	Get the map of CFBamDelSubDep1Obj instances sorted by their primary keys for the duplicate DelTopDepIdx key.
	 *
	 *	@param	DelTopDepId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelSubDep1Obj cached instances sorted by their primary keys for the duplicate DelTopDepIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelSubDep1Obj> readDelSubDep1ByDelTopDepIdx( CFLibDbKeyHash256 DelTopDepId,
		boolean forceRead );

	/**
	 *	Get the CFBamDelSubDep1Obj instance for the unique UNameIdx key.
	 *
	 *	@param	DelTopDepId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	CFBamDelSubDep1Obj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDelSubDep1Obj readDelSubDep1ByUNameIdx(CFLibDbKeyHash256 DelTopDepId,
		String Name );

	/**
	 *	Get the CFBamDelSubDep1Obj instance for the unique UNameIdx key.
	 *
	 *	@param	DelTopDepId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@return	CFBamDelSubDep1Obj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDelSubDep1Obj readDelSubDep1ByUNameIdx(CFLibDbKeyHash256 DelTopDepId,
		String Name,
		boolean forceRead );

	ICFBamDelSubDep1Obj readCachedDelSubDep1ByIdIdx( CFLibDbKeyHash256 Id );

	List<ICFBamDelSubDep1Obj> readCachedDelSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId );

	List<ICFBamDelSubDep1Obj> readCachedDelSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamDelSubDep1Obj> readCachedDelSubDep1ByDelDepIdx( CFLibDbKeyHash256 RelationId );

	List<ICFBamDelSubDep1Obj> readCachedDelSubDep1ByDelTopDepIdx( CFLibDbKeyHash256 DelTopDepId );

	ICFBamDelSubDep1Obj readCachedDelSubDep1ByUNameIdx( CFLibDbKeyHash256 DelTopDepId,
		String Name );

	void deepDisposeDelSubDep1ByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeDelSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId );

	void deepDisposeDelSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeDelSubDep1ByDelDepIdx( CFLibDbKeyHash256 RelationId );

	void deepDisposeDelSubDep1ByDelTopDepIdx( CFLibDbKeyHash256 DelTopDepId );

	void deepDisposeDelSubDep1ByUNameIdx( CFLibDbKeyHash256 DelTopDepId,
		String Name );

	/**
	 *	Internal use only.
	 */
	ICFBamDelSubDep1Obj updateDelSubDep1( ICFBamDelSubDep1Obj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteDelSubDep1( ICFBamDelSubDep1Obj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The DelSubDep1 key attribute of the instance generating the id.
	 */
	void deleteDelSubDep1ByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The DelSubDep1 key attribute of the instance generating the id.
	 */
	void deleteDelSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The DelSubDep1 key attribute of the instance generating the id.
	 */
	void deleteDelSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	RelationId	The DelSubDep1 key attribute of the instance generating the id.
	 */
	void deleteDelSubDep1ByDelDepIdx( CFLibDbKeyHash256 RelationId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DelTopDepId	The DelSubDep1 key attribute of the instance generating the id.
	 */
	void deleteDelSubDep1ByDelTopDepIdx( CFLibDbKeyHash256 DelTopDepId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DelTopDepId	The DelSubDep1 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DelSubDep1 key attribute of the instance generating the id.
	 */
	void deleteDelSubDep1ByUNameIdx(CFLibDbKeyHash256 DelTopDepId,
		String Name );
}
