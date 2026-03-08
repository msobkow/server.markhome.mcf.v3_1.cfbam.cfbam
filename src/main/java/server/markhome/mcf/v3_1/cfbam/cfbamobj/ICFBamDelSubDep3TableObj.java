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

public interface ICFBamDelSubDep3TableObj
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
	 *	Instantiate a new DelSubDep3 instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamDelSubDep3Obj newInstance();

	/**
	 *	Instantiate a new DelSubDep3 edition of the specified DelSubDep3 instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamDelSubDep3EditObj newEditInstance( ICFBamDelSubDep3Obj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamDelSubDep3Obj realiseDelSubDep3( ICFBamDelSubDep3Obj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamDelSubDep3Obj createDelSubDep3( ICFBamDelSubDep3Obj Obj );

	/**
	 *	Read a DelSubDep3-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DelSubDep3-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDelSubDep3Obj readDelSubDep3( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a DelSubDep3-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DelSubDep3-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDelSubDep3Obj readDelSubDep3( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamDelSubDep3Obj readCachedDelSubDep3( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeDelSubDep3( ICFBamDelSubDep3Obj obj );

	void deepDisposeDelSubDep3( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamDelSubDep3Obj lockDelSubDep3( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the DelSubDep3-derived instances in the database.
	 *
	 *	@return	List of ICFBamDelSubDep3Obj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDelSubDep3Obj> readAllDelSubDep3();

	/**
	 *	Return a sorted map of all the DelSubDep3-derived instances in the database.
	 *
	 *	@return	List of ICFBamDelSubDep3Obj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDelSubDep3Obj> readAllDelSubDep3( boolean forceRead );

	List<ICFBamDelSubDep3Obj> readCachedAllDelSubDep3();

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DelSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDelSubDep3Obj readDelSubDep3ByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DelSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDelSubDep3Obj readDelSubDep3ByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the map of CFBamScopeObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The DelSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelSubDep3Obj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelSubDep3Obj> readDelSubDep3ByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Get the map of CFBamDelSubDep3Obj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The DelSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelSubDep3Obj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelSubDep3Obj> readDelSubDep3ByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDelDepObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DelSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelSubDep3Obj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelSubDep3Obj> readDelSubDep3ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamDelSubDep3Obj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DelSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelSubDep3Obj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelSubDep3Obj> readDelSubDep3ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDelDepObj instances sorted by their primary keys for the duplicate DelDepIdx key.
	 *
	 *	@param	RelationId	The DelSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelSubDep3Obj cached instances sorted by their primary keys for the duplicate DelDepIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelSubDep3Obj> readDelSubDep3ByDelDepIdx( CFLibDbKeyHash256 RelationId );

	/**
	 *	Get the map of CFBamDelSubDep3Obj instances sorted by their primary keys for the duplicate DelDepIdx key.
	 *
	 *	@param	RelationId	The DelSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelSubDep3Obj cached instances sorted by their primary keys for the duplicate DelDepIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelSubDep3Obj> readDelSubDep3ByDelDepIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDelSubDep3Obj instances sorted by their primary keys for the duplicate DelSubDep2Idx key.
	 *
	 *	@param	DelSubDep2Id	The DelSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelSubDep3Obj cached instances sorted by their primary keys for the duplicate DelSubDep2Idx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelSubDep3Obj> readDelSubDep3ByDelSubDep2Idx( CFLibDbKeyHash256 DelSubDep2Id );

	/**
	 *	Get the map of CFBamDelSubDep3Obj instances sorted by their primary keys for the duplicate DelSubDep2Idx key.
	 *
	 *	@param	DelSubDep2Id	The DelSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelSubDep3Obj cached instances sorted by their primary keys for the duplicate DelSubDep2Idx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelSubDep3Obj> readDelSubDep3ByDelSubDep2Idx( CFLibDbKeyHash256 DelSubDep2Id,
		boolean forceRead );

	/**
	 *	Get the CFBamDelSubDep3Obj instance for the unique UNameIdx key.
	 *
	 *	@param	DelSubDep2Id	The DelSubDep3 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DelSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return	CFBamDelSubDep3Obj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDelSubDep3Obj readDelSubDep3ByUNameIdx(CFLibDbKeyHash256 DelSubDep2Id,
		String Name );

	/**
	 *	Get the CFBamDelSubDep3Obj instance for the unique UNameIdx key.
	 *
	 *	@param	DelSubDep2Id	The DelSubDep3 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DelSubDep3 key attribute of the instance generating the id.
	 *
	 *	@return	CFBamDelSubDep3Obj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDelSubDep3Obj readDelSubDep3ByUNameIdx(CFLibDbKeyHash256 DelSubDep2Id,
		String Name,
		boolean forceRead );

	ICFBamDelSubDep3Obj readCachedDelSubDep3ByIdIdx( CFLibDbKeyHash256 Id );

	List<ICFBamDelSubDep3Obj> readCachedDelSubDep3ByTenantIdx( CFLibDbKeyHash256 TenantId );

	List<ICFBamDelSubDep3Obj> readCachedDelSubDep3ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamDelSubDep3Obj> readCachedDelSubDep3ByDelDepIdx( CFLibDbKeyHash256 RelationId );

	List<ICFBamDelSubDep3Obj> readCachedDelSubDep3ByDelSubDep2Idx( CFLibDbKeyHash256 DelSubDep2Id );

	ICFBamDelSubDep3Obj readCachedDelSubDep3ByUNameIdx( CFLibDbKeyHash256 DelSubDep2Id,
		String Name );

	void deepDisposeDelSubDep3ByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeDelSubDep3ByTenantIdx( CFLibDbKeyHash256 TenantId );

	void deepDisposeDelSubDep3ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeDelSubDep3ByDelDepIdx( CFLibDbKeyHash256 RelationId );

	void deepDisposeDelSubDep3ByDelSubDep2Idx( CFLibDbKeyHash256 DelSubDep2Id );

	void deepDisposeDelSubDep3ByUNameIdx( CFLibDbKeyHash256 DelSubDep2Id,
		String Name );

	/**
	 *	Internal use only.
	 */
	ICFBamDelSubDep3Obj updateDelSubDep3( ICFBamDelSubDep3Obj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteDelSubDep3( ICFBamDelSubDep3Obj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The DelSubDep3 key attribute of the instance generating the id.
	 */
	void deleteDelSubDep3ByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The DelSubDep3 key attribute of the instance generating the id.
	 */
	void deleteDelSubDep3ByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The DelSubDep3 key attribute of the instance generating the id.
	 */
	void deleteDelSubDep3ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	RelationId	The DelSubDep3 key attribute of the instance generating the id.
	 */
	void deleteDelSubDep3ByDelDepIdx( CFLibDbKeyHash256 RelationId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DelSubDep2Id	The DelSubDep3 key attribute of the instance generating the id.
	 */
	void deleteDelSubDep3ByDelSubDep2Idx( CFLibDbKeyHash256 DelSubDep2Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	DelSubDep2Id	The DelSubDep3 key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DelSubDep3 key attribute of the instance generating the id.
	 */
	void deleteDelSubDep3ByUNameIdx(CFLibDbKeyHash256 DelSubDep2Id,
		String Name );
}
