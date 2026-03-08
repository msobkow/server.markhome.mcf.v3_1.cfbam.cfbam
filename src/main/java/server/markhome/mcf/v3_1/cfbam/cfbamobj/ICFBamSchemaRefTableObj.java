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

public interface ICFBamSchemaRefTableObj
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
	 *	Instantiate a new SchemaRef instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamSchemaRefObj newInstance();

	/**
	 *	Instantiate a new SchemaRef edition of the specified SchemaRef instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamSchemaRefEditObj newEditInstance( ICFBamSchemaRefObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamSchemaRefObj realiseSchemaRef( ICFBamSchemaRefObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamSchemaRefObj createSchemaRef( ICFBamSchemaRefObj Obj );

	/**
	 *	Read a SchemaRef-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SchemaRef-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamSchemaRefObj readSchemaRef( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a SchemaRef-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The SchemaRef-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamSchemaRefObj readSchemaRef( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamSchemaRefObj readCachedSchemaRef( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeSchemaRef( ICFBamSchemaRefObj obj );

	void deepDisposeSchemaRef( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamSchemaRefObj lockSchemaRef( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the SchemaRef-derived instances in the database.
	 *
	 *	@return	List of ICFBamSchemaRefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamSchemaRefObj> readAllSchemaRef();

	/**
	 *	Return a sorted map of all the SchemaRef-derived instances in the database.
	 *
	 *	@return	List of ICFBamSchemaRefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamSchemaRefObj> readAllSchemaRef( boolean forceRead );

	List<ICFBamSchemaRefObj> readCachedAllSchemaRef();

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The SchemaRef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamSchemaRefObj readSchemaRefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The SchemaRef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamSchemaRefObj readSchemaRefByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the map of CFBamScopeObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The SchemaRef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaRefObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaRefObj> readSchemaRefByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Get the map of CFBamSchemaRefObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The SchemaRef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaRefObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaRefObj> readSchemaRefByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamSchemaRefObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaId	The SchemaRef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaRefObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaRefObj> readSchemaRefBySchemaIdx( CFLibDbKeyHash256 SchemaId );

	/**
	 *	Get the map of CFBamSchemaRefObj instances sorted by their primary keys for the duplicate SchemaIdx key.
	 *
	 *	@param	SchemaId	The SchemaRef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaRefObj cached instances sorted by their primary keys for the duplicate SchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaRefObj> readSchemaRefBySchemaIdx( CFLibDbKeyHash256 SchemaId,
		boolean forceRead );

	/**
	 *	Get the CFBamSchemaRefObj instance for the unique UNameIdx key.
	 *
	 *	@param	SchemaId	The SchemaRef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaRef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamSchemaRefObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamSchemaRefObj readSchemaRefByUNameIdx(CFLibDbKeyHash256 SchemaId,
		String Name );

	/**
	 *	Get the CFBamSchemaRefObj instance for the unique UNameIdx key.
	 *
	 *	@param	SchemaId	The SchemaRef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaRef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamSchemaRefObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamSchemaRefObj readSchemaRefByUNameIdx(CFLibDbKeyHash256 SchemaId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamSchemaRefObj instances sorted by their primary keys for the duplicate RefSchemaIdx key.
	 *
	 *	@param	RefSchemaId	The SchemaRef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaRefObj cached instances sorted by their primary keys for the duplicate RefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaRefObj> readSchemaRefByRefSchemaIdx( CFLibDbKeyHash256 RefSchemaId );

	/**
	 *	Get the map of CFBamSchemaRefObj instances sorted by their primary keys for the duplicate RefSchemaIdx key.
	 *
	 *	@param	RefSchemaId	The SchemaRef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaRefObj cached instances sorted by their primary keys for the duplicate RefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaRefObj> readSchemaRefByRefSchemaIdx( CFLibDbKeyHash256 RefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamSchemaRefObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The SchemaRef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaRefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaRefObj> readSchemaRefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamSchemaRefObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The SchemaRef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaRefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaRefObj> readSchemaRefByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamSchemaRefObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The SchemaRef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaRefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaRefObj> readSchemaRefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamSchemaRefObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The SchemaRef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamSchemaRefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamSchemaRefObj> readSchemaRefByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	ICFBamSchemaRefObj readCachedSchemaRefByIdIdx( CFLibDbKeyHash256 Id );

	List<ICFBamSchemaRefObj> readCachedSchemaRefByTenantIdx( CFLibDbKeyHash256 TenantId );

	List<ICFBamSchemaRefObj> readCachedSchemaRefBySchemaIdx( CFLibDbKeyHash256 SchemaId );

	ICFBamSchemaRefObj readCachedSchemaRefByUNameIdx( CFLibDbKeyHash256 SchemaId,
		String Name );

	List<ICFBamSchemaRefObj> readCachedSchemaRefByRefSchemaIdx( CFLibDbKeyHash256 RefSchemaId );

	List<ICFBamSchemaRefObj> readCachedSchemaRefByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamSchemaRefObj> readCachedSchemaRefByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeSchemaRefByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeSchemaRefByTenantIdx( CFLibDbKeyHash256 TenantId );

	void deepDisposeSchemaRefBySchemaIdx( CFLibDbKeyHash256 SchemaId );

	void deepDisposeSchemaRefByUNameIdx( CFLibDbKeyHash256 SchemaId,
		String Name );

	void deepDisposeSchemaRefByRefSchemaIdx( CFLibDbKeyHash256 RefSchemaId );

	void deepDisposeSchemaRefByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeSchemaRefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 */
	ICFBamSchemaRefObj updateSchemaRef( ICFBamSchemaRefObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteSchemaRef( ICFBamSchemaRefObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The SchemaRef key attribute of the instance generating the id.
	 */
	void deleteSchemaRefByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The SchemaRef key attribute of the instance generating the id.
	 */
	void deleteSchemaRefByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaId	The SchemaRef key attribute of the instance generating the id.
	 */
	void deleteSchemaRefBySchemaIdx( CFLibDbKeyHash256 SchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	SchemaId	The SchemaRef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The SchemaRef key attribute of the instance generating the id.
	 */
	void deleteSchemaRefByUNameIdx(CFLibDbKeyHash256 SchemaId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	RefSchemaId	The SchemaRef key attribute of the instance generating the id.
	 */
	void deleteSchemaRefByRefSchemaIdx( CFLibDbKeyHash256 RefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The SchemaRef key attribute of the instance generating the id.
	 */
	void deleteSchemaRefByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The SchemaRef key attribute of the instance generating the id.
	 */
	void deleteSchemaRefByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Move the CFBamSchemaRefObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamSchemaRefObj refreshed cache instance.
	 */
	ICFBamSchemaRefObj moveUpSchemaRef( ICFBamSchemaRefObj Obj );

	/**
	 *	Move the CFBamSchemaRefObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamSchemaRefObj refreshed cache instance.
	 */
	ICFBamSchemaRefObj moveDownSchemaRef( ICFBamSchemaRefObj Obj );
}
